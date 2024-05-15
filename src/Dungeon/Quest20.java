package Dungeon;

import javax.swing.*;

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
