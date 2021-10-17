package gui.actions;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
