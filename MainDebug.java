
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainDebug {
  public static void main(String args[]) throws Exception {
    // stringExample();
    // fileExample();
    directoryExample();
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
    System.out.println(FileMD5HashCalculator.getHash("tmp/file_with_admin.txt"));
  }

  private static void directoryExample() throws Exception {
    ArrayList<String> hashList = DirectoryMD5HashCalculator.getHashes("tmp/directory/");

    for (String hash : hashList) {
      System.out.println(hash);
    }
  }
}
