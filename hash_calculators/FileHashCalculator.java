package hash_calculators;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHashCalculator {
  public static String getHash(String filename, String hashAlgorithmName) throws NoSuchAlgorithmException, IOException {
    File file = new File(filename);
    MessageDigest hashAlgorithm = MessageDigest.getInstance(hashAlgorithmName);

    // Set up streams
    FileInputStream fis = new FileInputStream(file);
    BufferedInputStream bis = new BufferedInputStream(fis);
    DigestInputStream dis = new DigestInputStream(bis, hashAlgorithm);

    // Read through the whole file and update its digest
    try {
      while (dis.read() != -1) {
      }
    } finally {
      fis.close();
    }

    return formatBytes(hashAlgorithm.digest());
  }

  private static String formatBytes(byte[] bytes) {
    StringBuilder hexStringBuilder = new StringBuilder();

    for (byte b : bytes) {
      hexStringBuilder.append(String.format("%02x", 0xFF & b));
    }

    return hexStringBuilder.toString();
  }
}
