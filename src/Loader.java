import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

public class Loader {

    public static Database readFile(File file, String password) {
        Database db = new Database();
        try {
            String enc = Files.readString(file.toPath());
            String dec;
            try{
                dec = Encryptor.decrypt(enc, password);
            } catch (Exception e){
                dec = enc;
            }
            db.setLastTry(LocalDateTime.now());
            db.fromString(dec);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }

    public static void writeFile(Database db, String password, File file) {
        try {
            db.setLastTry(LocalDateTime.now());
            String rawText = db.toRawString();
            String encryptedText = Encryptor.encrypt(rawText, password);
            Files.write(file.toPath(), encryptedText.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
