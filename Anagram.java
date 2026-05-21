
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

    private JLabel wordOneLabel;
    private JLabel wordTwoLabel;
    private JLabel resultLabelText;
    private JLabel errorMessagesLabelText;
    private JLabel messageOneLbl;
    private JLabel messageTwoLbl;
    private JLabel headerLabel;

    private Color[] colors = new Color[]{Color.RED,Color.black,Color.green,Color.blue};

    public Anagram(){

    }
    
}
