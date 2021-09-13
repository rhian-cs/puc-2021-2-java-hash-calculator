package gui.actions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import gui.components.FileSelector;
import gui.GlobalState;

public class SelectDirectoryAction implements ActionListener {

  private GlobalState state;

  public SelectDirectoryAction(GlobalState state) {
    this.state = state;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    state.currentDirectory = new FileSelector().call();
  }
}
