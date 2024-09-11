package uptc.views.wildCardClasses;

import java.awt.*;
import java.io.InputStream;

@SuppressWarnings("CallToPrintStackTrace")
public class Global {
    public static Color HEADER_BACKGROUND_COLOR = new Color(0xFF, 0xDB, 0x80);
    public static Color HEADER_TEXT_COLOR = new Color(0x14, 0x1E, 0x0C);
    public static Color WORK_BACKGROUND_COLOR = new Color(211, 211, 211);
    public static Color WORK_TEXT_COLOR = new Color(0x14, 0x1E, 0x0C);
    public static Color FOOTER_BACKGROUND_COLOR = new Color(0xB3, 0xD7, 0xA2);
    public static Color FOOTER_TEXT_COLOR = new Color(0xFF, 0xFF, 0xFF);
    public static Color BUTTON_BACKGROUND_COLOR = new Color(0x90, 0xC2, 0x79);
    public static Color BUTTON_TEXT_COLOR = new Color(0xFF, 0xFF, 0xFF);
    public static Font FONT_TEXTS = createCustomFont("/fonts/Archivo-SemiBold.ttf", 20);
    public static Font FONT_TITLE_NORMAL = createCustomFont("/fonts/Newsreader-VariableFont_opsz,wght.ttf", 36);
    public static Font BUTTON_FONT = createCustomFont("/fonts/Archivo-SemiBold.ttf", 20);
    public static Font FONT_TITLE_BIG = createCustomFont("/fonts/Newsreader-VariableFont_opsz,wght.ttf", 84);
    public static Font FONT_TEXTS_SMALL = createCustomFont("/fonts/Archivo-SemiBold.ttf", 12);

    public static Font createCustomFont(String path, float size) {
        try {
            InputStream is = Global.class.getResourceAsStream(path);
            assert is != null;
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(path + " not loaded. Using serif font.");
            return new Font("serif", Font.PLAIN, 24);
        }
    }

}
