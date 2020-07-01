import com.google.common.collect.Lists;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class BodyPointRelation {
    private final static Vector3D P1 = new Vector3D(1, 5, 2);
    private final static Vector3D P2 = new Vector3D(3, 5, 1);
    private final static Vector3D P3 = new Vector3D(4, 5, 3);
    private final static Vector3D P4 = new Vector3D(2, 5, 4);
    private final static Vector3D P5 = new Vector3D(6, 1, 2);
    private final static Vector3D P6 = new Vector3D(6, 3, 1);
    private final static Vector3D P7 = new Vector3D(6, 4, 3);
    private final static Vector3D P8 = new Vector3D(6, 2, 4);

    //AKO RJEŠENJE ISPADNE 1 OKRENI UVJET!!

    private final static Vector3D T1 = new Vector3D(-7, -7, -1);
    private final static Vector3D T2 = new Vector3D(0, -5, -1);
    private final static Vector3D T3 = new Vector3D(-5, 0, -1);
    private final static Vector3D T4 = new Vector3D(1, 1, -1);
    private final static Vector3D T5 = new Vector3D(-3, -3, 5);
    private final static Vector3D T6 = new Vector3D(-3, -3, -6);

    private final static Vector3D T = new Vector3D(-2, 2, 3);

    public static void main(String[] args) {
        System.out.println("Solution: " + solve());
    }

    private static double[] getPlane(Vector3D first, Vector3D second, Vector3D third) {
        double A = (second.getY() - first.getY()) * (third.getZ() - first.getZ()) - (second.getZ() - first.getZ()) * (third.getY() - first.getY());
        double B = -1 * (second.getX() - first.getX()) * (third.getZ() - first.getZ()) + (second.getZ() - first.getZ()) * (third.getX() - first.getX());
        double C = (second.getX() - first.getX()) * (third.getY() - first.getY()) - (second.getY() - first.getY()) * (third.getX() - first.getX());
        double D = -1 * first.getX() * A - first.getY() * B - first.getZ() * C;
        return new double[]{A, B, C, D};
    }

    private static int solve() {
        List<Vector3D> points = Lists.newArrayList(T1, T2, T3, T4, T5, T6);
        List<Vector3D> polygons = Lists.newArrayList(P1, P2, P3, P4, P5, P6, P7, P8);

        List<Triangle> triangles = new ArrayList<>();

        for (Vector3D polygon : polygons) {
            Vector3D first = points.get((int) polygon.getX() - 1);
            Vector3D second = points.get((int) polygon.getY() - 1);
            Vector3D third = points.get((int) polygon.getZ() - 1);

            Triangle triangle = new Triangle(first, second, third);
            triangles.add(triangle);
        }

        int counter = 1;

        for (Triangle triangle : triangles) {
            double[] plane = getPlane(triangle.getFirst(), triangle.getSecond(), triangle.getThird());
            double result = plane[0] * T.getX() + plane[1] * T.getY() + plane[2] * T.getZ() + plane[3];

            //OVAJ TU UVJET OKRENI AKO ISPADNE RJEŠENJE 1, UMJESTO > STAVI <
            if (result > 0) {
                return counter;
            }
            counter++;
        }
        return 0;
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
