import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Semestral Projekt Graphic2 M.Z." );
        MainPanel mainPanel = new MainPanel();
        frame.add(mainPanel);
        frame.setSize(1200, 600) ;  // Adjusted for a better view
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible( true );
    }
}
