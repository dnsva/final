package Dungeon;

import javax.swing.*;

public class Quest50 extends Quest {

    public Quest50() {
        super();
        super.questPanel.add(new JTextArea("QUEST ID 50"));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> hideQuest50());
        super.questPanel.add(exitButton);

    }

    public void showQuest50() {
        super.questFrame.setVisible(true);
    }

    public void hideQuest50() {
        super.questFrame.setVisible(false);
        //go to dungeon
        MapGUI.showMapGUI();
    }
}
