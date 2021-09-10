
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MainDebug {
  public static void main(String args[]) throws Exception {
    // stringExample();
    fileExample();
  }

  private static void fileExample() throws Exception {
    FileMD5HashCalculator hashCalculator = new FileMD5HashCalculator("tmp/file_with_admin.txt");

    System.out.println(hashCalculator.getHash());
  }

  private static void stringExample() throws Exception {
    String senha = "admin";
    String hashHexadecimal = MD5HashCalculator.getHash(senha.getBytes("UTF-8"));

    System.out.println("\nString original:");
    System.out.println(hashHexadecimal + "\n");

    System.out.println("String da HASH Gerada pelo Algoritmo MD5");
    System.out.println(hashHexadecimal + "\n");
  }
}
