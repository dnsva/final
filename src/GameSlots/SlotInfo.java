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

    private static String slotName; // Slot name
    private static String slotDifficulty; // Slot difficulty
    public static String slotCreationDate; // Slot creation date
    private static String slotCharacter; // Slot character

    private static JFrame slotInfoFrame = new JFrame(); // Slot info frame
    private static DefaultListModel<String> slotListModel = new DefaultListModel<>(); // Slot list model
    private static JList<String> slotList = new JList<>(slotListModel); // Slot list

    private static final int WINDOWWIDTH = 500; // Window width
    private static final int WINDOWHEIGHT = 300; // Window height
    public static final String SAVE_FILE_PATH = "src/GameSlots/GameSave.txt"; // Save file path

    public SlotInfo() {
        // Create save file if it doesn't exist
        File file = new File(SAVE_FILE_PATH);
        if (!file.exists()) {  //if the file doesnt already exist
            try {
                file.createNewFile(); //create a new one
            } catch (IOException e) { //so that you dont have to throw anything
                System.out.println("ERROR FILES");
            }
        } else {
            loadSlotFromFile(); //call fn
        }

        slotInfoFrame.setTitle("By Anna Denisova"); //title
        slotInfoFrame.setSize(WINDOWWIDTH, WINDOWHEIGHT); //size
        slotInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //close setting
        slotInfoFrame.setLocationRelativeTo(null); //center
        slotInfoFrame.getContentPane().setLayout(new BorderLayout()); //layout

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Slot"));
        listPanel.add(new JScrollPane(slotList));
        slotInfoFrame.getContentPane().add(listPanel, BorderLayout.CENTER);
        listPanel.setBackground(Color.decode("#9AD1D4")); //set background color

        /* The following code creates 4 buttons to be used in a grid layout */
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        JButton selectButton = new JButton("Select Slot");
        JButton addButton = new JButton("Add Slot");
        JButton deleteButton = new JButton("Delete Slot");
        JButton renameButton = new JButton("Rename Slot");

        /* The following code adds 4 buttons to the panel */
        buttonPanel.add(selectButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(renameButton);

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectSlot(); //call fn
            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSlot(); //call fn
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSlot(); //call fn
            }
        });
        renameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renameSlot(); //call fn
            }
        });

        slotInfoFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH); //add all the buttons to the bottom
    }

    public static void showInfoFrame() { //show the frame
        slotInfoFrame.setVisible(true); //set visible
    }

    public static void hideInfoFrame() { //hide the frame
        slotInfoFrame.setVisible(false); //set invisible
    }

    public static void selectSlot() { //select the slot
        if (slotName != null) { //if there is a slot
            hideInfoFrame(); //hide the frame
            GameVars.slotNameLocal = slotName; //set the slot name
            GameVars.difficultyLevel = slotDifficulty; //set the difficulty level
            GameVars.characterType = slotCharacter; //set the character type
            new HomeVillage(); //create a new home village
            Game.HomeVillage.showHomeVillage(); //show the home village
        } else {
            //ERROR MESSAGE:
            JOptionPane.showMessageDialog(slotInfoFrame, "Please add a slot first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void addSlot() { //create a new slot!!!
        if (slotName != null) { //if there is already a slot
            //ERROR MESSAGE
            JOptionPane.showMessageDialog(slotInfoFrame, "You can only have one slot", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ImageIcon icon1 = new ImageIcon("src/GameSlots/31_chocolatecake_dish.png"); //icon
        ImageIcon icon2 = new ImageIcon("src/GameSlots/23_cheesecake_dish.png"); //icon

        //get the name of the slot
        Object nameObj = JOptionPane.showInputDialog(slotInfoFrame, "Enter slot name:", "Slot Name", JOptionPane.QUESTION_MESSAGE, icon1, null, null);
        if (nameObj == null) return; //if the name is null
        String name = (String) nameObj; //set the name
        if (name.trim().isEmpty()) { //if the name is empty
            //ERROR MESSAGE
            JOptionPane.showMessageDialog(slotInfoFrame, "Slot name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] difficultyOptions = {"Easy", "Medium", "Hard"}; //difficulty options
        //have a little drop down with the difficulties
        String difficulty = (String) JOptionPane.showInputDialog(slotInfoFrame, "Select difficulty:", "Difficulty", JOptionPane.QUESTION_MESSAGE, icon2, difficultyOptions, difficultyOptions[0]);
        if (difficulty == null) return; //if the difficulty is null

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //get the date

        JFrame characterSelectFrame = new JFrame(); //initialize
        characterSelectFrame.setVisible(true); //set visible
        characterSelectFrame.setTitle("By Anna Denisova"); //title
        characterSelectFrame.setSize(WINDOWWIDTH, WINDOWHEIGHT + 370); //size
        characterSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close setting
        characterSelectFrame.setLocationRelativeTo(null); //center
        characterSelectFrame.getContentPane().setLayout(new BorderLayout()); //layout
        hideInfoFrame(); //hide the frame

        String[] characterNames = {"Wizard", "Mime", "Warrior", "Doctor", "Farmer"}; //character names
        final String[] selectedCharacter = {null}; //selected character

        JPanel characterSelectionPanel = new JPanel(); //initialize
        JEditorPane namesAndDescriptions = new JEditorPane(); //initialize
        namesAndDescriptions.setContentType("text/html"); //set type
        namesAndDescriptions.setText(getCharacterDescriptions()); //set text
        namesAndDescriptions.setEditable(false); //set editable
        characterSelectionPanel.add(namesAndDescriptions); //add to panel
        characterSelectionPanel.setPreferredSize(new Dimension(WINDOWWIDTH, WINDOWHEIGHT + 370)); //set size

        JPanel buttonPanel = new JPanel(); //initialize
        ButtonGroup group = new ButtonGroup(); //initialize
        for (String characterName : characterNames) { //for each character name
            JRadioButton button = new JRadioButton(characterName); //initialize the buttons
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedCharacter[0] = ((JRadioButton) e.getSource()).getText(); //set the selected character
                }
            });
            group.add(button); //add the button to the group so that only ONE button can be SELECTED AT ONCE
            buttonPanel.add(button); //add it to the panel
        }

        JButton confirmButton = new JButton("Confirm"); //initialize
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //on click
                if (selectedCharacter[0] != null) { //if a character is selected
                    //ask for confirmation
                    int confirmation = JOptionPane.showConfirmDialog(characterSelectFrame, "Confirm selection of " + selectedCharacter[0] + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) { //confirmed
                        slotName = name; //set the slot name
                        slotDifficulty = difficulty; //set the difficulty
                        slotCreationDate = date; //set the date
                        slotCharacter = selectedCharacter[0]; //set the character
                        //the following adds the new slot to display
                        slotListModel.addElement(String.format("%s (%s) (%s) - Created on: %s", name, difficulty, selectedCharacter[0], date));
                        writeSlotToFile(); //write to the file!!
                        showInfoFrame(); //alright
                        characterSelectFrame.setVisible(false);
                    }
                } else {
                    //ERROR MESSAGE
                    JOptionPane.showMessageDialog(characterSelectFrame, "Please select a character!");
                }
            }
        });

        buttonPanel.add(confirmButton); //add the confirm button
        characterSelectFrame.getContentPane().add(characterSelectionPanel, BorderLayout.CENTER); //add the character selection panel
        characterSelectFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH); //add the button panel
    }

    public static void deleteSlot() { //delete the slot
        if (slotName != null) { //if there is a slot
            slotListModel.clear(); //clear the list model
            slotName = null; //set the slot name to null
            slotDifficulty = null; //set the difficulty to null
            slotCreationDate = null; //set the creation date to null
            slotCharacter = null; //set the character to null
            clearSlotFile(); //clear the slot file
        } else {
            //ERROR MESSAGE:
            JOptionPane.showMessageDialog(slotInfoFrame, "Please select a slot first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void renameSlot() { //rename the slot
        if (slotName != null) { //if there is a slot
            ImageIcon icon = new ImageIcon("src/GameSlots/91_strawberrycake_dish.png");
            //POP UP:
            Object newNameObj = JOptionPane.showInputDialog(slotInfoFrame, "Enter new name for the slot", "New Slot Name", JOptionPane.QUESTION_MESSAGE, icon, null, null);
            if (newNameObj == null) return; //if the name is null
            String newName = (String) newNameObj; //set the new name
            if (newName.trim().isEmpty()) {
                //ERROR MESSAGE
                JOptionPane.showMessageDialog(slotInfoFrame, "Slot name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            slotName = newName; //set the slot name
            //the following code sets the date
            slotListModel.set(0, String.format("%s (%s) (%s) - Created on: %s", newName, slotCharacter, slotDifficulty, slotCreationDate));
            renameSlotFile(newName); //rename the slot FILE
        } else {
            //ERROR MESSAGE
            JOptionPane.showMessageDialog(slotInfoFrame, "Please select a slot first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void renameSlotFile(String newName) { //rename the slot file
        File file = new File(SAVE_FILE_PATH); //initialize
        if (!file.exists()) { //if the file doesnt exist
            //error
            System.out.println("File does not exist.");
            return;
        }

        try {
            // Read all lines from the file into a list of strings
            ArrayList<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line); //read in everything
            }
            reader.close(); //close the file

            // Modify the first line
            if (!lines.isEmpty()) {
                lines.set(0, newName);
            }

            // Write the modified content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(file)); //initialize
            for (String modifiedLine : lines) { //for each line
                writer.write(modifiedLine); //write the line
                writer.newLine(); //new line
            }
            writer.close(); //close the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeSlotToFile() { //write the slot to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE_PATH))) { //initialize
            //write the basic 4 line info:
            writer.write(slotName + "\n" + slotDifficulty + "\n" + slotCharacter + "\n" + slotCreationDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearSlotFile() { //clear the slot file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE_PATH))) {
            writer.write(""); //this clears EVERYTHING
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadSlotFromFile() { //load the slot from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE_PATH))) { //initialize
            slotName = reader.readLine(); //read the lines
            slotDifficulty = reader.readLine(); //read the lines
            slotCharacter = reader.readLine(); //read the lines
            slotCreationDate = reader.readLine(); //read the lines
            if (slotName != null) { //if there is a slot
                //add to the list
                slotListModel.addElement(String.format("%s (%s) (%s) - Created on: %s", slotName, slotDifficulty, slotCharacter, slotCreationDate));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCharacterDescriptions() {
        /* The following code is html formatted code with all the character options */
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

    /* FOR TESTING
    public static void main(String[] args) {
        new SlotInfo();
        showInfoFrame();
    }*/
}
