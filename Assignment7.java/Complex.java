import java.util.Scanner;

public class Complex implements Comparable<Complex> {
    private double realPart;
    private double imaginaryPart;

    public Complex() {
        this(0, 0);
    }

    public Complex(double realPart) {
        this(realPart, 0);
    }

    public Complex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public Complex(Complex complexNumber) {
        this(complexNumber.getRealPart(), complexNumber.getImaginaryPart());
    }

    public double getRealPart() {
        return realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public Complex add(Complex other) {
        double newRealPart = this.realPart + other.realPart;
        double newImaginaryPart = this.imaginaryPart + other.imaginaryPart;
        return new Complex(newRealPart, newImaginaryPart);
    }

    public Complex subtract(Complex other) {
        double newRealPart = this.realPart - other.realPart;
        double newImaginaryPart = this.imaginaryPart - other.imaginaryPart;
        return new Complex(newRealPart, newImaginaryPart);
    }

    public Complex multiply(Complex other) {
        double newRealPart = this.realPart * other.realPart - this.imaginaryPart * other.imaginaryPart;
        double newImaginaryPart = this.realPart * other.imaginaryPart + this.imaginaryPart * other.realPart;
        return new Complex(newRealPart, newImaginaryPart);
    }

    public Complex divide(Complex other) {
        double denominator = other.realPart * other.realPart + other.imaginaryPart * other.imaginaryPart;
        double newRealPart = (this.realPart * other.realPart + this.imaginaryPart * other.imaginaryPart) / denominator;
        double newImaginaryPart = (this.imaginaryPart * other.realPart - this.realPart * other.imaginaryPart)
                / denominator;
        return new Complex(newRealPart, newImaginaryPart);
    }

    public double abs() {
        return Math.sqrt(realPart * realPart + imaginaryPart * imaginaryPart);
    }

    @Override
    public String toString() {
        if (imaginaryPart == 0) {
            return String.format("%.1f", realPart);
        } else {
            return String.format("%.1f + %.1fi", realPart, imaginaryPart);
        }
    }

    @Override
    public int compareTo(Complex other) {
        return Double.compare(this.abs(), other.abs());
    }
}


class ComplexNumberTest {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the first complex number: ");
            double realPart1 = scanner.nextDouble();
            double imaginaryPart1 = scanner.nextDouble();
            Complex complex1 = new Complex(realPart1, imaginaryPart1);

            System.out.print("Enter the second complex number: ");
            double realPart2 = scanner.nextDouble();
            double imaginaryPart2 = scanner.nextDouble();
            Complex complex2 = new Complex(realPart2, imaginaryPart2);

            Complex sum = complex1.add(complex2);
            Complex difference = complex1.subtract(complex2);
            Complex product = complex1.multiply(complex2);
            Complex quotient = complex1.divide(complex2);
            double absolute = complex1.abs();

            System.out.println(complex1 + " + " + complex2 + " = " + sum);
            System.out.println(complex1 + " - " + complex2 + " = " + difference);
            System.out.println(complex1 + " * " + complex2 + " = " + product);
            System.out.println(complex1 + " / " + complex2 + " = " + quotient);
            System.out.println("|" + complex1 + "| = " + absolute);
        }
    }
}
