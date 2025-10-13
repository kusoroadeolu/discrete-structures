package truthtables;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AsyncChunkHandler {

    private final Map<Integer, Chunk> map;
    private int expectedPosition;
    private FileChannel fileChannel;
    private long currentWritePosition; //var to store the write position for the file channel

    public AsyncChunkHandler() {
        this.expectedPosition = 1;
        this.currentWritePosition = 0;
        this.map = new ConcurrentHashMap<>();
    }


    public AsyncChunkHandler openFileChannel(Path filePath) throws IOException {
        fileChannel = FileChannel.open(filePath, StandardOpenOption.WRITE);
        return this;
    }

    //Submits a chunk for processing, checks if the chunk is the expected number, otherwise, put in the map
    public synchronized void submitChunk(Chunk chunk) {
        if(chunk.position() == this.expectedPosition) {
            IO.println("Submitting chunk: " + chunk.position());
            processChunk(chunk);
        }else {
            IO.println("Putting chunk in map: " + chunk.position());
            this.map.putIfAbsent(chunk.position(), chunk);
        }
    }

    //Checks if the chunk with the expected pos, exists in the map for processing
    public void checkForChunk() {
        if(map.containsKey(expectedPosition)) {
            processChunk(map.get(expectedPosition));
        }
    }


    private void processChunk(Chunk chunk) {
        try{
            byte[] b = chunk.encryptedBytes();
            ByteBuffer byteBuffer = ByteBuffer.wrap(b);
            long bytesWritten = fileChannel.write(byteBuffer, currentWritePosition);
            IO.println("Bytes written 1: " + bytesWritten);

            while(byteBuffer.hasRemaining()) {
                bytesWritten = fileChannel.write(byteBuffer, currentWritePosition);
                currentWritePosition += bytesWritten;
            }


            currentWritePosition += bytesWritten;
            expectedPosition++;
            checkForChunk();
            IO.println("Expecting chunk: " + expectedPosition);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void finish() throws IOException {
        fileChannel.close();
    }

}
