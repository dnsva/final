package AnnaTools;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {
    public static Font Royalacid_o = null, curse_of_the_zombie = null, Pamela = null, BAD_GRUNGE = null, pepperoni_pizza = null, Corrupted_File = null, panicFont = null, dTheHero = null;
    public Fonts(){
        try {
            // Load the font file
            File fontFile = new File("src/AnnaTools/Royalacid_o.ttf");
            Royalacid_o = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            fontFile = new File("src/AnnaTools/BAD GRUNGE.ttf");
            BAD_GRUNGE = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            fontFile = new File("src/AnnaTools/pepperoni_pizza.ttf");
            pepperoni_pizza = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            fontFile = new File("src/AnnaTools/CorruptedFile.ttf");
            Corrupted_File = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            fontFile = new File("src/AnnaTools/panic.ttf");
            panicFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            fontFile = new File("src/AnnaTools/D the hero.ttf");
            dTheHero = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            fontFile = new File("src/AnnaTools/Curse of the Zombie.ttf");
            curse_of_the_zombie = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            fontFile = new File("src/AnnaTools/Pamela.ttf");
            Pamela = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            // Optionally, derive the font to a specific size
            Royalacid_o = Royalacid_o.deriveFont(50f);
            BAD_GRUNGE = BAD_GRUNGE.deriveFont(100f);
            pepperoni_pizza = pepperoni_pizza.deriveFont(60f);
            Corrupted_File = Corrupted_File.deriveFont(60f);
            panicFont = panicFont.deriveFont(60f);
            dTheHero = dTheHero.deriveFont(45f);
            curse_of_the_zombie = curse_of_the_zombie.deriveFont(53f);
            Pamela = Pamela.deriveFont(70f);

            // Register the font with the graphics environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Royalacid_o);
            ge.registerFont(BAD_GRUNGE);
            ge.registerFont(pepperoni_pizza);
            ge.registerFont(Corrupted_File);
            ge.registerFont(panicFont);
            ge.registerFont(dTheHero);
            ge.registerFont(curse_of_the_zombie);
            ge.registerFont(Pamela);

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
