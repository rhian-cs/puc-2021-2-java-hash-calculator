package gui.actions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectDirectoryAction implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("[SelectDirectoryAction] I was clicked!");
  }
}
