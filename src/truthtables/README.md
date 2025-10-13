
#### XOR FILE ENCRYPTOR WITH PARALLEL ENCRYPTION PROCESSING
A learning sandbox exploring **XOR** encryption with parallel processing.
</br>The encryptor splits files into chunks, processes them concurrently using virtual threads, and encrypts each chunk against a user-provided key.


**ARCHITECTURE**
1. 20 chunks and virtual threads for a base file size for 100MB and below increased by 10 chunks for each 100MB 
2. 12 fixed platform threads in a manged threadpool for encrypting chunks
3. Custom Handler which ensures chunks are written back in oder
4. State tracking for each chunk when encrypting

**NOTE**
</br> You can test the program by running the simple CLI

#### SOME PROBLEMS I ENCOUNTERED
#### 1. Global Position Tracking in Parallel Processing
Hit a subtle bug where each chunk was encrypting using its **local** index instead of its **global** file position.

**The Problem:**
- Chunk starting at byte 250 would use `key[0 % keyLen]` instead of `key[250 % keyLen]`
- During decryption, the algorithm correctly uses global positions
- Result: mismatched key positions = garbage output after the first chunk

**The Fix:**
Pass each chunk's starting byte position to the encryption function:
```java
long globalPosition = startingBytePosition + i;
byte encrypted = (byte)(data[i] ^ key[(int)(globalPosition % keyLen)]);
```

#### 2. Inefficient String Handling 
Converted each char in a string to its ASCII number before converting back to its binary form

**The problem:**
- Unnecessary creation of strings which polluted the JVM's heap
- Basically redoing what the CPU is built to do
- On^2 operations because I had to convert each char to a binary string and **XOR** each value of that string before appending the string

***The Fix:**
- Get each byte from the string's byte[] arr at that position and **XOR** it against the key bytes at that position
```java
  byte result = (byte)(dataByte ^ keyByte); // XORs all 8 bits in one operation
  ```

#### 3. Synchronizing Resources accessed by multiple threads
Hit a niche bug where multiple threads calling the `processChunk(Chunk chunk)` method led to very niche race conditions

**The problem:**
- The handler processes chunks in order using a check-then-put pattern
- Thread 1 finishes chunk 1, calls `checkForChunk()` looking for chunk 2
- Thread 2 with chunk 2 passes the initial `if(chunk.position() == expectedPosition)` check
- BUT before Thread 2 can process chunk 2, Thread 1's `checkForChunk()` runs and doesn't find it in the map yet
- Thread 2 then puts chunk 2 in the map, but Thread 1 has already moved on
- Result: chunk 2 sits in the map forever, never gets processed, file write stalls

**The Fix:**
- Synchronized the `submitChunk` method so the check-then-act happens atomically


### CHUNK STRUCTURE
```java
public final class Chunk {

    private final int position; // Keeps track of the chunk's position for ordering when rewriting to the file
    private final long beginByte; // The beginning of this chunk from the file
    private final long endByte;  // The end of this chunk from the file
    private byte[] chunkContent; // Reps the byte content read to the chunk
    private byte[] encryptedBytes; //The encrypted chunk content


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

```

