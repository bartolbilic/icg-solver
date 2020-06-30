import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

public class PolygonVisibility {

    public final static double[] A = {-6.3, -0.7, -9.8};
    public final static double[] B = {-7.4, -0.7, -0.4};
    public final static double[] C = {8.5, -6.1, -0.1};
    public final static double[] D = {7.2, -6.8, 4.6};

    public final static double[] O = {5.2, 11.5, -0.9};
    public final static double[] G = {0, 0, 0};

    public static void main(String[] args) {
        Vector3D v1 = new Vector3D(A[0], A[1], A[2]);
        Vector3D v2 = new Vector3D(B[0], B[1], B[2]);
        Vector3D v3 = new Vector3D(C[0], C[1], C[2]);
        Vector3D v4 = new Vector3D(D[0], D[1], D[2]);

        Triangle P1 = new Triangle(v1, v2, v3);
        Triangle P2 = new Triangle(v2, v3, v4);
        Triangle P3 = new Triangle(v3, v4, v1);
        Triangle P4 = new Triangle(v4, v1, v2);

        PolygonVisibility main = new PolygonVisibility();

        Vector3D center = main.getPyramidCenter(P1, P2, P3, P4);

        System.out.println("P1 is visible? " + main.isVisible(P1, center));
        System.out.println("P2 is visible? " + main.isVisible(P2, center));
        System.out.println("P3 is visible? " + main.isVisible(P3, center));
        System.out.println("P4 is visible? " + main.isVisible(P4, center));
    }

    private boolean isVisible(Triangle triangle, Vector3D pyramidCenter) {
        Vector3D viewpoint = new Vector3D(O[0], O[1], O[2]);

        RealMatrix matrix = new Array2DRowRealMatrix(new double[][]{
                {triangle.getFirst().getX(), triangle.getFirst().getY(), triangle.getFirst().getZ(), 1},
                {triangle.getSecond().getX(), triangle.getSecond().getY(), triangle.getSecond().getZ(), 1},
                {triangle.getThird().getX(), triangle.getThird().getY(), triangle.getThird().getZ(), 1},
                {pyramidCenter.getX(), pyramidCenter.getY(), pyramidCenter.getZ(), 1}
        }, false);

        double determinant = new LUDecomposition(matrix).getDeterminant();

        matrix = new Array2DRowRealMatrix(new double[][]{
                {triangle.getFirst().getX(), triangle.getFirst().getY(), triangle.getFirst().getZ(), 1},
                {triangle.getSecond().getX(), triangle.getSecond().getY(), triangle.getSecond().getZ(), 1},
                {triangle.getThird().getX(), triangle.getThird().getY(), triangle.getThird().getZ(), 1},
                {viewpoint.getX(), viewpoint.getY(), viewpoint.getZ(), 1}
        }, false);

        double otherDeterminant = new LUDecomposition(matrix).getDeterminant();

        if (determinant < 0) {
            return otherDeterminant >= 0;
        }

        return otherDeterminant <= 0;
    }

    private Vector3D getPyramidCenter(Triangle first, Triangle second, Triangle third, Triangle fourth) {
        Vector3D center1 = getCenter(first);
        Vector3D center2 = getCenter(second);
        Vector3D center3 = getCenter(third);
        Vector3D center4 = getCenter(fourth);

        return new Vector3D(
                (center1.getX() + center2.getX() + center3.getX() + center4.getX()) / 4.0,
                (center1.getY() + center2.getY() + center3.getY() + center4.getY()) / 4.0,
                (center1.getZ() + center2.getZ() + center3.getZ() + center4.getZ()) / 4.0);
    }

    private Vector3D getCenter(Triangle triangle) {
        return new Vector3D(
                (triangle.getFirst().getX() + triangle.getSecond().getX() + triangle.getThird().getX()) / 3.0,
                (triangle.getFirst().getY() + triangle.getSecond().getY() + triangle.getThird().getY()) / 3.0,
                (triangle.getFirst().getZ() + triangle.getSecond().getZ() + triangle.getThird().getZ()) / 3.0);
    }

    public static class Triangle {
        private final Vector3D first;
        private final Vector3D second;
        private final Vector3D third;

        public Triangle(Vector3D first, Vector3D second, Vector3D third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public Vector3D getFirst() {
            return first;
        }

        public Vector3D getSecond() {
            return second;
        }

        public Vector3D getThird() {
            return third;
        }
    }
}
