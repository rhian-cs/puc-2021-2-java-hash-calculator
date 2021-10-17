package gui.actions;

import java.awt.event.ActionListener;
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
      JOptionPane.showMessageDialog(null, "No file or directory was provided!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    try {
      DirectoryMD5HashCalculator.calculateAndOutputHashes(state.currentDirectory);
    } catch (NoSuchAlgorithmException | IOException ex) {
      ex.printStackTrace();
      return;
    }
  }
}
