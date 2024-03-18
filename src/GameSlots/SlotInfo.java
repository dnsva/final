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

    public static int takenSlots = 0;

    private static JFrame slotInfoFrame = new JFrame();
    private static JFrame slotAddFrame = new JFrame(); //for slot creation

   // private JTextField nameField;
    private static DefaultListModel<String> slotListModel;
    private static JList<String> slotList;

    private static int WINDOWWIDTH = 500;
    private static int WINDOWHEIGHT = 300;

    public static void CreateInfoFrame() {
        slotInfoFrame.setTitle("By Anna Denisova");
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

        slotInfoFrame.getContentPane().add(listPanel, BorderLayout.CENTER); //Add this panel to the frame

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

    public static void ShowInfoFrame(){
        slotInfoFrame.setVisible(true);
    }
    public static void HideInfoFrame(){
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

        // Load JPEG image
        /*
        File file = new File("23_cheescake_dish.png");
        Image img = ImageIO.read(file);
        // Convert image to ImageIcon
        ImageIcon icon1 = new ImageIcon(img);
        file = new File("31_chocolate_cake_dish.png");
        img = ImageIO.read(file);
        ImageIcon icon2 = new ImageIcon(img);
        */

        /*
        ImageIcon icon = null, icon2 = null;
        try {
            icon = new ImageIcon(ImageIO.read(new File("23_cheescake_dish.png")));
            icon2 = new ImageIcon(ImageIO.read(new File("31_chocolatecake_dish.png")));
        }catch(Exception e){
            //i dont care
        }
        */

        //The folling code adds a new slot to the list without getting
        //input from the user. It just uses "asd" for each field for test purpsoes

        if(takenSlots < 3) {
            // Prompt the user for slot name
            String name = JOptionPane.showInputDialog(slotInfoFrame, "Enter slot name:");
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(slotInfoFrame, "Slot name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Return if slot name is empty or user cancels
            }

            // Prompt the user for slot difficulty
            String[] difficultyOptions = {"Easy", "Medium", "Hard"};
            String difficulty = (String) JOptionPane.showInputDialog(slotInfoFrame, "Select difficulty:", "Difficulty", JOptionPane.QUESTION_MESSAGE, null, difficultyOptions, difficultyOptions[0]);
            if (difficulty == null) {
                return; // Return if user cancels
            }

            // Get the current date and time
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            //add to global arrays
            takenSlots++;
            slotNames[takenSlots-1] = name;
            slotDifficulties[takenSlots-1] = difficulty;
            slotCreationDates[takenSlots-1] = date;

            // Add the slot information to the list
            String slotDetails = String.format("%s (%s) - Created on: %s", name, difficulty, date);
            slotListModel.addElement(slotDetails);

            /*
            TESTS
            takenSlots++;

            String name = "asd";
            String difficulty = "asd";
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            slotNames[takenSlots-1] = name;
            slotDifficulties[takenSlots-1] = difficulty;
            slotCreationDates[takenSlots-1] = date;

            String slotDetails = String.format("%s (%s) - Created on: %s", name, difficulty, date);
            slotListModel.addElement(slotDetails);
            */
        }else{
            JOptionPane.showMessageDialog(slotInfoFrame, "You can only have 3 slots", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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

            String newName = JOptionPane.showInputDialog(slotInfoFrame, "Enter new name for the slot");
            if(newName != null){
                slotListModel.set(selectedIndex, String.format("%s (%s) - Created on: %s", newName, difficulty, date));
            }
        }else{
            JOptionPane.showMessageDialog(slotInfoFrame, "Please select a slot first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args){
        CreateInfoFrame();
        ShowInfoFrame();
    }

}
