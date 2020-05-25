package FractionMV;

/**
 * Hello world!
 *
 */
public class Fraction {
    private int numerator, denominator;
    public Fraction(int num, int den) {
        numerator = num;
        denominator = den;
    }
    public void Simplify() {
        long gcd = GCD(denominator, numerator);
        denominator /= gcd;
        numerator /= gcd;

        if(denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }


    }
    private static int GCD(int a, int b) {
        return b == 0 ? a : GCD(b, a % b);
    }
    public int getDenominator() {
        return denominator;
    }
    public int getNumerator() {
        return numerator;
    }
    public void setDenominator(int i) {
        denominator = i;
    }
    public void setNumerator(int i) {
        numerator = i;
    }
}
