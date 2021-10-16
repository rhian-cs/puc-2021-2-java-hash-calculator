
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import gui.MainWindow;
import hash_calculators.DirectoryMD5HashCalculator;
import hash_calculators.FileMD5HashCalculator;
import hash_calculators.HashCalculatorV2;
import hash_calculators.MD5HashCalculator;

public class MainDebug {
  public static void main(String args[]) throws Exception {
    System.out.println("\n");
    long startTime = System.nanoTime();

    // stringExample();
    // fileExample();
    // directoryExample();
    // guiExample();
    fileExampleV2();

    long stopTime = System.nanoTime();
    double seconds = (stopTime - startTime) * 0.000000001;

    System.out.println("\nExecution finished after " + seconds + " seconds.");
  }

  private static void stringExample() throws Exception {
    String senha = "admin";
    String hashHexadecimal = MD5HashCalculator.getHash(senha.getBytes("UTF-8"));

    System.out.println("\nString original:");
    System.out.println(hashHexadecimal + "\n");

    System.out.println("String da HASH Gerada pelo Algoritmo MD5");
    System.out.println(hashHexadecimal + "\n");
  }

  private static void fileExample() throws Exception {
    System.out.println(FileMD5HashCalculator.getHash("tmp/bigfile"));
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

  private static void fileExampleV2() {
    try {
      System.out.println(HashCalculatorV2.getHash("tmp/bigfile", "MD5"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
