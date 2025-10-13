package truthtables;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;

public class AsyncOperator {

    private final XorCipher xorCipher;
    private final AsyncChunkHandler asyncChunkHandler;

    public AsyncOperator(AsyncChunkHandler asyncChunkHandler) {
        this.xorCipher = new XorCipher();
        this.asyncChunkHandler = asyncChunkHandler;
    }


    //Spawns a virtual thread everytime this method is called.
    // Sets a chunk's content and offloads to a platform thread for encryption
    public void readBytesAsync(ExecutorService virtualThreadExecutor,
                               ExecutorService threadPoolExecutor,
                               FileChannel fileChannel,
                               Chunk chunk,
                               Key key){
        int bufSize = (int) (chunk.endByte() - chunk.beginByte());

        virtualThreadExecutor.submit(() -> {;
            try{
                System.out.println("VT started for chunk: " + chunk.position());
                chunk.setChunkContent(readBytes(fileChannel, chunk, bufSize));
                submitToThreads(threadPoolExecutor, chunk, key);
            }catch(Exception e){
                IO.println("Failed to read chunk: " + chunk.position());
                e.printStackTrace();
            }
        });

    }

    //Spawns a platform thread everytime this method is called up to a max of 4.
    // Encrypts a byte array and offloads it for writing
    private void submitToThreads(ExecutorService threadPoolExecutor, Chunk chunk, Key key){
         threadPoolExecutor.submit(() -> {
            System.out.println("TP started for chunk: " + chunk.position());
            try{
                byte[] encrypted = xorCipher.encrypt(key, chunk.chunkContent(), chunk.beginByte());
                asyncChunkHandler.submitChunk(chunk.setEncryptedString(encrypted));
            }catch(Exception e){
                IO.println("ERROR: failed to encrypt chunk: " + chunk.position());
                e.printStackTrace();
            }
            System.out.println("TP ended for chunk: " + chunk.position());
        });

    }

    private static byte[] readBytes(FileChannel fc, Chunk chunk, int bufSize){
        try{
            ByteBuffer byteBuffer = ByteBuffer.allocate(bufSize) ;
            int read = fc.read(byteBuffer,(long) chunk.beginByte());
            if(read == -1 || read !=  bufSize){
                throw new IOException("Error reading chunk content");
            }
            byteBuffer.flip();
            return byteBuffer.array();
        }catch(IOException e){
            IO.println("Error reading chunk " + chunk.position());
            throw new RuntimeException(e);
        }
    }

}
