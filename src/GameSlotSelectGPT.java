import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
public class GameSlotSelectGPT extends JFrame {
    private JTextField nameField;
    private JComboBox<String> difficultyComboBox;
    private DefaultListModel<String> slotListModel;
    private JList<String> slotList;

    public GameSlotSelectGPT() {
        setTitle("Game Slot Selection");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel for slot details --------
        JPanel detailsPanel = new JPanel(new GridLayout(3, 2));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Slot Details"));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel difficultyLabel = new JLabel("Difficulty:");
        String[] difficulties = {"Easy", "Medium", "Hard"};
        difficultyComboBox = new JComboBox<>(difficulties);
        JButton addButton = new JButton("Add Slot");

        detailsPanel.add(nameLabel);
        detailsPanel.add(nameField);
        detailsPanel.add(difficultyLabel);
        detailsPanel.add(difficultyComboBox);
        detailsPanel.add(new JLabel()); // Placeholder for alignment
        detailsPanel.add(addButton);
        //------------------

        // Panel for slot list
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Slots"));

        slotListModel = new DefaultListModel<>();
        slotList = new JList<>(slotListModel);
        JScrollPane scrollPane = new JScrollPane(slotList);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        //--------------

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        JButton deleteButton = new JButton("Delete Slot");
        JButton renameButton = new JButton("Rename Slot");

        buttonPanel.add(deleteButton);
        buttonPanel.add(renameButton);

        // Add panels to frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(detailsPanel, BorderLayout.NORTH);
        getContentPane().add(listPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSlot();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSlot();
            }
        });

        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renameSlot();
            }
        });
    }

    private void addSlot() {
        String name = nameField.getText();
        String difficulty = (String) difficultyComboBox.getSelectedItem();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        if (!name.isEmpty()) {


            String slotDetails = String.format("%s (%s) - Created on: %s", name, difficulty, date);
            slotListModel.addElement(slotDetails);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a name for the slot", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSlot() {
        int selectedIndex = slotList.getSelectedIndex();
        if (selectedIndex != -1) {
            slotListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a slot to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void renameSlot() {
        int selectedIndex = slotList.getSelectedIndex();
        if (selectedIndex != -1) {
            String newName = JOptionPane.showInputDialog(this, "Enter new name:");
            if (newName != null && !newName.isEmpty()) {
                String slotDetails = slotListModel.getElementAt(selectedIndex);
                String[] detailsArray = slotDetails.split(" \\(");
                slotListModel.setElementAt(newName + " (" + detailsArray[1], selectedIndex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a slot to rename", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        difficultyComboBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameSlotSelectGPT().setVisible(true);
            }
        });
    }

}
