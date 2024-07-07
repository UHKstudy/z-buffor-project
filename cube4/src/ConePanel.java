import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConePanel extends ShapePanel {

    private Cone cone;

    public ConePanel(double radius, double height) {
        super();

        cone = new Cone(radius, height, 20);
    }

    @Override
    protected void drawShape( Graphics2D g2d ) {
        g2d.setColor(Color.BLUE);

        double[][] vertices = cone.getVertices();
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

        int[][] faces = cone.getFaces();
        for (int[] face : faces) {
            int[] xPoints = new int[face.length];
            int[] yPoints = new int[face.length];
            for (int j = 0; j < face.length; j++) {
                double[] transformedVertex = transformedVertices[face[j]];
                float[] projected = camera.project(new float[]{
                        (float) transformedVertex[0],
                        (float) transformedVertex[1],
                        (float) transformedVertex[2]
                }, getWidth(), getHeight());
                xPoints[j] = (int) projected[0];
                yPoints[j] = (int) projected[1];
            }
            g2d.drawPolygon(xPoints, yPoints, face.length);
        }
    }
}
