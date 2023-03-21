package view;

import javax.swing.*;
public class RoundedTextField extends JTextField{
public RoundedTextField(int size) {
        super(size);
        setOpaque(false);
    }
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-3, getHeight()-3, 20, 20);
        super.paintComponent(g);
    }
    @Override
    protected void paintBorder(java.awt.Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth()-3, getHeight()-3, 20, 20);
    }
}
