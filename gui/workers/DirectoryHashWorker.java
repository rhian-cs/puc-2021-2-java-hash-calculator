package gui.workers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import hash_calculators.FileHashCalculator;

public class DirectoryHashWorker extends SwingWorker<Void, String> {
  private String path;
  private long filesCount;
  private JButton calculateHashButton;
  private JButton cancelButton;

  public DirectoryHashWorker(String path, JButton calculateHashButton, JButton cancelButton) throws IOException {
    this.calculateHashButton = calculateHashButton;
    this.cancelButton = cancelButton;
    this.path = path;
    this.filesCount = calculateFilesCount();
    System.out.println(filesCount + " files to hash.");
  }

  @Override
  protected Void doInBackground() throws NoSuchAlgorithmException, IOException {
    File file = new File(path);

    calculateAndOutputAllFilesHashes(path, file.isDirectory());

    return null;
  }

  @Override
  protected void process(List<String> hashes) {
    String latestHash = hashes.get(hashes.size() - 1);
    System.out.println(latestHash);
  }

  @Override
  public void done() {
    calculateHashButton.setEnabled(true);
    cancelButton.setEnabled(false);
  }

  private long calculateFilesCount() throws IOException {
    Path dir = Paths.get(path);
    return Files.walk(dir).parallel().filter(p -> !p.toFile().isDirectory()).count();
  }

  private void calculateAndOutputAllFilesHashes(String directoryPath, boolean isDirectory)
      throws NoSuchAlgorithmException, IOException {
    Path directory = Paths.get(directoryPath);

    if (isDirectory) {
      System.out.println(directoryPath);
    }

    Files.walk(directory).forEach(filePath -> {
      if (Files.isRegularFile(filePath) && !isCancelled()) {
        try {
          calculateAndOutputFileHash(filePath.toString());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void calculateAndOutputFileHash(String path) throws NoSuchAlgorithmException, IOException {
    String hashString = FileHashCalculator.getHash(path, "MD5");

    publish(formatHashData(path, hashString));
  }

  private String formatHashData(String path, String hashString) {
    return path + '\t' + hashString;
  }
}
