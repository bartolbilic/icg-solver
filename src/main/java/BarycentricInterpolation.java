import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class BarycentricInterpolation {

    private static final double[] A = {-3, -1};
    private static final double[] B = {-7, -2};
    private static final double[] C = {9, -7};

    private static final double[] BARYCENTRIC = {0.52, 0.17, 0.3};
    private static final double[] S = {21, 227, 148};

    public static void main(String[] args) {
        BarycentricInterpolation main = new BarycentricInterpolation();
        Vector2D result = main.getPoint();
        System.out.println("T = (" + result.getX() + ", " + result.getY() + ")");
        System.out.println("Intensity: " + (BARYCENTRIC[0] * S[0] + BARYCENTRIC[1] * S[1] + BARYCENTRIC[2] * S[2]));
    }

    private Vector2D getPoint() {
        double x = BARYCENTRIC[0] * A[0] + BARYCENTRIC[1] * B[0] + BARYCENTRIC[2] * C[0];
        double y = BARYCENTRIC[0] * A[1] + BARYCENTRIC[1] * B[1] + BARYCENTRIC[2] * C[1];

        return new Vector2D(x, y);
    }
}
