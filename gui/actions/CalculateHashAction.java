package gui.actions;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import gui.GlobalState;
import hash_calculators.DirectoryMD5HashCalculator;

public class CalculateHashAction implements ActionListener {
  private GlobalState state;

  public CalculateHashAction(GlobalState state) {
    this.state = state;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (state.currentDirectory == "") {
      return;
    }

    ArrayList<String> hashList;
    try {
      hashList = DirectoryMD5HashCalculator.getHashes(state.currentDirectory);
    } catch (NoSuchAlgorithmException | IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      return;
    }

    for (String hash : hashList) {
      System.out.println(hash);
    }
  }
}
