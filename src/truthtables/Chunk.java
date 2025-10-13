package truthtables;

public final class Chunk {

    private final int position;
    private final long beginByte;
    private final long endByte;
    private byte[] chunkContent; // Content read to the chunk
    private byte[] encryptedBytes;


    public byte[] encryptedBytes() {
        return encryptedBytes;
    }

    public Chunk setEncryptedString(byte[] encryptedBytes) {
        this.encryptedBytes = encryptedBytes;
        return this;
    }

    public byte[] chunkContent() {
        return chunkContent;
    }

    public Chunk setChunkContent(byte[] chunkContent) {
        this.chunkContent = chunkContent;
        return this;
    }

    public Chunk(int position, long beginByte, long endByte) {
        this.position = position;
        this.beginByte = beginByte;
        this.endByte = endByte;
    }

    public double endByte() {
        return endByte;
    }

    public double beginByte() {
        return beginByte;
    }

    public int position() {
        return position;
    }
}
