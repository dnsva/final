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

public class Quest {
    JFrame questFrame;
    JPanel questPanel;

    public Quest() {
        questFrame = new JFrame();
        questFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT);
        questFrame.setLayout(new BorderLayout());
        questFrame.setLocationRelativeTo(null);
        questFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questPanel = new JPanel();
        questFrame.add(questPanel, BorderLayout.CENTER);
        SideBar sideBar = new SideBar();
        questFrame.add(sideBar.getPanel(), BorderLayout.EAST);
    }

    public static void main(String[] args) {
        Quest hi = new Quest();
        hi.questFrame.setVisible(true);
    }
}