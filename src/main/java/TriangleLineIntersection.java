import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.linear.*;

public class TriangleLineIntersection {

    private static double[] A = {-1.90, -7.39, -2.84};
    private static double[] B = {4.39, 4.32, 6.55};
    private static double[] C = {1.05, -7.66, 1.57};

    private static double[] P = {8.49, -9.10, -1.13};
    private static double[] K = {-17.40, -20.15, 13.85};

    public static void main(String[] args) {
        TriangleLineIntersection main = new TriangleLineIntersection();
        Vector3D intersection = main.getIntersection();

        if (main.isInTriangle(intersection)) {
            System.out.println("Intersection: (" + intersection.getX() + ", " + intersection.getY() + ", " + intersection.getZ() + ")");
        } else {
            System.out.println("Intersection: (NE, NE, NE)");
        }

    }

    private Vector3D getPlaneNormalVector() {
        Vector3D v1 = new Vector3D(C[0], C[1], C[2]).subtract(new Vector3D(A[0], A[1], A[2]));
        Vector3D v2 = new Vector3D(B[0], B[1], B[2]).subtract(new Vector3D(A[0], A[1], A[2]));

        return v1.crossProduct(v2);
    }

    private double[] getPlane() {
        Vector3D normalVector = getPlaneNormalVector();

        double D = -1 * normalVector.getX() * A[0] - normalVector.getY() * A[1] - normalVector.getZ() * A[2];
        return new double[]{normalVector.getX(), normalVector.getY(), normalVector.getZ(), D};
    }

    private Vector3D getLineDirection() {
        return new Vector3D(K[0], K[1], K[2]).subtract(new Vector3D(P[0], P[1], P[2]));
    }

    private double getLambda() {
        double numerator = -1 * (getPlane()[3] + getPlaneNormalVector().dotProduct(new Vector3D(P[0], P[1], P[2])));
        double denominator = getPlaneNormalVector().dotProduct(getLineDirection());

        return numerator / denominator;
    }

    private Vector3D getIntersection() {
        return new Vector3D(P[0], P[1], P[2]).add(getLineDirection().scalarMultiply(getLambda()));
    }

    private boolean isInTriangle(Vector3D point) {
        RealMatrix coefficients = new Array2DRowRealMatrix(new double[][]{
                {A[0], B[0], C[0]},
                {A[1], B[1], C[1]},
                {A[2], B[2], C[2]}
        }, false);

        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        RealVector constants = new ArrayRealVector(new double[]{point.getX(), point.getY(), point.getZ()});
        RealVector solution = solver.solve(constants);

        if (solution.getEntry(0) < 0 || solution.getEntry(1) < 0 || solution.getEntry(2) < 0 ||
                solution.getEntry(0) > 1 || solution.getEntry(1) > 1 || solution.getEntry(2) > 1) {
            return false;
        }

        return true;
    }
}
