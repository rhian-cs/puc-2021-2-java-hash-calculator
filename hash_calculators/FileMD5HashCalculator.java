package hash_calculators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;

public class FileMD5HashCalculator {
  public static String getHash(String path) throws NoSuchAlgorithmException, IOException {
    File file = new File(path);
    byte[] fileBytes = Files.readAllBytes(file.toPath());

    return MD5HashCalculator.getHash(fileBytes);
  }
}
