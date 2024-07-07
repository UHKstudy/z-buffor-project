import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class TexturedCubePanel extends JPanel {
    private Cube cube;
    private BufferedImage texture;
    private Camera camera;
    private double[] rotation;

    public TexturedCubePanel(double size, String texturePath) {
        super();
        cube = new Cube(size);
        camera = new Camera();
        rotation = new double[]{0, 0};

        try {
            texture = ImageIO.read(new File(texturePath));
        } catch (IOException e) {
            e.printStackTrace() ;
            System.out.println("Błąd do ładowania tekstury: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawShape(g2d) ;
    }

    private void drawShape(Graphics2D g2d) {
        if (texture == null) {
            System.out.println("Tekstura jest null. ścieżka poprawna ?");
            return;
        }

        double[][] vertices = cube.getVertices() ;
        double[][] transformedVertices = new double[vertices.length][3];

        for (int i = 0; i < vertices.length; i++) {
            double[] vertex = vertices[i];
            double x = vertex[0];
            double y = vertex[1];
            double z = vertex[2];

            double tempX = x * Math.cos(rotation[1]) - z * Math.sin(rotation[1]);
            z = x * Math.sin(rotation[1]) + z * Math.cos(rotation[1]);
            x = tempX;
            double tempY = y * Math.cos(rotation[0]) - z * Math.sin(rotation[0]);
            z = y * Math.sin(rotation[0]) + z * Math.cos(rotation[0]);
            y = tempY;

            transformedVertices[i][0] = x ;
            transformedVertices[i][1] = y ;
            transformedVertices[i][2] = z ;
        }

        int[][] faces = cube.getFaces();
        double[] faceDepths = new double[faces.length];

        // calc z-value dla kazdej sciany
        for (int i = 0; i < faces.length; i++) {
            double sumZ = 0;
            for (int j : faces[i]) {
                sumZ += transformedVertices[j][2];
            }
            faceDepths[i] = sumZ / faces[i].length ;
        }

        // segreguj podle z value
        Integer[] faceOrder = new Integer[faces.length];
        for (int i = 0; i < faceOrder.length; i++) {
            faceOrder[i] = i;
        }
        Arrays.sort(faceOrder, (a, b) -> Double.compare(faceDepths[b], faceDepths[a]));

        for (int i : faceOrder) {
            int[] face = faces[i];
            int[] xPoints = new int[face.length];
            int[] yPoints = new int[face.length];
            for (int j = 0; j < face.length ; j++) {
                double[] transformedVertex = transformedVertices[face[j]];
                float[] projected = camera.project(new float[]{
                        (float) transformedVertex[0],
                        (float) transformedVertex[1],
                        (float) transformedVertex[2]
                }, getWidth(), getHeight());
                xPoints[j] = (int) projected[0];
                yPoints[j] = (int) projected[1];
            }

            Polygon poly = new Polygon(xPoints, yPoints, face.length);

            // calc granice ścian
            Rectangle bounds = poly.getBounds();

            // text paint
            double scaleX = bounds.getWidth() / texture.getWidth();

            double scaleY = bounds.getHeight() / texture.getHeight();
            AffineTransform tx = new AffineTransform();
            tx.translate(bounds.getX(), bounds.getY());
            tx.scale(scaleX, scaleY);

            Path2D path = new Path2D.Double(poly);
            g2d.setClip(path);
            g2d.drawImage(texture, tx , null);
            g2d.setClip(null);
        }
    }

    public void rotate(double dpitch, double dyaw) {
        rotation[0] += dpitch ;
        rotation[1] += dyaw ;
        repaint();
    }
}
