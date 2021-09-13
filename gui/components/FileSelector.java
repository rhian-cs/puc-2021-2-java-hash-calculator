package gui.components;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class FileSelector extends JFileChooser {
  private static final String TITLE = "Choose a file directory that you wish to calculate the hash of:";
  private static final String APPROVE_BUTTON_TEXT = "Select";

  public FileSelector() {
    super(FileSystemView.getFileSystemView().getHomeDirectory());
    setDialogTitle(TITLE);
    setApproveButtonText(APPROVE_BUTTON_TEXT);
    setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
  }

  public String call() {
    int returnValue = showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      return getSelectedFile().toString();
    }

    return "";
  }
}
