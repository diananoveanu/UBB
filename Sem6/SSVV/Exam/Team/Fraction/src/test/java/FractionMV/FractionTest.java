package FractionMV;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Unit test for simple App.
 */

public class FractionTest {

    private Fraction fc1, fc2, fc3;

    @Test
    public void testSimplify() {
        try {
            String[] arrayNumbers = getNumbersFromFile("src/test/java/FractionMV/GivenNumbers1.txt");
            fc1 = new Fraction(Integer.getInteger(arrayNumbers[0]).intValue(), Integer.getInteger(arrayNumbers[1]).intValue());
            fc1.Simplify();
            assertEquals(2, fc1.getNumerator());
            assertEquals(5, fc1.getDenominator());

            String[] arrayNumbers2 = getNumbersFromFile("src/test/java/FractionMV/GivenNumbers2.txt");
            fc2 = new Fraction(Integer.getInteger(arrayNumbers2[0]).intValue(), Integer.getInteger(arrayNumbers2[1]).intValue());
            fc2.Simplify();
            assertEquals(-25, fc2.getNumerator());
            assertEquals(7, fc2.getDenominator());
        }catch(IOException e){
            System.out.println("IOException ... ");
        }
    }

    @Test
    public void test_Denominator() {
        try {
            String[] arrayNumbers = getNumbersFromFile("src/test/java/FractionMV/GivenNumbers1.txt");
            fc1 = new Fraction(Integer.getInteger(arrayNumbers[0]).intValue(), Integer.getInteger(arrayNumbers[1]).intValue());
            int result = fc1.getDenominator();
            assertTrue("getDenominator() returned " + result + " instead of 30.", result == 30);

            String[] arrayNumbers2 = getNumbersFromFile("src/test/java/FractionMV/GivenNumbers2.txt");
            fc2 = new Fraction(Integer.getInteger(arrayNumbers2[0]).intValue(), Integer.getInteger(arrayNumbers2[1]).intValue());
            result = fc2.getNumerator();
            assertEquals(7, result);

            fc1.setDenominator(1);
            int result2 = fc1.getDenominator();
            assertTrue("getDenominator() returned " + result2 + " instead of 1.", result == 1);
            fc2.setNumerator(6);
            result2 = fc2.getNumerator();
            assertEquals(6, result2);
        }catch(IOException e){
            System.out.println("IOException ... ");
        }
    }

    public String[] getNumbersFromFile(String numbersFile) throws IOException
    {
        int n = 0;
        BufferedReader in = new BufferedReader(new FileReader(numbersFile));
        while ((in.readLine()) != null) {
            n++;
        }
        in.close();

        String[] la=new String[n];
        String s = new String();
        int i = 0;
        in = new BufferedReader(new FileReader(numbersFile));
        while ((s=in.readLine()) != null) {
            la[i] = s;
            i++;
        }
        in.close();
        return la;
    }


}
