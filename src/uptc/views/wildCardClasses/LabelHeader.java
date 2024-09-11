package uptc.views.wildCardClasses;

import javax.swing.*;
import java.awt.*;

public class LabelHeader extends JLabel {
    public LabelHeader(String text){
        super(text);
        setFont(Global.FONT_TEXTS);
        setForeground(Global.HEADER_TEXT_COLOR);
        setHorizontalAlignment(SwingConstants.CENTER);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
