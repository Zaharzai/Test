import java.util.Scanner;

public class Fibonati {

    static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    static int factor(int k){
        if (k <= 1) {
            return k;
        }
        else {
            return k * factor(k-1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер числа в последовательности Фибоначчи:");
        int n = scanner.nextInt();
        System.out.println("Введите число:");
        int k = scanner.nextInt();
        System.out.println("Факториал" + k + " равно: " + factor(k));
        System.out.println("Число Фибоначчи с номером " + n + " равно: " + fibonacci(n));

        scanner.close();
    }
}