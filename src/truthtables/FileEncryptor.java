package truthtables;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class FileEncryptor {
    public static String encryptFile(String filePath, Key key) throws IOException, InterruptedException {
        Path path = Path.of(filePath);
        if(Files.notExists(path) || Files.isDirectory(path)){
            throw new IllegalArgumentException();
        }

        Path encryptedPath = Path.of("encrypted-" + path.getFileName().toString());
        if(Files.notExists(encryptedPath)){
            Files.createFile(encryptedPath);
        }

        final int baseChunks = 20;
        final int maxChunksAllowed = 120;
        final double baseFileSizeForBaseChunk = 1e+7;
        final long fileSize = Files.size(path);

        if(fileSize <= 0){
            throw new IllegalArgumentException("File size must be greater than 0.");
        }

        int chunksAllocatedForFile = dynamicallyIncreaseFileChunks(fileSize, baseChunks, baseFileSizeForBaseChunk
                , baseChunks, maxChunksAllowed); //The number of file chunks allocated for this file

        //Bytes allocated to each thread rounded up
        int chunkPerThread = (int) fileSize / chunksAllocatedForFile;
        long beginByte = 0;
        long endByte = chunkPerThread;
        int pos = 1;
        long normalizedSize = ((long) chunkPerThread * chunksAllocatedForFile);

        try(
                ExecutorService vtEx = Executors.newVirtualThreadPerTaskExecutor();
                ExecutorService tpEx = Executors.newFixedThreadPool(12);
                FileChannel fc = FileChannel.open(path, StandardOpenOption.READ);
        ){

            Chunk chunk;
            AsyncChunkHandler asyncChunkHandler = new AsyncChunkHandler().openFileChannel(encryptedPath);
            AsyncOperator asyncOperator = new AsyncOperator(asyncChunkHandler);

            while(endByte < normalizedSize){
                chunk = new Chunk(pos, beginByte, endByte); // -> begin byte, endByte, position
                IO.println("Chunk: " + chunk.beginByte());
                asyncOperator.readBytesAsync(vtEx, tpEx, fc, chunk, key);
                beginByte = endByte;
                endByte+=chunkPerThread;
                pos++;
            }

            if(normalizedSize < fileSize){
                chunk = new Chunk(pos, normalizedSize, fileSize); // -> begin byte, endByte, position
                asyncOperator.readBytesAsync(vtEx, tpEx, fc, chunk, key);
            }


            vtEx.shutdown();
            vtEx.awaitTermination(5, TimeUnit.MINUTES);

            tpEx.shutdown();
            tpEx.awaitTermination(5, TimeUnit.MINUTES);


            asyncChunkHandler.finish();
        }



        return encryptedPath.toString();
    }

     static int dynamicallyIncreaseFileChunks(long fileSize, int baseChunks, double baseFileSizeForBaseChunk,
                                      int chunksAllocatedForFile, int maxChunksAllowed){
        //Dynamically increases chunks allowed by 10 per 100MB
        if(fileSize >= baseFileSizeForBaseChunk){
            //Increase for every 100MB
            int res = (int) (fileSize / baseFileSizeForBaseChunk);
            int speculatedChunks = baseChunks + (10 * res);
            //Cannot be more than 120 chunks per file
            chunksAllocatedForFile = Math.min(speculatedChunks, maxChunksAllowed);
        }

        return chunksAllocatedForFile;
    }
}
