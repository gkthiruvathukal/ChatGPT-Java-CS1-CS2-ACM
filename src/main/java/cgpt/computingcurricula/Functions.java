package cgpt.computingcurricula;

public class Functions {
    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        int sum = add(a, b);
        System.out.println("The sum of " + a + " and " + b + " is " + sum);
    }

    public static int add(int x, int y) {
        return x + y;
    }
}
