package gui.actions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import gui.GlobalState;

public class CalculateHashAction implements ActionListener {
  private GlobalState state;

  public CalculateHashAction(GlobalState state) {
    this.state = state;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("Directory: " + state.currentDirectory);
  }
}
