import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class SphereLineIntersection {

    private static final double[] S = {2.0, 7.0, 2.0, 1.0};
    private static final double radius = 4;

    private static final double[] V1 = {1.0, 10.0, 3.0, 1.0};
    private static final double[] V2 = {2.0, 2.0, 0.0, 1.0};

    public static void main(String[] args) {
        SphereLineIntersection main = new SphereLineIntersection();
        List<Vector3D> solutions = main.solve();

        if (solutions.size() == 0) {
            System.out.println("Solution 1: (+, +, +)");
            System.out.println("Solution 2: (+, +, +)");
        }

        if (solutions.size() == 1) {
            System.out.println("Solution 1: (" + solutions.get(0).getX() + ", " + solutions.get(0).getY() + ", " + solutions.get(0).getZ() + ")");
            System.out.println("Solution 2: (+, +, +)");
        }

        if (solutions.size() == 2) {
            System.out.println("Solution 1: (" + solutions.get(0).getX() + ", " + solutions.get(0).getY() + ", " + solutions.get(0).getZ() + ")");
            System.out.println("Solution 2: (" + solutions.get(1).getX() + ", " + solutions.get(1).getY() + ", " + solutions.get(1).getZ() + ")");
        }
    }

    private Vector3D getDirection() {
        return new Vector3D(V2[0], V2[1], V2[2]).subtract(new Vector3D(V1[0], V1[1], V1[2]));
    }

    private List<Vector3D> solve() {
        Vector3D direction = getDirection();

        double a = direction.getX() * direction.getX() +
                direction.getY() * direction.getY() +
                direction.getZ() * direction.getZ();

        double b = 2 * (direction.getX() * (V1[0] - S[0]) +
                direction.getY() * (V1[1] - S[1]) +
                direction.getZ() * (V1[2] - S[2]));

        double c = (V1[0] - S[0]) * (V1[0] - S[0]) + (V1[1] - S[1]) * (V1[1] - S[1]) + (V1[2] - S[2]) * (V1[2] - S[2]) - radius * radius;

        List<Double> parameters = solveQuadratic(a, b, c);
        List<Vector3D> solutions = new ArrayList<>();

        for (double parameter : parameters) {
            solutions.add(new Vector3D(
                    getDirection().getX() * parameter + V1[0],
                    getDirection().getY() * parameter + V1[1],
                    getDirection().getZ() * parameter + V1[2]));
        }
        return solutions;
    }

    private List<Double> solveQuadratic(double a, double b, double c) {
        double determinant = b * b - 4 * a * c;

        if (determinant < 0) {
            return new ArrayList<>();
        }

        List<Double> solutions = new ArrayList<>();

        if (determinant == 0) {
            solutions.add(-1 * b / 2 * a);
            return solutions;
        }

        double numerator = -1 * b + Math.sqrt(determinant);
        double denominator = 2 * a;
        solutions.add(numerator / denominator);
        numerator = -1 * b - Math.sqrt(determinant);
        solutions.add(numerator / denominator);
        return solutions;
    }
}
