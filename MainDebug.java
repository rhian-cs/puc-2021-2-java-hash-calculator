
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import gui.MainWindow;
import hash_calculators.DirectoryMD5HashCalculator;
import hash_calculators.FileHashCalculator;

public class MainDebug {
  public static void main(String args[]) throws Exception {
    System.out.println("");
    long startTime = System.nanoTime();

    fileExample();
    // directoryExample();
    // guiExample();

    long stopTime = System.nanoTime();
    double seconds = (stopTime - startTime) * 0.000000001;

    System.out.println("\nExecution finished after " + seconds + " seconds.");
  }

  private static void fileExample() {
    try {
      System.out.println(FileHashCalculator.getHash("tmp/bigfile", "MD5"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void directoryExample() throws Exception {
    ArrayList<String> hashList = DirectoryMD5HashCalculator.getHashes("tmp/directory/");

    for (String hash : hashList) {
      System.out.println(hash);
    }
  }

  private static void guiExample() throws Exception {
    MainWindow window = new MainWindow();
  }
}
