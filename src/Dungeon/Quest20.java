package Dungeon;

//IMPORT ALL PACKAGES -----
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Game.*;
import AnnaTools.*;

import static Dungeon.Map.dealWithMapLevelCompletion;
import static Game.GameOver.checkGameOver;
//-------------------------

public class Quest20 {
    public static JFrame quest20Frame = new JFrame(); //The main frame including the game and the sidebar
    public static SideBar quest20SideBar = new SideBar(); //Specific quest 20 sidebar
    public static boolean complete = false; //Marker

    public static JPanel currentPanel; //The main panel
    public static int currentQuestion = 0; //The current question
    public static int sanityLossPerIncorrect = 20; //The sanity loss per incorrect answer
    public static int rewardPerCorrect = 20; //The reward per correct answer MONEY

    public Quest20() {

       // new Fonts(); //ONLY FOR TSETING

        quest20Frame = new JFrame("By Anna Denisova"); //The main frame
        quest20Frame.setSize(GameVars.WINDOWWIDTH + GameVars.SIDEBARWIDTH, GameVars.WINDOWHEIGHT); //The size of the frame
        quest20Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //The close operation
        quest20Frame.setLocationRelativeTo(null); //The location of the frame make it centered
        quest20Frame.getContentPane().setLayout(new BorderLayout()); //north east south west center etc layout type

        currentPanel = new JPanel(); //initialize
        quest20SideBar = new SideBar(); //initialize
        quest20Frame.add(quest20SideBar.getPanel(), BorderLayout.EAST); //move it to the side
        quest20Frame.add(currentPanel, BorderLayout.CENTER); //make this on the other side

        teacherIntroduction(); //THIS STARTS THE QUEST OFF
    }

    public static void showQuest20() { //shows the frame
        quest20Frame.setVisible(true); //show the frame
    }

    public static void hideQuest20() { //hides the frame
        dealWithMapLevelCompletion(20); //deals with the map level completion. This function is found in the map class
        quest20Frame.setVisible(false); //hides the frame
        MapGUI.showMapGUI(); //brings back the map gui
    }

    public static void teacherIntroduction() { //the first "window" encountered
        currentPanel.removeAll(); //clear everything off
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set the size
        currentPanel.setBackground(Color.decode("#FF0000")); //make it RED

        JLabel title = new JLabel("A VERY"); //title
        /* The following 4 lines set the font,
        alignment, and size of the title
        They also make the background a hex code.
         */
        title.setFont(Fonts.Corrupted_File);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBackground(Color.decode("#FF0000"));

        JLabel title2 = new JLabel("PREDICTABLE"); //title after the first title
        /* The following 4 lines set the font,
        alignment, and size of the title
        They also make the background a hex code.
         */
        title2.setFont(Fonts.Corrupted_File);
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);
        title2.setBackground(Color.decode("#FF0000"));

        JLabel title3 = new JLabel("TEACHER"); //title after the second title
        /* The following 4 lines set the font,
        alignment, and size of the title
        They also make the background a hex code.
         */
        title3.setFont(Fonts.Corrupted_File);
        title3.setHorizontalAlignment(SwingConstants.CENTER);
        title3.setAlignmentX(Component.CENTER_ALIGNMENT);
        title3.setBackground(Color.decode("#FF0000"));

