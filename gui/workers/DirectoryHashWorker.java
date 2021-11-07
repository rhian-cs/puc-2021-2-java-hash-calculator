package gui.workers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import hash_calculators.FileHashCalculator;

public class DirectoryHashWorker extends SwingWorker<Void, String> {
  private String directoryToHashPath;
  private long filesCount;
  private JButton calculateHashButton;
  private JButton cancelButton;

  private String hashAlgorithm;

  private Path outputFilePath;
  private BufferedWriter outputFileBuffer;

  public DirectoryHashWorker(String directoryToHashPath, JButton calculateHashButton, JButton cancelButton,
      String hashAlgorithm) throws IOException {
    this.calculateHashButton = calculateHashButton;
    this.cancelButton = cancelButton;
    this.directoryToHashPath = directoryToHashPath;
    this.filesCount = calculateFilesCount();

    this.hashAlgorithm = hashAlgorithm;

    setOutputPath();
    initializeOutputFileBuffer();

    System.out.println(filesCount + " files to hash.");
  }

  @Override
  protected Void doInBackground() throws NoSuchAlgorithmException, IOException {
    File directoryToHash = new File(directoryToHashPath);

    calculateAndOutputAllFilesHashes(directoryToHashPath, directoryToHash.isDirectory());

    return null;
  }

  @Override
  protected void process(List<String> hashes) {
    String latestHash = hashes.get(hashes.size() - 1);
    System.out.println(latestHash);

    try {
      outputFileBuffer.write(latestHash);
      outputFileBuffer.newLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void done() {
    try {
      outputFileBuffer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    calculateHashButton.setEnabled(true);
    cancelButton.setEnabled(false);

    JOptionPane.showMessageDialog(null, successMessage(), "Success!", JOptionPane.INFORMATION_MESSAGE);
  }

  private long calculateFilesCount() throws IOException {
    Path dir = Paths.get(directoryToHashPath);
    return Files.walk(dir).parallel().filter(p -> !p.toFile().isDirectory()).count();
  }

  private void calculateAndOutputAllFilesHashes(String directoryPath, boolean isDirectory)
      throws NoSuchAlgorithmException, IOException {
    Path directory = Paths.get(directoryPath);

    if (isDirectory) {
      System.out.println(directoryPath);
      publish(formatHashData(directoryPath, ""));
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
    String hashString = FileHashCalculator.getHash(path, hashAlgorithm);

    publish(formatHashData(path, hashString));
  }

  private String formatHashData(String path, String hashString) {
    return path + '\t' + hashString;
  }

  private void setOutputPath() {
    Date date = new Date(System.currentTimeMillis());
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    String formattedDateTime = dateFormat.format(date);
    String outputFileName = "hash-report-" + hashAlgorithm + "-" + formattedDateTime + ".tsv";

    String home = System.getProperty("user.home");

    this.outputFilePath = Paths.get(home, outputFileName);
  }

  private void initializeOutputFileBuffer() throws IOException {
    outputFileBuffer = Files.newBufferedWriter(outputFilePath);

    String header = "File\t" + hashAlgorithm + " Hash";

    outputFileBuffer.write(header);
    outputFileBuffer.newLine();
  }

  private String successMessage() {
    String filesGrammarForm = filesCount >= 2 ? " files were" : " file was";

    return filesCount + filesGrammarForm + " processed.\n" + "The TSV report was saved to the directory:\n"
        + outputFilePath.toString();
  }
}
