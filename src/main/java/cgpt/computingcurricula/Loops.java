package cgpt.computingcurricula;

public class Loops {
    public static void main(String[] args) {
        System.out.println("For loop:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Iteration " + i);
        }

        System.out.println("While loop:");
        int count = 1;
        while (count <= 5) {
            System.out.println("Iteration " + count);
            count++;
        }
    }
}
