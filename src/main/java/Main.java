import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Main {
    //Triangle vertices
    private static final int[] A = {-4, -4, 0};
    private static final int[] B = {2, 6, -4};
    private static final int[] C = {7, -1, -2};

    private static final int[] viewPoint = {2, -5, 4};
    private static final int[] view = {3, -8, 5};

    private static final int intensity = 198;
    private static final int[] lightPosition = {-1, 7, -4};

    private static final int ambientIntensity = 183;

    private static final double ka = 0.22;
    private static final double kd = 0.96;
    private static final double ks = 0.33;
    private static final int n = 5;

    public static void main(String[] args) {
        Main main = new Main();
        double ambientComponent = main.calculateAmbientComponent();
        double diffuseComponent = main.calculateDiffuseComponent();
        double specularComponent = main.calculateSpecularComponent();

        System.out.println("Ambient component: " + ambientComponent);
        System.out.println("Diffuse component: " + diffuseComponent);
        System.out.println("Specular component: " + specularComponent);
        System.out.println("Total: " + (ambientComponent + diffuseComponent + specularComponent));
    }

    public double calculateAmbientComponent() {
        return ambientIntensity * ka;
    }

    public double calculateDiffuseComponent() {
        Vector3D normal = getNormalVector();
        Vector3D lightVector = getLightVector();

        return intensity * kd * lightVector.dotProduct(normal);
    }

    public double calculateSpecularComponent() {
        Vector3D reflectionVector = getReflection(getLightVector(), getNormalVector());
        Vector3D viewPointVector = getViewpointVector();

        double dotProduct = reflectionVector.dotProduct(viewPointVector);
        return intensity * ks * Math.pow(dotProduct, n);
    }

    private Vector3D getNormalVector() {
        Vector3D v1 = new Vector3D(B[0], B[1], B[2]).subtract(new Vector3D(A[0], A[1], A[2]));
        Vector3D v2 = new Vector3D(C[0], C[1], C[2]).subtract(new Vector3D(A[0], A[1], A[2]));

        return v1.crossProduct(v2).normalize();
    }

    private Vector3D getLightVector() {
        Vector3D lightPos = new Vector3D(lightPosition[0], lightPosition[1], lightPosition[2]);
        return getCenterOfPolygon().subtract(lightPos).normalize();
    }

    private Vector3D getCenterOfPolygon() {
        return new Vector3D((A[0] + B[0] + C[0]) / 3.0, (A[1] + B[1] + C[1]) / 3.0, (A[2] + B[2] + C[2]) / 3.0);
    }

    private Vector3D getReflection(Vector3D vector, Vector3D normalVector) {
        normalVector = normalVector.normalize();
        vector = vector.normalize();

        return vector.subtract(normalVector.scalarMultiply(2 * normalVector.dotProduct(vector)));
    }

    private Vector3D getViewpointVector() {
        return new Vector3D(viewPoint[0], viewPoint[1], viewPoint[2]).subtract(getCenterOfPolygon()).normalize();
    }
}
