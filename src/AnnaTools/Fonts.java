package AnnaTools;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {
    public static Font Royalacid_o = null;
    public Fonts(){
        try {
            // Load the font file
            File fontFile = new File("src/AnnaTools/Royalacid_o.ttf");
            Royalacid_o = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            // Optionally, derive the font to a specific size
            Royalacid_o = Royalacid_o.deriveFont(50f);

            // Register the font with the graphics environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Royalacid_o);

            // Now you can use this font in your application, e.g., set it to a JLabel
            // JLabel label = new JLabel("Custom Font");
            // label.setFont(customFont);

            System.out.println("Font loaded and registered successfully.");

        } catch (FontFormatException e) {
            System.out.println("The font file is not in the correct format.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading the font file.");
            e.printStackTrace();
        }
    }
}
