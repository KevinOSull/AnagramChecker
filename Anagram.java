
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.InputMismatchException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
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

    private final int X = 200;
    private final int Y = 11;
    private final int WIDTH = 392;
    private final int HEIGHT = 42;

    private int result;

    private JButton submitButton;
    private JButton resetButton;

    private JTextField wordOneTextField;
    private JTextField wordTwoTextField;
    private JLabel wordOneLabel;
    private JLabel wordTwoLabel;
    private JLabel resultLabelText;
    private JLabel errorMessagesLabelText;
    private JLabel messageOneLbl;
    private JLabel messageTwoLbl;
    private JLabel headerLabel;

    private Color[] colors = new Color[]{Color.RED,Color.black,Color.green,Color.blue};

    public Anagram(){
        headerLabel = new JLabel("ANAGRAM CHECKER");
        headerLabel.setFont(new Font("Serif",Font.BOLD,30));
        result = getRandomColor();
        headerLabel.setForeground(setColor());
         submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");
        wordOneLabel = new JLabel("Word One:");
        wordTwoLabel = new JLabel("Word Two:");
        resultLabelText = new JLabel("Result:");
        messageOneLbl = new JLabel("RESULT GOES HERE");
        messageTwoLbl = new JLabel("ERRORS GO HERE");
        messageOneLbl.setBounds(21,212,566,14);
        messageTwoLbl.setBounds(21,250,566,14);
        errorMessagesLabelText = new JLabel("Error Messages:");
        resultLabelText.setBounds(21,198,79,14);
        errorMessagesLabelText.setBounds(21,237,101,14);
        wordOneLabel.setBounds(93,79,73,14);
        wordTwoLabel.setBounds(93,128,73,14);
        wordOneTextField = new JTextField();
        wordTwoTextField = new JTextField();
        wordOneTextField.setBounds(179,76,188,20);
        wordTwoTextField.setBounds(179,125,188,20);
        headerLabel.setBounds(X,Y,WIDTH,HEIGHT);
        submitButton.setBounds(179,156,89,23);
        resetButton.setBounds(278,156,89,23);
        getContentPane().setBackground(Color.yellow);
        add(headerLabel);
        add(submitButton);
        add(resetButton);
        add(wordOneTextField);
        add(wordTwoTextField);
        add(wordOneLabel);
        add(wordTwoLabel);
        add(resultLabelText);
        add(errorMessagesLabelText);
        add(messageOneLbl);
        add(messageTwoLbl);

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

    }

    private void submitWordButton(){

    }

    private void reset(){
        
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
