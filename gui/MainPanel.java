package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainPanel extends JPanel {
  private static final int DEFAULT_OUTER_PADDING_LEFT = 25;
  private static final int DEFAULT_OUTER_PADDING_RIGHT = 25;
  private static final int DEFAULT_OUTER_PADDING_TOP = 10;
  private static final int DEFAULT_OUTER_PADDING_BOTTOM = 30;
  private static final int DEFAULT_OUTER_PADDING_BOTTOM_CLOSE = 0;

  private static final int LABEL_INNER_PADDING = 0;
  private static final int BUTTON_INNER_PADDING = 20;

  GridBagConstraints gbc;

  public MainPanel() {
    super(new GridBagLayout());

    defineGridBagConstraints();
    addComponents();
  }

  private void defineGridBagConstraints() {
    gbc = new GridBagConstraints();

    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridwidth = GridBagConstraints.REMAINDER;
  }

  private void setBottomOuterPadding(int bottomOuterPadding) {
    gbc.insets = new Insets(DEFAULT_OUTER_PADDING_TOP, DEFAULT_OUTER_PADDING_LEFT, bottomOuterPadding,
        DEFAULT_OUTER_PADDING_RIGHT);
  }

  private void setInnerPadding(int paddingX, int paddingY) {
    gbc.ipadx = paddingX;
    gbc.ipady = paddingY;
  }

  private void addComponents() {
    addLabelWithButton("Select a file or directory to calculate it's hash:", "Click to select");
    addButton("Calculate hash!");
  }

  private void addLabelWithButton(String labelText, String buttonText) {
    addLabel(labelText);
    addButton(buttonText);
  }

  private void addLabel(String labelText) {
    setBottomOuterPadding(DEFAULT_OUTER_PADDING_BOTTOM_CLOSE);
    setInnerPadding(LABEL_INNER_PADDING, LABEL_INNER_PADDING);
    JLabel label = new JLabel(labelText, SwingConstants.CENTER);
    add(label, gbc);
  }

  private void addButton(String buttonText) {
    setBottomOuterPadding(DEFAULT_OUTER_PADDING_BOTTOM);
    setInnerPadding(BUTTON_INNER_PADDING, BUTTON_INNER_PADDING);
    add(new JButton(buttonText), gbc);
  }
}
