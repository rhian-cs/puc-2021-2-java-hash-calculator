package gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.components.FileSelector;
import hash_calculators.DirectoryMD5HashCalculator;

public class MainPanel extends DefaultPanel implements ActionListener {

  private JButton calculateHashButton;

  protected void addComponents() {
    addButtonWithLabel("Select a file or directory to calculate it's hash:", "Click to select directory", this);

    calculateHashButton = addButton("Calculate hash!", this);
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    switch (ev.getActionCommand()) {
    case "Calculate hash!":
      calculateHashAction();
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
      DirectoryMD5HashCalculator calculator = new DirectoryMD5HashCalculator(state.currentDirectory);
      calculator.calculateAndOutputHashes();
    } catch (NoSuchAlgorithmException e) {
      errorDialog("The specified hash algorithm doesn't exist!");
    } catch (FileNotFoundException e) {
      errorDialog("The specified file or directory doesn't exist!");
    } catch (IOException e) {
      errorDialog("An error occurred when attempting to read the file or directory!");
    } finally {
      calculateHashButton.setEnabled(true);
    }
  }

  private void errorDialog(String message) {
    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
  }
}
