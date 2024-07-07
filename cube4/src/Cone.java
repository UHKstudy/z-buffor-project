public class Cone  {
    private double radius , height;
    private int segments;
    private double[][] vertices;
    private int[][] faces;

    public Cone(double radius, double height , int segments) {
        this.radius = radius ;
        this.height = height ;
        this.segments = segments ;

        initialize();
    }

    private void initialize() {
        vertices = new double[segments + 2][3];
        faces = new int[segments * 2][3];

        // Bottom center
        vertices[0] = new double[]{0, 0, 0};

        // Top vertex
        vertices[1] = new double[]{0, height, 0};

        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);
            vertices[i + 2] = new double[]{x, 0, z};
        }

        for (int i = 0; i < segments; i++) {
            faces[i] = new int[]{0, i + 2, (i + 1) % segments + 2};
            faces[i + segments] = new int[]{1, i + 2, (i + 1) % segments + 2};
        }
    }

    public double[][] getVertices() {
        return vertices ;
    }

    public int[][] getFaces() {
        return faces;
    }
}
