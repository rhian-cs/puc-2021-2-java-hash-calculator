
import java.io.File;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;

public class FileMD5HashCalculator {
  byte[] fileBytes;

  public FileMD5HashCalculator(String path) {
    try {
      File file = new File(path);
      fileBytes = Files.readAllBytes(file.toPath());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getHash() throws NoSuchAlgorithmException {
    return MD5HashCalculator.getHash(fileBytes);
  }
}
