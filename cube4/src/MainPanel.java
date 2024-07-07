import javax.swing.*;
import java.awt.*;

class MainPanel extends JPanel {
    public MainPanel() {
        setLayout(new GridLayout(1, 3));

        CubePanel cubePanel = new CubePanel(1, "src/main/resources/bric.png") ; // Dodaj ścieżkę do pliku tekstury
        SpherePanel spherePanel = new SpherePanel(1);
        ConePanel conePanel = new ConePanel(1, 1);

        add(cubePanel) ;
        add(spherePanel) ;
        add(conePanel) ;
    }
}
