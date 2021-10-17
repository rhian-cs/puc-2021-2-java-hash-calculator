package hash_calculators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

public class DirectoryMD5HashCalculator {
  private static final int MAX_FILE_TREE_DEPTH = 1;

  public static void calculateAndOutputHashes(String path) throws NoSuchAlgorithmException, IOException {
    File file = new File(path);

    if (file.isDirectory()) {
      calculateAndOutputAllFilesHashes(path);
    } else {
      calculateAndOutputFileHash(path);
    }
  }

  private static void calculateAndOutputFileHash(String path) throws NoSuchAlgorithmException, IOException {
    String hashString = FileHashCalculator.getHash(path, "MD5");

    System.out.println(path + ";\t" + hashString);
  }

  private static void calculateAndOutputAllFilesHashes(String directoryPath)
      throws NoSuchAlgorithmException, IOException {
    Path directory = Paths.get(directoryPath);

    System.out.println(directoryPath);

    Files.walk(directory, MAX_FILE_TREE_DEPTH).forEach(filePath -> {
      if (Files.isRegularFile(filePath)) {
        try {
          calculateAndOutputFileHash(filePath.toString());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
