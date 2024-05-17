package Dungeon;

//IMPORT ALL PACKAGES -----
import java.io.*;       //-
import java.util.*;     //-
import java.awt.*;      //-
import javax.swing.*;   //-
import Game.*;          //-
import Items.*;         //-
import Monsters.*;      //-
import Shops.*;         //-
import AnnaTools.*;     //-
//-------------------------
public class Quest10 extends Quest {
    public Quest10() {
        super();
        super.questPanel.add(new JTextArea("QUEST ID 10"));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> hideQuest10());
        super.questPanel.add(exitButton);

    }

    public void showQuest10() {
        super.questFrame.setVisible(true);
    }

    public void hideQuest10() {
        super.questFrame.setVisible(false);
        //go to dungeon
        MapGUI.showMapGUI();
    }
    public static void main(String[] args) {
        Quest10 hi = new Quest10();
        hi.questFrame.setVisible(true);
    }
}
