package gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.components.FileSelector;
import gui.workers.DirectoryHashWorker;

public class MainPanel extends DefaultPanel implements ActionListener {

  private JButton calculateHashButton;
  private JButton cancelButton;
  private DirectoryHashWorker directoryHashWorker;

  protected void addComponents() {
    addButtonWithLabel("Select a file or directory to calculate it's hash:", "Click to select directory", this);

    calculateHashButton = addButton("Calculate hash!", this);
    cancelButton = addButton("Stop hashing", this);
    cancelButton.setEnabled(false);
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    switch (ev.getActionCommand()) {
    case "Calculate hash!":
      calculateHashAction();
      break;
    case "Stop hashing":
      calculateHashButton.setEnabled(true);
      cancelButton.setEnabled(false);
      directoryHashWorker.cancel(true);
      break;
    case "Click to select directory":
      state.currentDirectory = new FileSelector().call();
      break;
    }
  }

  private void calculateHashAction() {
    if (state.currentDirectory == "") {
      errorDialog("No file or directory was provided!");
      return;
    }

    try {
      calculateHashButton.setEnabled(false);
      cancelButton.setEnabled(true);

      (directoryHashWorker = new DirectoryHashWorker(state.currentDirectory, calculateHashButton, cancelButton, "MD5"))
          .execute();

    } catch (FileNotFoundException e) {
      errorDialog("The specified file or directory doesn't exist!");
    } catch (IOException e) {
      errorDialog("An error occurred when attempting to read the file or directory!");
    }
  }

  private void errorDialog(String message) {
    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
  }
}
