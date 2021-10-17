package gui;

import gui.actions.CalculateHashAction;
import gui.actions.SelectDirectoryAction;

public class MainPanel extends DefaultPanel {

  protected void addComponents() {
    addLabelWithButton("Select a file or directory to calculate it's hash:", "Click to select",
        new SelectDirectoryAction(state));
    addButton("Calculate hash!", new CalculateHashAction(state));
  }
}
