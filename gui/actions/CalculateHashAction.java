package gui.actions;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import gui.GlobalState;
import hash_calculators.DirectoryMD5HashCalculator;

public class CalculateHashAction implements ActionListener {
  private GlobalState state;

  public CalculateHashAction(GlobalState state) {
    this.state = state;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    if (state.currentDirectory == "") {
      errorDialog("No file or directory was provided!");
      return;
    }

    try {
      DirectoryMD5HashCalculator calculator = new DirectoryMD5HashCalculator(state.currentDirectory);
      calculator.calculateAndOutputHashes();
    } catch (NoSuchAlgorithmException e) {
      errorDialog("The specified hash algorithm doesn't exist!");
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
