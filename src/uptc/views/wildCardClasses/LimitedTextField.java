package uptc.views.wildCardClasses;

import javax.swing.FocusManager;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class LimitedTextField extends JTextField {
    private final String placeholder;

    public LimitedTextField(int limit, String placeholder) {
        setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (getLength() + str.length() <= limit) {
                    super.insertString(offs, str, a);
                }
            }
        });
        this.placeholder = placeholder;
        setOpaque(false); // This is necessary for the rounded corners to be visible
        setTextFieldProperties();
    }

    private void setTextFieldProperties() {
        setBackground(Color.WHITE);
        setFont(Global.FONT_TEXTS);
        setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        paintRoundedBackground(g);
        super.paintComponent(g);
        paintPlaceholder(g);
    }

    private void paintRoundedBackground(Graphics g) {
        g.setColor(getBackground());
        // Radius for the rounded corners
        int radius = 10;
        g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
    }

    private void paintPlaceholder(Graphics g) {
        if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
            Graphics2D g2 = (Graphics2D)g.create();
            g2.setColor(Color.gray); //Color of the placeholder
            g2.setFont(Global.FONT_TEXTS_SMALL);
            g2.drawString(placeholder, 5, 20); //draws placeholder
            g2.dispose();
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do nothing to remove the border
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
    }
}