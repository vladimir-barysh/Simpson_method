public class NumericIntegration {

    private static double func(double x){
        return x * x;
    }

    // Методы прямоугольников
    public static double rectangleLeftMethod(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum += func(a + i * h);
        }
        return h * sum;
    }

    public static double rectangleMidMethod(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum += func(a + (i + 0.5) * h);
        }
        return h * sum;
    }

    public static double rectangleRightMethod(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += func(a + i * h);
        }
        return h * sum;
    }

    // Метод трапеций
    public static double trapezoidMethod(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = (func(a) + func(b)) / 2.0;
        for (int i = 1; i < n; i++) {
            sum += func(a + i * h);
        }
        return h * sum;
    }

    // Метод Симпсона
    public static double simpsonMethod(double a, double b, int n) {
        if (n % 2 != 0) n++;
        double h = (b - a) / n;
        double sum = func(a) + func(b);
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += (i % 2 == 0 ? 2 : 4) * func(x);
        }
        return h * sum / 3;
    }

    //Метод для автоматического подбора числа разбиений с указанной точностью
    public static double rungeStep(int method, double a, double b, double eps, int m) {
        int n = 4;
        double I_h = integrate(method, a, b, n);
        double I_h2, realEps;

        do {
            n *= 2;
            I_h2 = integrate(method, a, b, n);
            realEps = Math.abs(I_h2 - I_h) / (Math.pow(2, m) - 1);
            I_h = I_h2;
        } while (realEps > eps);

        return I_h2;
    }

    // Универсальный метод для вызова конкретного способа интегрирования
    public static double integrate(int method, double a, double b, int n) {
        double result = 0.0;
        switch (method) {
            case 1:
                result = rectangleLeftMethod(a, b, n);
                break;
            case 2:
                result = rectangleMidMethod(a, b, n);
                break;
            case 3:
                result = rectangleRightMethod(a, b, n);
                break;
            case 4:
                result = trapezoidMethod(a, b, n);
                break;
            case 5:
                result = simpsonMethod(a, b, n);
                break;
            default: throw new IllegalArgumentException("Неверный метод интегрирования");
        };
        return result;
    }

}
