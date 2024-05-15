package Dungeon;

import javax.swing.*;

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
