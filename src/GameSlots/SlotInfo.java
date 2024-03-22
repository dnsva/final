package GameSlots;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.ImageIO; //FOR IMAGES (icons)
import java.io.*; //FOR IMAGES (icons)

public class SlotInfo {

    public static String [] slotNames = new String[3];
    public static String [] slotDifficulties = new String[3];
    public static String [] slotCreationDates = new String[3];
    public static String [] slotCharacters = new String[3];
    private static String selectedCharacter = ""; //used in character selection

    public static int takenSlots = 0;

    private static JFrame slotInfoFrame = new JFrame();

   // private JTextField nameField;
    private static DefaultListModel<String> slotListModel;
    private static JList<String> slotList;

    private static int WINDOWWIDTH = 500;
    private static int WINDOWHEIGHT = 300;

    public SlotInfo() {
        slotInfoFrame.setTitle("By Anna Denisova");
        //slotInfoFrame.getContentPane().setBackground( Color.red );
       // slotInfoFrame.getContentPane().setBackground(new java.awt.Color(204, 166, 166));
        slotInfoFrame.setSize(WINDOWWIDTH, WINDOWHEIGHT);
        slotInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        slotInfoFrame.setLocationRelativeTo(null);
        slotInfoFrame.getContentPane().setLayout(new BorderLayout()); //Make the layout border

        /* 2 Panels.
        1st panel to show slots
        2nd panel to show buttons
         */

        //PANEL 1 - SLOT LIST!!!!!
        JPanel listPanel = new JPanel(new BorderLayout()); //Create the panel

        listPanel.setBorder(BorderFactory.createTitledBorder("Slots")); //Add the title
        slotListModel = new DefaultListModel<>(); //For the inside box
        slotList = new JList<>(slotListModel); //The list of things in SlotListModel
        listPanel.add(new JScrollPane(slotList));

        listPanel.setForeground(Color.red);

        slotInfoFrame.getContentPane().add(listPanel, BorderLayout.CENTER); //Add this panel to the frame
        listPanel.setBackground(Color.pink);

        //PANEL 2 - BUTTONS!!!!!!!!!
        /*
        There will be 4 buttons.
        - Select Slot
        - Add Slot
        - Delete Slot
        - Rename Slot
         */
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4)); //Create the panel

        /* This code will create the buttons */
        JButton selectButton = new JButton("Select Slot");
        JButton addButton = new JButton("Add Slot");
        JButton deleteButton = new JButton("Delete Slot");
        JButton renameButton = new JButton("Rename Slot");

        /* This code will add the buttons to the panel */
        buttonPanel.add(selectButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(renameButton);

        /* This code adds action listeners to the buttons. This is
        because the buttons are that, buttons. They get pressed and
        they need to do things. Overrides actionPerformed method from
        superclass.
         */
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { selectSlot(); } //Call selection
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //try{
                    addSlot();
               // }catch(Exception ignored){}
            } //Call addition
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { deleteSlot(); } //Call deletion
        });
        renameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { renameSlot(); } //Call renaming
        });

        slotInfoFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH); //Add this panel to the frame
    }

    public static void showInfoFrame(){
        slotInfoFrame.setVisible(true);
    }
    public static void hideInfoFrame(){
        slotInfoFrame.setVisible(false);
    }

    public static void selectSlot(){
        int selectedIndex = slotList.getSelectedIndex();
        if(selectedIndex != -1) {
            //-------
            //the name is names[selectedIndex]
        }else{
            JOptionPane.showMessageDialog(slotInfoFrame, "Please select a slot first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void addSlot(){

        //The folling code adds a new slot to the list without getting
        //input from the user. It just uses "asd" for each field for test purpsoes

        if(takenSlots < 3) {

            // get icons
            ImageIcon icon1 =  new ImageIcon("src/GameSlots/31_chocolatecake_dish.png");
            ImageIcon icon2 = new ImageIcon("src/GameSlots/23_cheesecake_dish.png");

            // Show icon1 for slot name input
            Object nameObj = JOptionPane.showInputDialog(slotInfoFrame, "Enter slot name:", "Slot Name", JOptionPane.QUESTION_MESSAGE, icon1, null, null);

            // Check for cancellation
            if (nameObj == null) {
                return; // User canceled, exit method
            }

            String name = (String) nameObj; // Cast to String

            // Handle empty input
            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(slotInfoFrame, "Slot name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Prompt the user for slot difficulty
            String[] difficultyOptions = {"Easy", "Medium", "Hard"};

            String difficulty = (String) JOptionPane.showInputDialog(slotInfoFrame, "Select difficulty:", "Difficulty", JOptionPane.QUESTION_MESSAGE, icon2, difficultyOptions, difficultyOptions[0]);

            //String difficulty = (String) JOptionPane.showInputDialog(slotInfoFrame, "Select difficulty:", "Difficulty", JOptionPane.QUESTION_MESSAGE, null, difficultyOptions, difficultyOptions[0]);
            if (difficulty == null) {
                return; // Return if user cancels
            }

            // Get the current date and time
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());



            //----------------------------------------

            JFrame characterSelectFrame = new JFrame(); //for slot creation
            //Now, character selection
            //characterSelectFrame

            characterSelectFrame.setVisible(true);
            characterSelectFrame.setTitle("By Anna Denisova");
            characterSelectFrame.setSize(WINDOWWIDTH, WINDOWHEIGHT+150);
            characterSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            characterSelectFrame.setLocationRelativeTo(null);
            characterSelectFrame.getContentPane().setLayout(new BorderLayout()); //Make the layout border

            hideInfoFrame();

            //------------------------------------------

            String[] characterNames = {"Wizard", "Mime", "Warrior", "Doctor", "Farmer"};
            selectedCharacter = null;

            JPanel panel = new JPanel();

            //panel.setSize(WINDOWWIDTH, WINDOWHEIGHT+200);

            JTextArea namesAndDescriptions = new JTextArea();
            namesAndDescriptions.setSize(WINDOWWIDTH, WINDOWHEIGHT + 130);

            String nameAndDescriptionsString = "";

            namesAndDescriptions.setEditable(false);
            //namesAndDescriptions.setOpaque(false); // Make it transparent like a label
            namesAndDescriptions.setLineWrap(true); // Enable line wrapping
            namesAndDescriptions.setFocusable(false); //nocursor
            namesAndDescriptions.setFont(new Font("Courier", Font.PLAIN, 14));
            //namesAndDescriptions.setColumns(WINDOWWIDTH);

            nameAndDescriptionsString += " -------------------------------------------------------------" +
                                        "| WIZARD                                                     |" +
                                        "| - Start game with [curse/potion name]                      |" +
                                        "| - 25% off all monster curses                               |" +
                                        "| - Default attack power: 10                                 |" +
                                        "| MIME                                                       |" +
                                        "| - Useless and weak                                         |" +
                                        "| - Gets 1 free apple                                        |" +
                                        "| - Default attack power: 1                                  |" +
                                        "| WARRIOR                                                    |" +
                                        "| - Start game with [weapon name]                            |" +
                                        "| - 25% off all weapons                                      |" +
                                        "| - Default attack power: 15                                 |" +
                                        "| DOCTOR                                                     |" +
                                        "| - Start game with [healing potion]                         |" +
                                        "| - 25% off all healing potions                              |" +
                                        "| - Default attack power: 10                                 |" +
                                        "| FARMER                                                     |" +
                                        "| - Can't fight                                              |" +
                                        "| - Start game with all the food                             |" +
                                        "| - Default attack power: 0                                  |" +
                                        " -------------------------------------------------------------";


            namesAndDescriptions.setText(nameAndDescriptionsString);
            namesAndDescriptions.setBackground(Color.pink);

            panel.add(namesAndDescriptions);


            //for (int i = 0; i < characterNames.length; i++) {
                /*JLabel nameLabel = new JLabel(characterNames[i], JLabel.CENTER);
                nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));
                panel.add(nameLabel);
                */
                //JTextArea nameArea = new JTextArea(characterNames[i]);
               // nameArea.setAlignmentX(Component.CENTER_ALIGNMENT);
                //nameArea.setFont(nameArea.getFont().deriveFont(Font.BOLD));
                //panel.add(nameArea);

               // JTextArea descriptionArea = new JTextArea(descriptions[i]);
                //descriptionArea.setLineWrap(true);
               // descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);
               // panel.add(descriptionArea);

                /*
                JLabel descriptionLabel = new JLabel(descriptions[i]);
                descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(descriptionLabel);
                */


                //JLabel descriptionLabel2 = new JLabel("sdfasdfasdfasdf");
                //descriptionLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
                //panel.add(descriptionLabel2);
            //}

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
                                characterSelectFrame,
                                "Confirm selection of " + selectedCharacter + "?",
                                "Confirmation",
                                JOptionPane.YES_NO_OPTION
                        );
                        if (confirmation == JOptionPane.YES_OPTION) {

                            //add to global arrays
                            takenSlots++;
                            slotNames[takenSlots-1] = name;
                            slotDifficulties[takenSlots-1] = difficulty;
                            slotCreationDates[takenSlots-1] = date;
                            slotCharacters[takenSlots-1] = selectedCharacter; //add to the array

                            // Add the slot information to the list
                            String slotDetails = String.format("%s (%s) (%s) - Created on: %s", name, difficulty, selectedCharacter, date);
                            slotListModel.addElement(slotDetails);

                            showInfoFrame();
                            characterSelectFrame.setVisible(false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(characterSelectFrame, "Please select a character!");
                    }
                }
            });

            buttonPanel.add(confirmButton);

            characterSelectFrame.getContentPane().add(panel, BorderLayout.CENTER);
            characterSelectFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
            //characterSelectFrame.pack();






            //showInfoFrame();
            //characterSelectFrame.setVisible(false);

            //System.out.println("Selected character: " + selectedCharacter);

        }else{
            JOptionPane.showMessageDialog(slotInfoFrame, "You can only have 3 slots", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   // private static String getRemainingWidthSpaces(int textWidth){
        //String spaces = "";
        //for(int i = 0; i < WINDOWWIDTH - textWidth; ++i){
           //spaces += " ";
        //}
       // return spaces;
    //}
    public static void deleteSlot(){
        int selectedIndex = slotList.getSelectedIndex();
        if(selectedIndex != -1) {
            slotListModel.remove(selectedIndex);
            takenSlots--;
        }else{
            JOptionPane.showMessageDialog(slotInfoFrame, "Please select a slot first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void renameSlot(){

        int selectedIndex = slotList.getSelectedIndex();

        if(selectedIndex != -1) {

            String difficulty = slotDifficulties[selectedIndex]; //get from array
            String date = slotCreationDates[selectedIndex]; //get from array
            ImageIcon icon =  new ImageIcon("src/GameSlots/91_strawberrycake_dish.png");
            String character = slotCharacters[selectedIndex]; //get from array

            Object newNameObj = JOptionPane.showInputDialog(slotInfoFrame,
                    "Enter new name for the slot",
                    "New Slot Name", // Title
                    JOptionPane.QUESTION_MESSAGE,
                    icon, null, null);

            // Check for cancellation
            if (newNameObj == null) {
                return; // User canceled, exit method
            }
            String newName = (String) newNameObj; //Cast to strinig
            // Handle empty input
            if (newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(slotInfoFrame, "Slot name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(newName != null){
                slotListModel.set(selectedIndex, String.format("%s (%s) (%s) - Created on: %s", newName, character, difficulty, date));
            }
        }else{
            JOptionPane.showMessageDialog(slotInfoFrame, "Please select a slot first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args){
        new SlotInfo();
        showInfoFrame();
      // hideInfoFrame();
    }

}
