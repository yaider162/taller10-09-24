package uptc.views.wildCardClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomJComboBox extends JComboBox<String> {
    public CustomJComboBox(String[] items){
        super(items);
        setOpaque(false);
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setOpaque(isSelected);
                return this;
            }
        });
        setEditor(new ComboBoxEditor() {
            final JTextField editor = new JTextField();
            @Override
            public Component getEditorComponent() {
                editor.setOpaque(true);
                editor.setBackground(Color.WHITE);
                return editor;
            }

            @Override
            public void setItem(Object anObject) {
                editor.setText((anObject != null) ? anObject.toString() : "");
            }

            @Override
            public Object getItem() {
                return editor.getText();
            }

            @Override
            public void selectAll() {
                editor.selectAll();
            }

            @Override
            public void addActionListener(ActionListener l) {
                editor.addActionListener(l);
            }

            @Override
            public void removeActionListener(ActionListener l) {
                editor.removeActionListener(l);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        int radius = 10;
        g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do nothing to remove the border
    }
}