        JPanel titlePanel = new JPanel(new GridLayout(3, 1)); //3 rows, 1 col
        titlePanel.add(title); //row 1
        titlePanel.add(title2); //row 2
        titlePanel.add(title3); //row 3
        titlePanel.setBackground(Color.decode("#FF0000")); //red background of course
        titlePanel.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3)); //SET THE SIZE

        JEditorPane introText = new JEditorPane(); //This is the monologue
        introText.setOpaque(true); //for color visibility
        introText.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000"), 5)); //red outline
        introText.setBackground(Color.white); //white background
        introText.setEditable(false); //not editable
        introText.setFocusable(false); //not clickable
        introText.setContentType("text/html"); //for html formatting
        introText.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3 + 100); //set the size
        introText.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT / 3 + 100)); //set the size
        introText.setMargin(new Insets(5, 5, 5, 5)); //so that there is space around the text
        /* The following code outlines the text in html formatting */
        introText.setText(
                "<html><body style='font-size: 15px; padding: 10px; font-family: Arial'>" +
                        "<b>Teacher:</b> If you don’t answer all these questions I'm about to give you, your life will be ruined," +
                        "your reputation will be destroyed, and you will have no future. To proceed you must answer all of them." +
                        " You will get get some cash for every question you answer correctly. For every incorrect answer, you might go insane." +
                        "</body></html>"
        );

        JButton okButton = new JButton("OK"); //for moving on
        okButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50)); //set the size
        okButton.addActionListener(new ActionListener() { //when the OK button is pressed...
            public void actionPerformed(ActionEvent e) { //on button press... (this is an override)
                nextQuestion(); //GO TO THE NEXT QUESTION!!!!!!
            }
        });

        currentPanel.setLayout(new GridLayout(3, 1)); //3 rows, 1 col
        currentPanel.add(titlePanel); //add the title panel
        currentPanel.add(introText); //add the monologue
        currentPanel.add(okButton); //add the OK button

        currentPanel.revalidate(); //refresh the panel
        currentPanel.repaint(); //refresh the panel
    }

    public static void nextQuestion() { //THIS ALLOWS YOU TO MOVE ON
        switch (currentQuestion) { //depending on the current question, move forward
            case 0: question1(); break; //if q1 go to q2
            case 1: question2(); break; //if q2 go to q3
            case 2: question3(); break; //if q3 go to q4
            case 3: question4(); break; //if q4 go to q5
            case 4: question5(); break; //if q5 go to reward
            default: showRewardScreen(); break; //if no more questions, show the reward screen
        }
        currentQuestion++; //UPDATE
    }

    public static void question1() { //QUESTION 1
        /* specific requirements for question passed into function: */
        askQuestion("\nNUMBER 1", "1 + 1 =", 10, "It was binary and the answer was 10.");
    }

    public static void question2() { //QUESTION 2
        /* specific requirements for question passed into function: */
        askQuestion("NUMBER 2", "101 + 1 =", 110, "It was binary again and the answer was 110.");
    }

    public static void question3() { //QUESTION 3
        /* specific requirements for question passed into function: */
        askQuestion("NUMBER 3", "101 + 11 =", 1000, "It was binary AGAIN and the answer was 1000.");
    }

    public static void question4() { //QUESTION 4
        /* specific requirements for question passed into function: */
        askQuestion("NUMBER 4", "10 + 10 =", 20, "20. Did you think it would always be binary?");
    }

    public static void question5() { //QUESTION 5
        /* specific requirements for question passed into function: */
        askQuestion("NUMBER 5", "123456 + 789 =", 124245, "124245!");
    }

    public static void askQuestion(String prompt, String equation, int correctAnswer, String incorrectMessage) { //make a question based off 4 params
        currentPanel.removeAll(); //clear everything off
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set the size
        currentPanel.setBackground(Color.decode("#A3BCF9")); //set the background color BLUE

        JLabel questionTitle = new JLabel(prompt); //the prompt
        questionTitle.setFont(Fonts.Corrupted_File); //custom font for quest 20
        questionTitle.setHorizontalAlignment(SwingConstants.CENTER); //center the text
        questionTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //center the text

        JLabel equationLabel = new JLabel(equation); //the equation
        equationLabel.setFont(new Font("Arial", Font.PLAIN, 20)); //set the font. Make it normal not custom
        equationLabel.setBackground(Color.decode("#A3BCF9")); //set the background color (blue)
        equationLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //center the text
        equationLabel.setHorizontalAlignment(SwingConstants.CENTER); //center the text

        JTextField answerField = new JTextField(); //THIS IS WHERE THE USER ENTERS THEIR ANSWER
        answerField.setHorizontalAlignment(SwingConstants.CENTER); //center the text
        answerField.setFont(new Font("Arial", Font.PLAIN, 20)); //set the font. Make it normal not custom

        JLabel feedbackLabel = new JLabel(""); //this is the feedback label with the CORRECT ANSDWER
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 20)); //set the font. Make it normal not custom
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER); //center the text
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //center the text

        JButton enterButton = new JButton("Enter"); //this is the enter button for entering question answers
        JButton nextButton = new JButton("Next"); //this is the next button to get the next quesiton
        nextButton.setEnabled(false); //disable the next button until the user enters an answer
        enterButton.setEnabled(true); //enable the enter button to start
        enterButton.addActionListener(new ActionListener() { //WHEN ENTER IS PRESSED
            public void actionPerformed(ActionEvent e) { //override
                try { //try and catch so that i dont have to throw anything
                    int userAnswer = Integer.parseInt(answerField.getText().trim()); //get the user answer
                    if (userAnswer == correctAnswer) { //if the user answer is correct
                        GameVars.balance += rewardPerCorrect; //add the reward to the balance
                        feedbackLabel.setText("Correct! You get $" + rewardPerCorrect); //give feedback
                        enterButton.setEnabled(false); //disable the enter button
                        nextButton.setEnabled(true); //enable the next button
                    } else {
                        GameVars.sanity -= sanityLossPerIncorrect; //subtract the sanity
                        feedbackLabel.setText(incorrectMessage + " [-" + sanityLossPerIncorrect + " sanity]"); //give feedback
                        enterButton.setEnabled(false); //disable the enter button
                        nextButton.setEnabled(true); //enable the next button
                        checkGameOver(); //check if globally youre dead
                    }
                } catch (NumberFormatException ex) { //if the user enters something that is not a number
                    feedbackLabel.setText("Please enter a valid number."); //give feedback
                }
                AnnaTools.Updater.updateAllSidePanels(); //update all user stats at the side, you know how it goes.

                nextButton.addActionListener(new ActionListener() { //WHEN NEXT IS PRESSED
                    public void actionPerformed(ActionEvent e) { //override
                        nextQuestion(); //GO TO THE NEXT QUESTION
                    }
                });

            }
        });

        currentPanel.setLayout(new GridLayout(6,  1)); //6 rows, 1 col
        currentPanel.add(questionTitle); //add the question title
       // currentPanel.add(new JLabel("")); // Placeholder for spacing. Nevermind
        currentPanel.add(equationLabel); //add the equation
        currentPanel.add(answerField); //add the answer field
        currentPanel.add(feedbackLabel); //add the feedback label
        currentPanel.add(enterButton); //add the enter button
        currentPanel.add(nextButton); //add the next button

        currentPanel.revalidate(); //refresh the panel
        currentPanel.repaint(); //refresh the panel
    }

    public static void showRewardScreen() { //SHOW THE REWARD SCREEN
        currentPanel.removeAll(); //clear everything off
        currentPanel.setSize(GameVars.WINDOWWIDTH, GameVars.WINDOWHEIGHT); //set the size
        currentPanel.setBackground(Color.decode("#A3BCF9")); //set the background color BLUE

        JLabel rewardTitle = new JLabel("REWARD"); //the reward title
        rewardTitle.setFont(Fonts.Corrupted_File); //custom font for quest 20
        rewardTitle.setHorizontalAlignment(SwingConstants.CENTER); //center the text
        rewardTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //center the text

        JEditorPane rewardText = new JEditorPane(); //the reward text
        rewardText.setOpaque(true); //for color visibility
        rewardText.setBorder(BorderFactory.createLineBorder(Color.decode("#A3BCF9"), 5)); //blue outline
        rewardText.setBackground(Color.white); //white background
        rewardText.setEditable(false); //not editable
        rewardText.setFocusable(false); //not clickable
        rewardText.setContentType("text/html"); //for html formatting
        rewardText.setMargin(new Insets(5, 5, 5, 5)); //so that there is space around the text
        /* The following code outlines the text in html formatting */
        rewardText.setText(
                "<html><body style='font-size: 20px; padding: 10px; font-family: Arial'>" +
                        "You have completed the quiz! Your game is saved." +
                        "</body></html>"
        );

        JButton exitButton = new JButton("Exit"); //the exit button
        exitButton.setPreferredSize(new Dimension(GameVars.WINDOWWIDTH, 50)); //set the size
        exitButton.addActionListener(new ActionListener() { //WHEN THE EXIT BUTTON IS PRESSED
            public void actionPerformed(ActionEvent e) { //override
                hideQuest20(); //hide and go back to map
            }
        });

        complete = true; //MARKER, GAME COMPLETED
        currentPanel.setLayout(new GridLayout(3, 1)); //3 rows, 1 col
        currentPanel.add(rewardTitle); //add the reward title
        currentPanel.add(rewardText); //add the reward text
        currentPanel.add(exitButton); //add the exit button

        currentPanel.revalidate(); //refresh the panel
        currentPanel.repaint(); //refresh the panel
    }

    /* TESTING
    public static void main(String[] args) {
        Quest20 quest20 = new Quest20();
        quest20.showQuest20();
    }*/
}
