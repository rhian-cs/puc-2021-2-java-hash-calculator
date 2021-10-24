package hash_calculators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

public class DirectoryMD5HashCalculator {
  private String path;
  private long filesCount;

  public DirectoryMD5HashCalculator(String path) throws IOException {
    this.path = path;
    this.filesCount = calculateFilesCount();
    System.out.println(filesCount + " files to hash.");
  }

  public void calculateAndOutputHashes() throws NoSuchAlgorithmException, IOException {
    File file = new File(path);

    if (file.isDirectory()) {
      calculateAndOutputAllFilesHashes(path);
    } else {
      calculateAndOutputFileHash(path);
    }
  }

  private long calculateFilesCount() throws IOException {
    Path dir = Paths.get(path);
    return Files.walk(dir).parallel().filter(p -> !p.toFile().isDirectory()).count();
  }

  private void calculateAndOutputFileHash(String path) throws NoSuchAlgorithmException, IOException {
    String hashString = FileHashCalculator.getHash(path, "MD5");

    System.out.println(path + ";\t" + hashString);
  }

  private void calculateAndOutputAllFilesHashes(String directoryPath) throws NoSuchAlgorithmException, IOException {
    Path directory = Paths.get(directoryPath);

    System.out.println(directoryPath);

    Files.walk(directory).forEach(filePath -> {
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
