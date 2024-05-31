package AnnaTools;

import java.awt.*; //For gui
import java.io.File; //For files

/*
THIS CLASS INITIALIZES FONTS FOR USE IN GUI
 */
public class Fonts {
    public static Font Royalacid_o = null, curse_of_the_zombie = null, Pamela = null, BAD_GRUNGE = null, pepperoni_pizza = null, Corrupted_File = null, panicFont = null, dTheHero = null; //all the fonts
    public Fonts(){
        try {
            //LOAD the FONT FILES
            /* The following code loads all the font files into the Font fields */
            File fontFile = new File("src/AnnaTools/Royalacid_o.ttf");
            Royalacid_o = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            //------------------------------------------------------------
            fontFile = new File("src/AnnaTools/BAD GRUNGE.ttf");
            BAD_GRUNGE = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            //------------------------------------------------------------
            fontFile = new File("src/AnnaTools/pepperoni_pizza.ttf");
            pepperoni_pizza = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            //------------------------------------------------------------
            fontFile = new File("src/AnnaTools/CorruptedFile.ttf");
            Corrupted_File = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            //------------------------------------------------------------
            fontFile = new File("src/AnnaTools/panic.ttf");
            panicFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            //------------------------------------------------------------
            fontFile = new File("src/AnnaTools/D the hero.ttf");
            dTheHero = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            //------------------------------------------------------------
            fontFile = new File("src/AnnaTools/Curse of the Zombie.ttf");
            curse_of_the_zombie = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            //------------------------------------------------------------
            fontFile = new File("src/AnnaTools/Pamela.ttf");
            Pamela = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            //MAKING THE FONT A SPECIFIC SIZE......
            //These were determined based off testing and what fits into the windows nicely
            Royalacid_o = Royalacid_o.deriveFont(50f); //set size to 50
            BAD_GRUNGE = BAD_GRUNGE.deriveFont(100f); //set size to 100
            pepperoni_pizza = pepperoni_pizza.deriveFont(60f); //set size to 60
            Corrupted_File = Corrupted_File.deriveFont(60f); //set size to 60
            panicFont = panicFont.deriveFont(60f); //set size to 60
            dTheHero = dTheHero.deriveFont(45f); //set size to 45
            curse_of_the_zombie = curse_of_the_zombie.deriveFont(53f); //set size to 53
            Pamela = Pamela.deriveFont(70f); //set size to 70

            // Register the font with the graphics environment
            /* The following code registers every single font*/
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Royalacid_o); //font royal acid
            ge.registerFont(BAD_GRUNGE); //font bad grunge
            ge.registerFont(pepperoni_pizza); //font pepperoni pizza
            ge.registerFont(Corrupted_File); //font corrupted file
            ge.registerFont(panicFont); //font panic
            ge.registerFont(dTheHero); //font d the hero
            ge.registerFont(curse_of_the_zombie); //font curse of the zombie
            ge.registerFont(Pamela); //font pamela

            //System.out.println("Font loaded and registered successfully."); debugging

        } catch (Exception e) { //so that nothing has to be thrown
            System.out.println("Some error happened with fonts"); //message to console
            e.printStackTrace();
        }
    }
}
