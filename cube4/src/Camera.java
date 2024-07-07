public class Camera {
    private float x, y, z;
    private float pitch , yaw;

    public Camera() {
        this(new double[]{0, 0, -5} , new double[]{0, 0});
    }

    public Camera(double[] position, double[] rotation) {
        x = (float) position[0] ;
        y = (float) position[1] ;
        z = (float) position[2] ;
        pitch = (float) rotation[0];
        yaw = (float) rotation[1];
    }

    public void move(float dx, float dy, float dz) {
        x += dx;
        y += dy;
        z += dz;
    }

    public void rotate(float dpitch, float dyaw) {
        pitch += dpitch ;
        yaw += dyaw ;
     }

    public float[] project(float[] vertex, int width, int height) {
        float[] result = new float[2];

        float cosPitch = (float) Math.cos(pitch);
        float sinPitch = (float) Math.sin(pitch);

        float cosYaw = (float) Math.cos(yaw);
        float sinYaw = (float) Math.sin(yaw);

        float x = vertex[0] - this.x;
        float y = vertex[1] - this.y;

        float z = vertex[2] - this.z;

        float dx = cosYaw * (sinPitch * y + cosPitch * x) - sinYaw * z;
        float dy = sinYaw * (sinPitch * y + cosPitch * x) + cosYaw * z;

        float dz = cosPitch * y - sinPitch * x ;

        float scale = 200 / (dz + 5);

        result[0] = width / 2 + scale * dx;
        result[1] = height / 2 - scale * dy;


        return result;
    }
}
