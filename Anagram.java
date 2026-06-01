
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.InputMismatchException;

import javax.swing.border.EmptyBorder;
import java.util.function.Supplier;
import java.util.LinkedHashMap;
import javax.swing.*;
public class Anagram extends JFrame{

    private int[] firstWordArray = new int[26];
    private int[] secondWordArray = new int[26];
    private String caseWordOne = "";
    private String caseWordTwo = "";
    private String userWordOne = "";
    private String userWordTwo = "";

    private JFrame frame = new JFrame();
    private static final Random RAND = new Random();

    private final int X = 53;
    private final int Y = 11;
    private final int WIDTH = 392;
    private final int HEIGHT = 42;

    private int result;

    private JButton submitButton;
    private JButton resetButton;
    private JButton exitButton;

    private JTextField wordOneTextField;
    private JTextField wordTwoTextField;
    private JLabel wordOneLabel;
    private JLabel wordTwoLabel;
    private JLabel resultLabelText;
    private JLabel errorMessagesLabelText;
    private JLabel headerLabel;
    private JLabel resultLabel;
    private JLabel errorMessageLabel;

    private Color[] colors = new Color[]{Color.RED,Color.black,Color.green,Color.blue};

    public Anagram(){
        getContentPane().setLayout(null);
        headerLabel = new JLabel("ANAGRAM CHECKER");
        headerLabel.setFont(new Font("Tahoma",Font.BOLD,16));
        result = getRandomColor();
        headerLabel.setForeground(setColor());
        headerLabel.setOpaque(true);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBackground(new Color(0, 128, 255));
        headerLabel.setBounds(X,Y,WIDTH,HEIGHT);

        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");

        wordOneLabel = new JLabel("Word One:");
        wordOneLabel.setBounds(93,79,73,14);
        wordOneLabel.setForeground(Color.red);
        
        wordTwoLabel = new JLabel("Word Two:");
        wordTwoLabel.setBounds(93,128,73,14);
        wordTwoLabel.setForeground(Color.red);

        resultLabelText = new JLabel("Result:");
        resultLabelText.setBounds(21,198,79,14);
        resultLabelText.setForeground(Color.red);

        errorMessagesLabelText = new JLabel("Error Messages:");
        errorMessagesLabelText.setBounds(21,237,101,14);
        errorMessagesLabelText.setForeground(Color.red);
        
        resultLabel = new JLabel("result go here");
        resultLabel.setBounds(21,212,566,14);

        errorMessageLabel = new JLabel("error messages go here");
        errorMessageLabel.setBounds(21,250,566,14);

        wordOneTextField = new JTextField();
        wordOneTextField.setBounds(179,76,188,20);

        wordTwoTextField = new JTextField();
        wordTwoTextField.setBounds(179,125,188,20);
        
        submitButton.setBounds(179,156,89,23);
        resetButton.setBounds(278,156,89,23);
        exitButton.setBounds(377,156,89,23);

        getContentPane().setBackground(Color.yellow);
        add(headerLabel);
        add(submitButton);
        add(resetButton);
        add(exitButton);
        add(wordOneTextField);
        add(wordTwoTextField);
        add(wordOneLabel);
        add(wordTwoLabel);
        add(resultLabelText);
        add(errorMessagesLabelText);
        add(resultLabel);
        add(errorMessageLabel);

        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               submitWordButton(); 
            }
        });

        resetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                reset();
            }
        });

        exitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

            }
        });

    }

    private void submitWordButton(){
        userWordOne = wordOneTextField.getText();
        userWordTwo = wordTwoTextField.getText();
        boolean isValid = isExit();
        if(!isValid){
            JOptionPane.showMessageDialog(frame, "Exiting the program......");
            dispose();
        }

        if(isInputEmpty(wordOneTextField).get() || isInputEmpty(wordTwoTextField).get()){
            errorMessages(userWordOne,userWordTwo);
        }

        boolean hasErrors = isLengthOfWordsMismatch(wordOneTextField,wordTwoTextField).get();
        if(hasErrors){
            errorMessages(userWordOne,userWordTwo);
        }
        caseWordOne = checkCaseOfWord(userWordOne);
        caseWordTwo = checkCaseOfWord(userWordTwo);
        processWordsAndArrays(firstWordArray,secondWordArray,caseWordOne,caseWordTwo);
        checkWinner(userWordOne,userWordTwo,firstWordArray,secondWordArray);
    }

    private void checkWinner(String wordOne,String wordTwo,int[] wordOneArray,int[] wordTwoArray){

    }

    private void errorMessages(String wordOne,String wordTwo){

    }

    private String getGameMessage(String word,String wordOne,String wordTwo){
        return "";
    }

    private String setGameMessage(JLabel label,String message){
        return "";
    }

    private Supplier<Boolean> isInputEmpty(JTextField textField){
        return ()->{
            return true;
        };
    }

    private Supplier<Boolean> isLengthOfWordsMismatch(JTextField jTextField,JTextField jTextFieldTwo){
        return ()->{
            return true;
        };
    }

    private Supplier<Boolean>isAnagram(int[]firstWordArray,int[] secondWordArray,String caseWordOne,String caseWordTwo){
        return ()->{
            return true;
        };
    }

    private void processWordsAndArrays(int[] firstWordArray,int[] secondWordArray,String caseWordOne,String caseWordTwo){
        
    }

    private void countCharacters(String word,int[]count){

    }

    private void resetArrays(int[] numArrays){

    }

    private String checkCaseOfWord(String words){
        return "";
    }

    private String convertToLowerCase(String input){
        return "";
    }

    private void reset(){
        
    }

    private void exitButton(){

    }

    private boolean isExit(){
        return !userWordOne.equalsIgnoreCase("quit");
    }

    private int getRandomColor(){
        return RAND.nextInt(colors.length);
    }

    private Color setColor(){
        switch(result){
            case 0:
                return colors[0];
            case 1: 
                return colors[1];
            case 3:
                return colors[3];
            default:
                return colors[0];
        }
    }
    
}
