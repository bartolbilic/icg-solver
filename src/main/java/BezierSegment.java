public class BezierSegment {
    private static final int[] T0 = {-3, 6, 3};
    private static final int[] T1 = {-9, -5, -5};
    private static final int[] T2 = {7, 8, 3};
    private static final int[] T3 = {9, -5, 9};

    private static final int[] P0 = {31, 8, -10};
    private static final int[] P1 = {36, -1, -7};
    private static final int[] P2 = {50, -1};
    private static final int[] P3 = {49, 4};


    public static void main(String[] args) {
        System.out.println("SO = (" + T3[0] + ", " + T3[1] + ", " + T3[2] + ")");
        System.out.println("S1 = (" + (2 * T3[0] - T2[0]) + ", " + (2 * T3[1] - T2[1]) + ", " + (2 * T3[2] - T2[2]) + ")");
        System.out.println("S2 = (" + (2 * P0[0] - P1[0]) + ", " + (2 * P0[1] - P1[1]) + ", " + (2 * P0[2] - P1[2]) + ")");
        System.out.println("S3 = (" + P0[0] + ", " + P0[1] + ", " + P0[2] + ")");
    }

}
