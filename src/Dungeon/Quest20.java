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

public class Quest20 extends Quest{

    public Quest20() {
        super();
        super.questPanel.add(new JTextArea("QUEST ID 20"));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> hideQuest20());
        super.questPanel.add(exitButton);

    }

    public void showQuest20() {
        super.questFrame.setVisible(true);
    }

    public void hideQuest20() {
        super.questFrame.setVisible(false);
        //go to dungeon
        MapGUI.showMapGUI();
    }
}
