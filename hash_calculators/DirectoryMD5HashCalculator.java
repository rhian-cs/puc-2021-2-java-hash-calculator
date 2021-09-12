package hash_calculators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class DirectoryMD5HashCalculator {
  public static ArrayList<String> getHashes(String path) throws NoSuchAlgorithmException, IOException {
    File file = new File(path);
    ArrayList<String> hashes = new ArrayList<String>();

    if (file.isDirectory()) {
      getHashFromAllFiles(hashes, path);
    } else {
      addHashToList(hashes, path);
    }

    return hashes;
  }

  private static void addHashToList(ArrayList<String> hashes, String path)
      throws NoSuchAlgorithmException, IOException {

    hashes.add(FileMD5HashCalculator.getHash(path));
  }

  private static ArrayList<String> getHashFromAllFiles(ArrayList<String> hashes, String directoryPath)
      throws NoSuchAlgorithmException, IOException {
    Path directory = Paths.get(directoryPath);

    Files.walk(directory).forEach(filePath -> {
      if (Files.isRegularFile(filePath)) {
        try {
          addHashToList(hashes, filePath.toString());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    return hashes;
  }
}
