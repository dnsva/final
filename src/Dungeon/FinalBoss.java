package Dungeon;

import javax.swing.*;

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
