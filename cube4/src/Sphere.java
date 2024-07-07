public class Sphere {
    private double radius;
    private int segments;
    private double[][] vertices;
    private int[][] faces;

    public Sphere(double radius, int segments) {
        this.radius = radius;
        this.segments = segments;
        initialize() ;
    }

    private void initialize() {
        int numVertices = (segments + 1) * (segments + 1);

        vertices = new double[numVertices][3];
        faces = new int[segments * segments * 2][3];

        int index = 0;
        for (int i = 0; i <= segments; i++) {
            double theta = i * Math.PI / segments;
            for (int j = 0; j <= segments; j++) {
                double phi = j * 2 * Math.PI / segments;
                double x = radius * Math.sin(theta) * Math.cos(phi);
                double y = radius * Math.sin(theta) * Math.sin(phi);
                double z = radius * Math.cos(theta);
                vertices[index][0] = x;
                vertices[index][1] = y;
                vertices[index][2] = z;
                index++ ;
            }
        }

        index = 0;
        for (int i = 0; i < segments; i++) {
            for (int j = 0; j < segments; j++) {
                int first = (i * (segments + 1)) + j;
                int second = first + segments + 1;
                faces[index++] = new int[]{first, second, first + 1};
                faces[index++] = new int[]{second, second + 1, first + 1};
            }
        }
    }

    public double[][] getVertices() {
        return vertices ;
    }

    public int[][] getFaces() {
        return faces;
    }
}
