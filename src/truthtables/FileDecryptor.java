package truthtables;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileDecryptor {

    private FileDecryptor() {

    }

    //Decrypts the content of the given path with a specified key
    public static String decryptFile(String path, Key key) throws IOException {
        Path encrpytedPath = Path.of(path);
        if(Files.notExists(encrpytedPath) && Files.isDirectory(encrpytedPath)){
            throw new IllegalArgumentException("File " + encrpytedPath + " does not exist or is not a directory");
        }

        Path decryptedPath = Path.of("decrypted-" + encrpytedPath.getFileName().toString());

        byte[] encryptedBytes = Files.readAllBytes(encrpytedPath);
        byte[] decryptedBytes = new XorCipher().decrypt(key, encryptedBytes, 0);
        Files.write(decryptedPath, decryptedBytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        return decryptedPath.toString();
    }

}
