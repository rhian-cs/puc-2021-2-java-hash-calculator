package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
  private static final int INNER_PADDING = 10;

  GridBagConstraints gbc;

  public MainPanel() {
    super(new GridBagLayout());

    gbc = new GridBagConstraints();
    defineGridBagConstraints();

    addComponents();
  }

  private void defineGridBagConstraints() {
    gbc.weightx = 1;
    gbc.ipadx = 20;
    gbc.ipady = 30;
    gbc.insets = panelInsets();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridwidth = GridBagConstraints.REMAINDER;
  }

  private Insets panelInsets() {
    return new Insets(INNER_PADDING, INNER_PADDING, INNER_PADDING, INNER_PADDING);
  }

  private void addComponents() {
    addButton("Button 1");
    addButton("Button 2");
    addButton("Button 3");
  }

  private void addButton(String label) {
    JButton button = new JButton(label);

    add(button, gbc);
  }
}
