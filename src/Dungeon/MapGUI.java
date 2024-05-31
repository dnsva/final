package Dungeon;

//IMPORT ALL PACKAGES -----
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;      //-
import javax.swing.*;   //-
import Game.*;          //-
//-------------------------

public class MapGUI {

    private static JFrame mapFrame; //the frame with the ascii display AND sidebar
    public static SideBar mapGUISideBar = new SideBar(); //local sidebar
    boolean exitFlag; //this is used to check if the exit button has been pressed
    public static JEditorPane mapEditorPane, mapLegendEditorPane, mapTitleEditorPane; //the different displays

    public MapGUI() {

        mapFrame = new JFrame(); //initialize
        mapFrame.setTitle("By Anna Denisova"); //set title
        mapFrame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT); //set size
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close setting
        mapFrame.setLocationRelativeTo(null); //center
        mapFrame.getContentPane().setLayout(new BorderLayout()); //make layout border
        mapFrame.setBackground(Color.black); //set background all black
        mapFrame.getContentPane().setBackground(Color.black); //set background all black

        JPanel mapAsciiPanel = new JPanel(); //new panel for all the ascii stuff
        mapAsciiPanel.setBackground(Color.black); //again, black background
        mapAsciiPanel.setLayout(new BoxLayout(mapAsciiPanel, BoxLayout.Y_AXIS)); //make everything go after the previous thing. vertical layout
        //mapAsciiPanel.setSize(600, GameVars.WINDOWHEIGHT);

       // JLabel titleLabel = new JLabel("Map");
       // titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      //  titleLabel.setForeground(Color.white);

      //  mapAsciiPanel.add(Box.createVerticalStrut(20)); //add space ebtween
      //  mapAsciiPanel.add(titleLabel);
      //  mapAsciiPanel.add(Box.createVerticalStrut(20)); //add space ebtween
      //  mapAsciiPanel.setBackground(Color.blue);

        mapEditorPane = new JEditorPane(); //initiallize
        mapEditorPane.setContentType("text/html"); //make format be html
        mapEditorPane.setText(Map.getMapAsciiString()); //get the ascii map string from the map class
        mapEditorPane.setEditable(false); //cannot be editable
        mapEditorPane.setBackground(Color.black); //set background black as always

        mapLegendEditorPane = new JEditorPane(); //initialize
        mapLegendEditorPane.setContentType("text/html"); //make the format be html here too
        mapLegendEditorPane.setText(Map.getMapLegend()); //use map function again
        mapLegendEditorPane.setEditable(false); //cannot be editable
        mapLegendEditorPane.setBackground(Color.black); //set background black as always again

        mapTitleEditorPane = new JEditorPane(); //intialize
        mapTitleEditorPane.setContentType("text/html"); //make format be html...
        mapTitleEditorPane.setText(Map.getMapTitle()); //use map class functin to get the ascii art
        mapTitleEditorPane.setEditable(false); //cannot be editable
        mapTitleEditorPane.setBackground(Color.black); //set panel background to be black

        mapAsciiPanel.add(mapTitleEditorPane);
        mapAsciiPanel.add(mapEditorPane);
        mapAsciiPanel.add(mapLegendEditorPane);

        JButton exitButton = new JButton("Exit");

        /*
        AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

        DO THIS
        JButton b=new JButton(new ImageIcon("D:\\icon.png"));
         */
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.HomeVillage.showHomeVillage();
                hideMapGUI();

            }
        });

        JButton upButton = new JButton("Up");
        upButton.setSize(100, 100);
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.moveUp();
                updateMapGUIAscii();
            }
        });

        JButton downButton = new JButton("Down");
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.moveDown();
                updateMapGUIAscii();
            }
        });

        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.moveLeft();
                updateMapGUIAscii();
            }
        });

        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.moveRight();
                updateMapGUIAscii();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBackground(Color.black);

        buttonPanel.add(leftButton);

        JPanel buttonPanelUpDown = new JPanel(new GridLayout(2, 1));
        buttonPanelUpDown.setBackground(Color.black);
        buttonPanelUpDown.add(upButton);
        buttonPanelUpDown.add(downButton);

        buttonPanel.add(buttonPanelUpDown);

        //rightButton.setSize(100, 100);
        buttonPanel.add(rightButton);

       // buttonPanel.add(exitButton);

        //---------------------

        mapAsciiPanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT));

        mapAsciiPanel.add(buttonPanel);

        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

      //  JPanel exitAndHelpPanel = new JPanel();

      //  mapAsciiPanel.add(exitButton);

        JPanel exitAndHelpPanel = new JPanel(new GridLayout(2, 1));

        JButton helpButton = new JButton("Help");
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //create a pop up that says:
                /*
                Beat the final boss to win the game. Use the shops to buy items and equip armour and weapons from the inventory.
                If your health or sanity reaches 0, or your hunger reaches 100, you will die and become a ghost.
                 */
                JOptionPane.showMessageDialog(null, "Use the buttons to move.");
            }
        });
        //mapAsciiPanel.add(helpButton);
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        helpButton.setHorizontalAlignment(SwingConstants.CENTER);

        exitAndHelpPanel.add(exitButton);
        exitAndHelpPanel.add(helpButton);

        exitAndHelpPanel.setBackground(Color.black);

        mapAsciiPanel.add(exitAndHelpPanel);


        mapFrame.add(mapAsciiPanel, BorderLayout.WEST);


        //make the sidebar labels white for this one
        mapGUISideBar.healthLabel.setForeground(Color.white);
        mapGUISideBar.sanityLabel.setForeground(Color.white);
        mapGUISideBar.hungerLabel.setForeground(Color.white);
        mapGUISideBar.balanceLabel.setForeground(Color.white);
        mapGUISideBar.weaponLabel.setForeground(Color.white);
        mapGUISideBar.armourLabel.setForeground(Color.white);
        mapGUISideBar.attackLabel.setForeground(Color.white);
        mapGUISideBar.defenseLabel.setForeground(Color.white);
        mapGUISideBar.dayLabel.setForeground(Color.white);



        mapGUISideBar.setColor("#000000");
        mapGUISideBar.updatePanel();

        mapFrame.add(mapGUISideBar.getPanel(), BorderLayout.EAST);

        mapFrame.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT));
        //mapFrame.pack();
    }

    public static void showMapGUI(){
        mapFrame.setVisible(true);
    }

    public static void hideMapGUI(){

        mapFrame.setVisible(false);

    }

    public static void updateMapGUIAscii(){
        mapEditorPane.setText(Map.getMapAsciiString());
    }
    public static void main(String[] args){
        //new GameSlots.SlotInfo();

        // new HomeVillage();
        //new SideBar();
        // showHomeVillage();

        new MapGUI();
        //Map m = new Map();
        //m.moveRight();
        //m.moveRight();
        //m.moveRight();
        //m.moveRight();
        updateMapGUIAscii();
        showMapGUI();

       // Map.quest10Object.showQuest10();

        //while(true){
          //  m.moveRight();
            //updateMapGUIAscii();
        //}
    }

}
