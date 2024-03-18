package GameSlots;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CharacterSelection extends JFrame {

    private final String[] characterNames = {"Wizard", "Mime", "Warrior", "Doctor", "Farmer"};
    private final String[] descriptions = {
            "Master of Arcane Arts. Weak in close combat.",
            "A silent but deadly performer. Confuses enemies with tricks.",
            "Strong and fearless fighter. Leads the charge on the battlefield.",
            "Heals allies and weakens enemies with medical knowledge.",
            "Hardworking and resourceful. Provides support with crops and livestock."
    };
    private String selectedCharacter;

    public CharacterSelection() {
        super("Character Selection");

        JPanel panel = new JPanel(new GridLayout(characterNames.length * 2, 1, 5, 5));

        for (int i = 0; i < characterNames.length; i++) {
            JLabel nameLabel = new JLabel(characterNames[i], JLabel.CENTER);
            nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));
            panel.add(nameLabel);

            JLabel descriptionLabel = new JLabel(descriptions[i]);
            descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(descriptionLabel);
        }

        JPanel buttonPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        for (String characterName : characterNames) {
            JRadioButton button = new JRadioButton(characterName);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedCharacter = ((JRadioButton) e.getSource()).getText();
                }
            });
            group.add(button);
            buttonPanel.add(button);
        }

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedCharacter != null) {
                    int confirmation = JOptionPane.showConfirmDialog(
                            CharacterSelection.this,
                            "Confirm selection of " + selectedCharacter + "?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirmation == JOptionPane.YES_OPTION) {
                        setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(CharacterSelection.this, "Please select a character!");
                }
            }
        });

        buttonPanel.add(confirmButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getSelectedCharacter() {
        return selectedCharacter;
    }

    public static void main(String[] args) {
        new CharacterSelection();
    }
}
