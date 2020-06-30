import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class PerspectiveProjection {
    private static final int[] C = {46, 43, 20};
    private static final int[] V1 = {40, 16, 39};
    private static final int[] V2 = {29, 14, 14};
    private static final int[] R = {17, 3, 5, 20};

    public static void main(String[] args) {
        PerspectiveProjection main = new PerspectiveProjection();
        Vector3D first = main.getPoint(main.getFirstVector());
        Vector3D second = main.getPoint(main.getSecondVector());

        System.out.println("T1 = (" + first.getX() + ", " + first.getY() + ", " + first.getZ() + ")");
        System.out.println("T2 = (" + second.getX() + ", " + second.getY() + ", " + second.getZ() + ")");
    }


    private Vector3D getFirstVector() {
        return new Vector3D(V1[0], V1[1], V1[2]).subtract(new Vector3D(C[0], C[1], C[2]));
    }

    private Vector3D getSecondVector() {
        return new Vector3D(V2[0], V2[1], V2[2]).subtract(new Vector3D(C[0], C[1], C[2]));
    }

    private double getLambda(Vector3D vector) {
        double numerator = -1.0 * (R[3] + new Vector3D(R[0], R[1], R[2]).dotProduct(new Vector3D(C[0], C[1], C[2])));
        double denominator = new Vector3D(R[0], R[1], R[2]).dotProduct(vector);
        return numerator / denominator;
    }

    private Vector3D getPoint(Vector3D vector) {
        double lambda = getLambda(vector);
        return new Vector3D(C[0], C[1], C[2]).add(vector.scalarMultiply(lambda));
    }
}
