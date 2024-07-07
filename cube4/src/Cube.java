public class Cube {
    private double[][] vertices ;
    private int[][] faces ;

    public Cube(double size) {
        double halfSize = size / 2.0;
        vertices = new double[][]{
                { -halfSize, -halfSize, -halfSize },
                { halfSize, -halfSize , -halfSize },
                { halfSize, halfSize, -halfSize },
                { -halfSize, halfSize , -halfSize },
                { -halfSize , -halfSize, halfSize },
                { halfSize, -halfSize, halfSize },
                { halfSize, halfSize , halfSize },
                { -halfSize, halfSize, halfSize }
        };
        faces = new int[][]{
                { 0, 1, 2, 3 },
                { 4, 5, 6, 7 },
                { 0, 1, 5, 4 },
                { 2, 3, 7, 6 },
                { 0, 3, 7, 4 },
                { 1, 2, 6, 5 }

        };
    }

    public double[][] getVertices() {
        return vertices;
    }

    public int[][] getFaces() {
        return faces;
    }
}
