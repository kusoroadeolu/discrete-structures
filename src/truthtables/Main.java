import truthtables.FileDecryptor;
import truthtables.FileEncryptor;
import truthtables.Key;
import truthtables.XorCipher;

void main() throws IOException, InterruptedException {
    IO.println("Welcome to the file my file encryptor");
    String val = IO.readln("""
            1. Encrypt a message
            2. Decrypt a message
            3. Encrypt a text or image file
            4. Decrypt a text or image file
            """);

    int choice = Integer.parseInt(val);
    if(choice < 1 || choice > 4){
        IO.println("Invalid choice");
        return;
    }

    String keyStr = IO.readln("Enter your encryption/decryption key: ");
    Key key = new Key(keyStr);

    switch (choice){
        case 1 -> {
            String msg = IO.readln("Enter text to encrypt: ");
            byte[] b = new XorCipher().encrypt(key, msg.getBytes(), 0);
            String str = Base64.getEncoder().encodeToString(b);
            IO.println("Encrypted text is: " + str);
        }

        case 2 -> {
            String msg = IO.readln("Enter text to decrypt: ");
            byte[] encrypted = Base64.getDecoder().decode(msg.getBytes());
            byte[] b = new XorCipher().decrypt(key, encrypted, 0);
            IO.println("Decrypted text is: " + new String(b));
        }

        case 3 -> {
            String fp = IO.readln("Enter the full file path of the file to encrypt: ");
            String path = FileEncryptor.encryptFile(fp, key);
            IO.println("Encrypted file is located at: " + path);
        }

        case 4 -> {
            String fp = IO.readln("Enter the full file path of the file to decrypt: ");
            String path = FileDecryptor.decryptFile(fp, key);
            IO.println("Encrypted file is located at: " + path);
        }

        default -> throw new IllegalArgumentException("Invalid choice");
    }
}


