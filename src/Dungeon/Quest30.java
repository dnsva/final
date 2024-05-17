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
public class Quest30 extends Quest{

    public Quest30() {
        super();
        super.questPanel.add(new JTextArea("QUEST ID 30"));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> hideQuest30());
        super.questPanel.add(exitButton);

    }

    public void showQuest30() {
        super.questFrame.setVisible(true);
    }

    public void hideQuest30() {
        super.questFrame.setVisible(false);
        //go to dungeon
        MapGUI.showMapGUI();
    }
}
