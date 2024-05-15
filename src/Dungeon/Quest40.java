package Dungeon;

import javax.swing.*;

public class Quest40 extends Quest{

    public Quest40() {
        super();
        super.questPanel.add(new JTextArea("QUEST ID 40"));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> hideQuest40());
        super.questPanel.add(exitButton);

    }

    public void showQuest40() {
        super.questFrame.setVisible(true);
    }

    public void hideQuest40() {
        super.questFrame.setVisible(false);
        //go to dungeon
        MapGUI.showMapGUI();
    }
}
