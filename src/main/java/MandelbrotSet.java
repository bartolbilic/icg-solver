import org.apache.commons.math3.complex.Complex;

public class MandelbrotSet {

    private final static double xMin = 0.0;
    private final static double yMin = 0.0;

    private final static double xMax = 800.0;
    private final static double yMax = 600.0;

    private final static double uMin = -2.0;
    private final static double vMin = -1.0;

    private final static double uMax = 0.25;
    private final static double vMax = 1.0;

    private final static double x = 470;
    private final static double y = 375;

    private final static int iterationNumber = 9;
    private final static double epsilon = 297;


    public static void main(String[] args) {
        MandelbrotSet main = new MandelbrotSet();
        int solution = main.solve();

        System.out.println("Solution: " + solution);
    }

    public int solve() {
        Complex z = getComplexNumber();
        Complex result = new Complex(0, 0);

        System.out.println("z" + 0 + " = " + result.getReal() + (result.getImaginary() >= 0 ? "+" : "") + result.getImaginary() + "i");

        for (int i = 0; i < iterationNumber - 1; i++) {
            result = result.multiply(result);
            result = result.add(z);

            System.out.println("z" + (i + 1) + " = " + result.getReal() + (result.getImaginary() >= 0 ? "+" : "") + result.getImaginary() + "i");
            if (result.abs() >= epsilon) {
                return i + 1;
            }
        }
        return 0;
    }

    private Complex getComplexNumber() {
        double u = ((x - xMin) / (xMax - xMin)) * (uMax - uMin) + uMin;
        double v = ((y - yMin) / (yMax - yMin)) * (vMax - vMin) + vMin;

        return new Complex(u, v);
    }


}
