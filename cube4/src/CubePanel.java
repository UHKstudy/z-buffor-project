import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class CubePanel extends ShapePanel {
    private Cube cube;
    private BufferedImage texture;

    public CubePanel(double size , String texturePath) {
        super();
        cube = new Cube(size);
        try {
            texture = ImageIO.read(new FileInputStream(texturePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Błąd podczas ładowania tex: " + e.getMessage());
        }
    }

    @Override
    protected void drawShape(Graphics2D g2d) {
        if (texture == null) {
            System.out.println("Tex jest null. Poprawna ścieżka?");
            return;
        }

        double[][] vertices = cube.getVertices();

        double[][] transformedVertices = new double[vertices.length][3];

        for (int i = 0; i < vertices.length; i++) {
            double[] vertex = vertices[i];
            double x = vertex[0] ;
            double y = vertex[1] ;
            double z = vertex[2] ;

            double tempX = x * Math.cos(rotation[1]) - z * Math.sin(rotation[1]);
            z = x * Math.sin(rotation[1]) + z * Math.cos(rotation[1]);
            x = tempX;
            double tempY = y * Math.cos(rotation[0]) - z * Math.sin(rotation[0]);
            z = y * Math.sin(rotation[0]) + z * Math.cos(rotation[0]);
            y = tempY;

            transformedVertices[i][0] = x;

            transformedVertices[i][1] = y;
            transformedVertices[i][2] = z;
        }

        int[][] faces = cube.getFaces();
        for (int[] face : faces) {
            // Calculate face normal to determine if the face is front-facing
            double[] v1 = transformedVertices[face[0]] ;
            double[] v2 = transformedVertices[face[1]] ;
            double[] v3 = transformedVertices[face[2]] ;

            double[] normal = {
                    (v2[1] - v1[1]) * (v3[2] - v1[2]) - (v2[2] - v1[2]) * (v3[1] - v1[1]),
                    (v2[2] - v1[2]) * (v3[0] - v1[0]) - (v2[0] - v1[0]) * (v3[2] - v1[2]),
                    (v2[0] - v1[0]) * (v3[1] - v1[1]) - (v2[1] - v1[1]) * (v3[0] - v1[0])
            };

            // Check if face is front-facing
            double[] viewDirection = {0 - v1[0], 0 - v1[1], -5 - v1[2]};
            double dotProduct = normal[0] * viewDirection[0] + normal[1] * viewDirection[1] + normal[2] * viewDirection[2];

            if (dotProduct < 0) {
                int[] xPoints = new int[face.length];
                int[] yPoints = new int[face.length];
                for (int j = 0; j < face.length; j++) {
                    double[] transformedVertex = transformedVertices[face[j]];
                    float[] projected = camera.project(new float[]{
                            (float) transformedVertex[0],
                            (float) transformedVertex[1],
                            (float) transformedVertex[2]
                    } , getWidth(), getHeight());
                    xPoints[j] = (int) projected[0];
                    yPoints[j] = (int) projected[1];
                }
                Polygon poly = new Polygon(xPoints, yPoints, face.length);
                TexturePaint paint = new TexturePaint(texture, new Rectangle(xPoints[0], yPoints[0], texture.getWidth(), texture.getHeight()));
                g2d.setPaint(paint);
                g2d.fillPolygon(poly);

            }
        }
    }
}
