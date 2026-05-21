public class AnagramTester{
    private static final int X_COORDINATE = 100;
    private static final  int Y_COORDINATE = 100;
    private static final int WINDOW_WIDTH = 613;
    private static final int WINDOW_HEIGHT = 351;
    public static void main(String[] args){
        Anagram anagram = new Anagram();
        anagram.setBounds(X_COORDINATE,Y_COORDINATE,WINDOW_WIDTH,WINDOW_HEIGHT);
        anagram.setVisible(true);
    }
}