import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите нижнюю границу интегрирования (a): ");
        double a = scanner.nextDouble();
        System.out.print("Введите верхнюю границу интегрирования (b): ");
        double b = scanner.nextDouble();

        System.out.println("Выберите метод интегрирования:");
        System.out.println("1 - Метод левых прямоугольников");
        System.out.println("2 - Метод средних прямоугольников");
        System.out.println("3 - Метод правых прямоугольников");
        System.out.println("4 - Метод трапеций");
        System.out.println("5 - Метод Симпсона");
        int methodChoice = scanner.nextInt();

        System.out.println("Выберите режим:");
        System.out.println("1 - Постоянный шаг");
        System.out.println("2 - Автоматический шаг (метод Рунге)");
        int mode = scanner.nextInt();

        double result = 0.0;
        switch (mode){
            case 1:
                System.out.print("Введите количество разбиений (n): ");
                int n = scanner.nextInt();
                result = NumericIntegration.integrate(methodChoice, a, b, n);
                break;
            case 2:
                System.out.print("Введите точность (eps): ");
                double eps = scanner.nextDouble();
                int m = switch (methodChoice) {
                    case 1 -> 1;
                    case 2 -> 2;
                    case 3 -> 1;
                    case 4 -> 2;
                    case 5 -> 4;
                    default -> throw new IllegalArgumentException("Неверный метод.");
                };
                result = NumericIntegration.rungeStep(methodChoice, a, b, eps, m);
                break;
            default:
                System.out.println("Неверный режим.");
                break;
        }

        System.out.print("Результат интегрирования ");
        switch (methodChoice) {
            case 1:
                System.out.print("методом левых треугольников: ");
                break;
            case 2:
                System.out.print("методом средних треугольников: ");
                break;
            case 3:
                System.out.print("методом правых треугольников: ");
                break;
            case 4:
                System.out.print("методом трапеций: ");
                break;
            case 5:
                System.out.print("методом Симпсона: ");
                break;
            default:
                break;
        }
        System.out.println(result);
    }
}