
import GameSlots.*;

import java.util.ArrayList;
import java.io.*;


public class PARRT_MAIN_RUNNER {

    public static void main(String[] args) throws IOException{

        SlotInfo slotInfoObject = new GameSlots.SlotInfo();

        //the folllowing code creates and initializes an arraylist with strings "a", "b", and "c"
        ArrayList<String> hi = new ArrayList<String>();
        hi.add("a");
        hi.add("b");
        hi.add("c");

        ArrayList<String> hi2 = new ArrayList<String>();
        hi2.add("a2");
        hi2.add("b2");
        hi2.add("c2");

        /*
        // Create a JTextArea with HTML content
        JFrame frame = new JFrame("HTML Text Area Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a JEditorPane with HTML content
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setContentType("text/html");

        String htmlContent = "<html><b>Hello</b> <i>World!</i><br>"
                + "<h2>&#128507;</h2></html>";

        //EMOJIS WORK

        editorPane.setText(htmlContent);

        editorPane.getCaret().setVisible(false);
        JScrollPane scrollPane = new JScrollPane(editorPane);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
         */

        new Game.GameVars();
        new Game.Inventory();

        //new Game.SideBar(); //has to be created before HomeVillage or anything that uses it

       // new Game.HomeVillage();

       // WeaponShop weaponShopObject = new Shops.WeaponShop();
       // Game.HomeVillage.showHomeVillage();
       // GameSlots.SlotInfo.showInfoFrame();
    }


}
