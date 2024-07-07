import javax.swing.*;
import java.awt.*;

public class Renderer extends JFrame {

    private JPanel shapePanel;

    public Renderer(JPanel shapePanel) {
        this.shapePanel = shapePanel;
        add(this.shapePanel);

        setSize(1200, 600);  // Adjusted for a better view
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        shapePanel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        TexturedCubePanel texturedCubePanel = new TexturedCubePanel(1, "src/main/resources/bric.png");
        SpherePanel spherePanel = new SpherePanel(1 );
        ConePanel conePanel = new ConePanel(1, 2);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3));
        mainPanel.add(texturedCubePanel);

        mainPanel.add(spherePanel) ;
        mainPanel.add(conePanel);

        // Utwórz Renderer z głównym panelem
        Renderer renderer = new Renderer(mainPanel);
    }
}
