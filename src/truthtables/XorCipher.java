package truthtables;

public class XorCipher {

    private final static int MAX_ASCII_LENGTH = 3;

    /**
     * Encrypts an array of bytes using XOR logic
     * @param key The key we're XOR'ing against
     * @param strBytes The bytes of the string we're encrypting
     * @param currentBytePos The current position of the byte so we can know which key char to encrypt the byte with
     * @return a byte array of the encrypted bytes
     *
     * */
    public byte[] encrypt(Key key, byte[] strBytes, double currentBytePos) {
        final byte[] keyBytes = key.keyBytes();
        final int keyLen = keyBytes.length;
        final byte[] out = new byte[strBytes.length];

        for(int i = 0; i < strBytes.length; i++) {
            double nextPos = currentBytePos + i;
            byte ch = (byte) (strBytes[i] ^ keyBytes[(int) (nextPos % keyLen)]); //XOR THE VALUES OF THE CURRENT STR BYTE AND KEY BYTE
            out[i] =  ch;
        }

        return out;
    }


    /**
     * Decrypts an encrypted string XOR'ed against a specific key.
     * Decrypting a value which wasn't encrypted by that key will result in jargon
     * @param key The key to decrypt the string
     * @param encryptedBytes The bytes from the encrypted string
     * @param currentBytePos The current position of the byte so we can know which key char to encrypt the byte with
     * @return an array of decrypted bytes
     * */
    public byte[] decrypt(Key key, byte[] encryptedBytes,  double currentBytePos) {
        return encrypt(key, encryptedBytes, currentBytePos);
    }




}
