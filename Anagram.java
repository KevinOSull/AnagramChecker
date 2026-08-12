
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.border.EmptyBorder;
import java.util.function.Supplier;
import java.util.LinkedHashMap;
import javax.swing.*;
import javax.swing.Timer;
public class Anagram extends JFrame{
    private HashMap<String,String> gameMessages = new HashMap<>();
    private static final File TEXT_FILE = new File("gamemessages.txt");
    private int[] firstWordArray = new int[26];
    private int[] secondWordArray = new int[26];
    private String caseWordOne = "";
    private String caseWordTwo = "";
    private String userWordOne = "";
    private String userWordTwo = "";

    private JFrame frame = new JFrame();
    private static final Random RAND = new Random();
    private static final File file = new File("words.txt");
    private static Set<String> fileWords = new HashSet<>();

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

    private Timer errorMessageTimer;
    private Timer resultMessageTimer;

    private Color[] colors = new Color[]{Color.RED,Color.black,Color.green,Color.blue};

    public Anagram(){
        loadGameMessagesFile();
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
        
        resultLabel = new JLabel("");
        resultLabel.setBounds(21,212,566,14);

        errorMessageLabel = new JLabel("");
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
                exitButton();
            }
        });

    }

    private Timer displayOutputTimer(int delay,Runnable taskToRun){
        Timer timer = new Timer(delay, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Timer timeInMotion = (Timer)e.getSource();
                timeInMotion.stop();
                taskToRun.run();
            }
        });
        timer.setRepeats(false);
        timer.start();
        return timer;
    }

    private void stopTimer(Timer timer){
        if(timer != null){
            timer.stop();
        }
    }

    private void submitWordButton(){
        readTextFile();
        filterOutBadData();
        startGame();
    }

    private void startGame(){
        if(isExit(wordOneTextField.getText())){
           JOptionPane.showMessageDialog(frame,"Thanks for playing!","Goodbye!",JOptionPane.INFORMATION_MESSAGE);
            dispose(); 
            return;
        }
        
        if(!errorMessages(wordOneTextField.getText(),wordTwoTextField.getText())){
            processAndCheckGame();
        }
    }
    
    private void processAndCheckGame(){
        userWordOne = wordOneTextField.getText();
        userWordTwo = wordTwoTextField.getText();
        caseWordOne = checkCaseOfWord(userWordOne);
        caseWordTwo = checkCaseOfWord(userWordTwo);
        processWordsAndArrays(firstWordArray,secondWordArray,caseWordOne,caseWordTwo);
        checkWinner(userWordOne,userWordTwo,firstWordArray,secondWordArray);
    }

    private void readTextFile(){
        try(Scanner myFileScanner = new Scanner(file)){
            while(myFileScanner.hasNextLine()){
                String words = myFileScanner.nextLine();
                fileWords.add(words.toLowerCase().trim());
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(frame,"File not found!","Error!",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadGameMessagesFile(){
        try(Scanner myFileScanner = new Scanner(TEXT_FILE)){
            while(myFileScanner.hasNextLine()){
                String line = myFileScanner.nextLine();
                String[] values = line.split("=");
                if(values.length == 2){
                    gameMessages.put(values[0].trim(),values[1].trim());
                }
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(frame,"Game messages file not found!","Error!",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterOutBadData(){
        boolean isValid = true;
        Set<String>cleanData = new HashSet<>();
        for(String word:fileWords){
            isValid = true;
            for(int i = 0;i<word.length();i++){
                if(!Character.isLetter(word.charAt(i))){
                    isValid = false;
                    break;
                }
            }
            if(isValid){
                cleanData.add(word);
            }
        }
        fileWords = cleanData;
    }



    private boolean errorMessages(String wordOne,String wordTwo){
        Map<String,Boolean>errorMessages = new LinkedHashMap<>();
        errorMessages.put(getGameMessage("emptyFieldOne"),isInputEmpty(wordOneTextField));
        errorMessages.put(getGameMessage("emptyFieldTwo"),isInputEmpty(wordTwoTextField));
        errorMessages.put(getGameMessage("firstWordNotInFile"),!hasFileContainedWord(wordOneTextField));
        errorMessages.put(getGameMessage("secondWordNotInFile"),!hasFileContainedWord(wordTwoTextField));
        errorMessages.put(getGameMessage("lengthMismatch"),isLengthOfWordsMismatch(wordOneTextField,wordTwoTextField));
        errorMessages.put(getGameMessage("isWordOneLengthInvalid"),isLengthOfWordValid(wordOneTextField));
        errorMessages.put(getGameMessage("isWordTwoLengthInvalid"),isLengthOfWordValid(wordTwoTextField));
        for(Map.Entry<String,Boolean> entry:errorMessages.entrySet()){
            if(entry.getValue()){
                setGameMessage(errorMessageLabel,entry.getKey());
                scheduleMessageToShow(errorMessageTimer,3000,errorMessageLabel);
                System.out.println(entry.getKey());
                return true;
            }
        }
        return false;
    }

    private void checkWinner(String wordOne,String wordTwo,int[] wordOneArray,int[] wordTwoArray){
        Map<String,Boolean> anagramResults = new LinkedHashMap<>();
        anagramResults.put(getGameMessage("anagram"),booleanIsAnagram(wordOneArray,wordTwoArray));
        anagramResults.put(getGameMessage("notAnagram"),!booleanIsAnagram(wordOneArray,wordTwoArray));
        for(Map.Entry<String,Boolean> entry:anagramResults.entrySet()){
            if(entry.getValue()){
                setGameMessage(resultLabel,entry.getKey());
                scheduleMessageToShow(resultMessageTimer,3000,resultLabel);
                System.out.println(entry.getKey());
                break;
            }
        }
    }

    private Timer scheduleMessageToShow(Timer timer,int displayDelay,JLabel label){
        stopTimer(timer);
        timer = displayOutputTimer(displayDelay,()->label.setText(""));
        return timer;
    }

    private String getGameMessage(String key){
        return gameMessages.getOrDefault(key,key);
    }

    private JLabel setGameMessage(JLabel label,String message){
        label.setText(message);
        return label;
    }

    private boolean isInputEmpty(JTextField jTextField){
        String input = jTextField.getText().trim();
        return input.isEmpty();
        
    }

    private boolean isLengthOfWordValid(JTextField jTextField){
        String input = jTextField.getText().trim();
        return input.length() <= 2;
    }

    private boolean isLengthOfWordsMismatch(JTextField jTextField,JTextField jTextFieldTwo){
        String inputOne = jTextField.getText().trim();
        String inputTwo = jTextFieldTwo.getText().trim();
        return inputOne.length() != inputTwo.length();
        
    }

    private boolean hasFileContainedWord(JTextField jTextField){
        String input = jTextField.getText().trim();
        return fileWords.contains(input.toLowerCase().trim());
    }

    private boolean booleanIsAnagram(int[]firstWordArray,int[] secondWordArray){
        for(int i = 0; i < 26; i++){
            if(firstWordArray[i] != secondWordArray[i]){
                return false;
            }
        }
        return true;
    }

    private void processWordsAndArrays(int[] firstWordArray,int[] secondWordArray,String caseWordOne,String caseWordTwo){
        resetArrays(firstWordArray);
        resetArrays(secondWordArray);
        countCharacters(caseWordOne,firstWordArray);
        countCharacters(caseWordTwo,secondWordArray);
    }

    private void countCharacters(String word,int[]count){
        for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) >= 97 && word.charAt(i) <= 122) {
				int result = word.charAt(i)-97;
				count[result]++;
			}
		}
    }

    private void resetArrays(int[] numArrays){
        for(int i = 0; i < numArrays.length; i++){
            numArrays[i] = 0;
        }
    }

    private String checkCaseOfWord(String words){
        return words = convertToLowerCase(words);
    }

    private String convertToLowerCase(String input){
        StringBuilder sb = new StringBuilder();
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) >= 65 && input.charAt(i) <= 90) {
				char result = (char)(input.charAt(i)+32);
				sb.append(result);
			}else if(input.charAt(i)>=97 && input.charAt(i) <= 122) {
				char result = (char)(input.charAt(i));
				sb.append(result);
			}
		}
		return sb.toString();
    }

    private void reset(){
        wordOneTextField.setText("");
        wordTwoTextField.setText("");
        resultLabel.setText("");
        errorMessageLabel.setText("");
    }

    private void exitButton(){
        dispose();
    }

    private boolean isExit(String userWordOne){
        return userWordOne.equalsIgnoreCase("quit");
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
