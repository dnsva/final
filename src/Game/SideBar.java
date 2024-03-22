package Game;


import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;


public class SideBar {

    public static JPanel getPanel(){

        JPanel hey = new JPanel();

        JTextArea hello = new JTextArea("Hi from Game.SideBar!!!!!");
        hey.add(hello);

        return hey;

    }

}
