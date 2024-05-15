package Dungeon;

import javax.swing.*;
import java.awt.*;
import Game.*;

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