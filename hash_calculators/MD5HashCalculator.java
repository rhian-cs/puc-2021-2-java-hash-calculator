package hash_calculators;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashCalculator {
  public static String getHash(byte[] originalBytes) throws NoSuchAlgorithmException {
    MessageDigest hashAlgorithm = MessageDigest.getInstance("MD5");

    byte[] hashedBytes = hashAlgorithm.digest(originalBytes);

    return formatBytes(hashedBytes);
  }

  private static String formatBytes(byte[] bytes) {
    StringBuilder hexStringBuilder = new StringBuilder();

    for (byte b : bytes) {
      hexStringBuilder.append(String.format("%02X", 0xFF & b));
    }

    return hexStringBuilder.toString();
  }
}
