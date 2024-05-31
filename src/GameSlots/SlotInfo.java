package GameSlots;

import Game.GameVars;
import Game.HomeVillage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SlotInfo {

    private static String slotName;
    private static String slotDifficulty;
    public static String slotCreationDate;
    private static String slotCharacter;

    private static JFrame slotInfoFrame = new JFrame();
    private static DefaultListModel<String> slotListModel = new DefaultListModel<>();
    private static JList<String> slotList = new JList<>(slotListModel);

    private static final int WINDOWWIDTH = 500;
    private static final int WINDOWHEIGHT = 300;
    public static final String SAVE_FILE_PATH = "src/GameSlots/GameSave.txt";

    public SlotInfo() {
        // Create save file if it doesn't exist
        File file = new File(SAVE_FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("ERROR FILES");
            }
        } else {
            loadSlotFromFile();
        }

        slotInfoFrame.setTitle("By Anna Denisova");
        slotInfoFrame.setSize(WINDOWWIDTH, WINDOWHEIGHT);
        slotInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        slotInfoFrame.setLocationRelativeTo(null);
        slotInfoFrame.getContentPane().setLayout(new BorderLayout());

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Slot"));
        listPanel.add(new JScrollPane(slotList));
        slotInfoFrame.getContentPane().add(listPanel, BorderLayout.CENTER);
        listPanel.setBackground(Color.decode("#9AD1D4"));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        JButton selectButton = new JButton("Select Slot");
        JButton addButton = new JButton("Add Slot");
        JButton deleteButton = new JButton("Delete Slot");
        JButton renameButton = new JButton("Rename Slot");

        buttonPanel.add(selectButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(renameButton);

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectSlot();
            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSlot();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSlot();
            }
        });
        renameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renameSlot();
            }
        });

        slotInfoFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void showInfoFrame() {
        slotInfoFrame.setVisible(true);
    }

    public static void hideInfoFrame() {
        slotInfoFrame.setVisible(false);
    }

    public static void selectSlot() {
        if (slotName != null) {
            hideInfoFrame();
            GameVars.slotNameLocal = slotName;
            GameVars.difficultyLevel = slotDifficulty;
            GameVars.characterType = slotCharacter;
            new HomeVillage();
            Game.HomeVillage.showHomeVillage();
        } else {
            JOptionPane.showMessageDialog(slotInfoFrame, "Please add a slot first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void addSlot() {
        if (slotName != null) {
            JOptionPane.showMessageDialog(slotInfoFrame, "You can only have one slot", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ImageIcon icon1 = new ImageIcon("src/GameSlots/31_chocolatecake_dish.png");
        ImageIcon icon2 = new ImageIcon("src/GameSlots/23_cheesecake_dish.png");

        Object nameObj = JOptionPane.showInputDialog(slotInfoFrame, "Enter slot name:", "Slot Name", JOptionPane.QUESTION_MESSAGE, icon1, null, null);
        if (nameObj == null) return;
        String name = (String) nameObj;
        if (name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(slotInfoFrame, "Slot name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] difficultyOptions = {"Easy", "Medium", "Hard"};
        String difficulty = (String) JOptionPane.showInputDialog(slotInfoFrame, "Select difficulty:", "Difficulty", JOptionPane.QUESTION_MESSAGE, icon2, difficultyOptions, difficultyOptions[0]);
        if (difficulty == null) return;

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        JFrame characterSelectFrame = new JFrame();
        characterSelectFrame.setVisible(true);
        characterSelectFrame.setTitle("By Anna Denisova");
        characterSelectFrame.setSize(WINDOWWIDTH, WINDOWHEIGHT + 370);
        characterSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        characterSelectFrame.setLocationRelativeTo(null);
        characterSelectFrame.getContentPane().setLayout(new BorderLayout());
        hideInfoFrame();

        String[] characterNames = {"Wizard", "Mime", "Warrior", "Doctor", "Farmer"};
        final String[] selectedCharacter = {null};

        JPanel characterSelectionPanel = new JPanel();
        JEditorPane namesAndDescriptions = new JEditorPane();
        namesAndDescriptions.setContentType("text/html");
        namesAndDescriptions.setText(getCharacterDescriptions());
        namesAndDescriptions.setEditable(false);
        characterSelectionPanel.add(namesAndDescriptions);
        characterSelectionPanel.setPreferredSize(new Dimension(WINDOWWIDTH, WINDOWHEIGHT + 370));

        JPanel buttonPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        for (String characterName : characterNames) {
            JRadioButton button = new JRadioButton(characterName);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedCharacter[0] = ((JRadioButton) e.getSource()).getText();
                }
            });
            group.add(button);
            buttonPanel.add(button);
        }

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedCharacter[0] != null) {
                    int confirmation = JOptionPane.showConfirmDialog(characterSelectFrame, "Confirm selection of " + selectedCharacter[0] + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        slotName = name;
                        slotDifficulty = difficulty;
                        slotCreationDate = date;
                        slotCharacter = selectedCharacter[0];

                        slotListModel.addElement(String.format("%s (%s) (%s) - Created on: %s", name, difficulty, selectedCharacter[0], date));
                        writeSlotToFile();
                        showInfoFrame();
                        characterSelectFrame.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(characterSelectFrame, "Please select a character!");
                }
            }
        });

        buttonPanel.add(confirmButton);
        characterSelectFrame.getContentPane().add(characterSelectionPanel, BorderLayout.CENTER);
        characterSelectFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void deleteSlot() {
        if (slotName != null) {
            slotListModel.clear();
            slotName = null;
            slotDifficulty = null;
            slotCreationDate = null;
            slotCharacter = null;
            clearSlotFile();
        } else {
            JOptionPane.showMessageDialog(slotInfoFrame, "Please select a slot first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void renameSlot() {
        if (slotName != null) {
            ImageIcon icon = new ImageIcon("src/GameSlots/91_strawberrycake_dish.png");
            Object newNameObj = JOptionPane.showInputDialog(slotInfoFrame, "Enter new name for the slot", "New Slot Name", JOptionPane.QUESTION_MESSAGE, icon, null, null);
            if (newNameObj == null) return;
            String newName = (String) newNameObj;
            if (newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(slotInfoFrame, "Slot name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            slotName = newName;
            slotListModel.set(0, String.format("%s (%s) (%s) - Created on: %s", newName, slotCharacter, slotDifficulty, slotCreationDate));
            renameSlotFile(newName);
        } else {
            JOptionPane.showMessageDialog(slotInfoFrame, "Please select a slot first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void renameSlotFile(String newName) {
        File file = new File(SAVE_FILE_PATH);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        try {
            // Read all lines from the file into a list of strings
            ArrayList<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            // Modify the first line
            if (!lines.isEmpty()) {
                lines.set(0, newName);
            }

            // Write the modified content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String modifiedLine : lines) {
                writer.write(modifiedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeSlotToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE_PATH))) {
            writer.write(slotName + "\n" + slotDifficulty + "\n" + slotCharacter + "\n" + slotCreationDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearSlotFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE_PATH))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadSlotFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE_PATH))) {
            slotName = reader.readLine();
            slotDifficulty = reader.readLine();
            slotCharacter = reader.readLine();
            slotCreationDate = reader.readLine();
            if (slotName != null) {
                slotListModel.addElement(String.format("%s (%s) (%s) - Created on: %s", slotName, slotDifficulty, slotCharacter, slotCreationDate));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCharacterDescriptions() {
        return "<html><body style='font-family: PT Mono; font-size: 9px;'>" +
                "<div style='background-color: #9AD1D4;'>" +
                "<p><b>&nbsp;&nbsp;WIZARD</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>" +
                "<p>&nbsp;- Start game with Basket of Berries</p>" +
                "<p>&nbsp;- 25% off all sanity medicine</p>" +
                "<p>&nbsp;- Default attack power: 10</p>" +
                "<p><b>&nbsp;&nbsp;MIME</b></p>" +
                "<p>&nbsp;- Useless and weak</p>" +
                "<p>&nbsp;- Gets 1 free apple</p>" +
                "<p>&nbsp;- Default attack power: 1</p>" +
                "<p>&nbsp;&nbsp;<b>WARRIOR</b></p>" +
                "<p>&nbsp;- Start game with Axe</p>" +
                "<p>&nbsp;- 25% off all weapons</p>" +
                "<p>&nbsp;- Default attack power: 15</p>" +
                "<p>&nbsp;&nbsp;<b>DOCTOR</b></p>" +
                "<p>&nbsp;- Start game with Regeneration Pill</p>" +
                "<p>&nbsp;- 25% off all healing potions</p>" +
                "<p>&nbsp;- Default attack power: 10</p>" +
                "<p>&nbsp;&nbsp;<b>FARMER</b></p>" +
                "<p>&nbsp;- Bad at fighting</p>" +
                "<p>&nbsp;- Start game with all the food</p>" +
                "<p>&nbsp;- Default attack power: 0 <br>&nbsp;<br></p>" +
                "</div></body></html>";
    }

    public static void main(String[] args) {
        new SlotInfo();
        showInfoFrame();
    }
}
