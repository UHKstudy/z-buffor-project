import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Texture {

    private BufferedImage image;

    public Texture(String path) {
        try {
            System.out.println("próba czytania textury z pathu: " + path);
            image = ImageIO.read(getClass().getResourceAsStream(path)) ;
            if (image == null) {
                System.out.println("ImageIO.read zwraca null " + path);
            }
        } catch (IOException e) {
            e.printStackTrace() ;
        }
    }

    public BufferedImage getImage() {
        return image ;
    }
}
