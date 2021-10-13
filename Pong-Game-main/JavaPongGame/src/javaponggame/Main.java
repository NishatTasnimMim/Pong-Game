package javaponggame;
import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        View view = new View();
        Model model = new Model(view);

        frame.setSize(800, 800);
        frame.getContentPane().add(view);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
 