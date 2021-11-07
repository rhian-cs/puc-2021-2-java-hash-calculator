package gui;

import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class DefaultPanel extends JPanel {
  private static final int DEFAULT_OUTER_PADDING_LEFT = 25;
  private static final int DEFAULT_OUTER_PADDING_RIGHT = 25;
  private static final int DEFAULT_OUTER_PADDING_TOP = 10;
  private static final int DEFAULT_OUTER_PADDING_BOTTOM = 30;
  private static final int DEFAULT_OUTER_PADDING_BOTTOM_CLOSE = 0;

  private static final int LABEL_INNER_PADDING = 0;
  private static final int BUTTON_INNER_PADDING = 20;

  private GridBagConstraints gbc;
  private Font standardFont;
  private Font buttonFont;
  protected GlobalState state;

  protected DefaultPanel() {
    super(new GridBagLayout());

    state = new GlobalState();

    standardFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
    buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

    defineGridBagConstraints();
    addComponents();
  }

  protected abstract void addComponents();

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

  protected void addButtonWithLabel(String labelText, String buttonText, ActionListener listener) {
    addLabel(labelText);
    addButton(buttonText, listener);
  }

  protected void addLabel(String labelText) {
    setBottomOuterPadding(DEFAULT_OUTER_PADDING_BOTTOM_CLOSE);
    setInnerPadding(LABEL_INNER_PADDING, LABEL_INNER_PADDING);
    JLabel label = new JLabel(labelText, SwingConstants.LEFT);
    label.setFont(standardFont);
    add(label, gbc);
  }

  protected JButton addButton(String buttonText, ActionListener listener) {
    setBottomOuterPadding(DEFAULT_OUTER_PADDING_BOTTOM);
    setInnerPadding(BUTTON_INNER_PADDING, BUTTON_INNER_PADDING);

    JButton button = new JButton(buttonText);
    button.setFont(buttonFont);
    button.setActionCommand(buttonText);
    button.addActionListener(listener);
    add(button, gbc);

    return button;
  }

  protected JComboBox<String> addComboBoxWithLabel(String labelText, String[] options) {
    addLabel(labelText);

    return addComboBox(options);
  }

  private JComboBox<String> addComboBox(String[] options) {
    setBottomOuterPadding(DEFAULT_OUTER_PADDING_BOTTOM);
    setInnerPadding(BUTTON_INNER_PADDING, BUTTON_INNER_PADDING);

    JComboBox<String> comboBox = new JComboBox<String>(options);

    add(comboBox, gbc);

    return comboBox;
  }
}
