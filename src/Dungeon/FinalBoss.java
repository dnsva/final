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

public class FinalBoss extends Quest{

    public FinalBoss() {
        super();
        super.questPanel.add(new JTextArea("QUEST ID FinalBoss"));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> hideFinalBoss());
        super.questPanel.add(exitButton);

    }

    public void showFinalBoss() {
        super.questFrame.setVisible(true);
    }

    public void hideFinalBoss() {
        super.questFrame.setVisible(false);
        //go to dungeon
        MapGUI.showMapGUI();
    }
}
