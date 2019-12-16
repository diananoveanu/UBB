import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;


public class main {

    // function to fill number with 0's to make numbers of equal length
    static int untilPowerOfTwo(int n) {
        while ((n & (n - 1)) != 0) {
            n++;
        }
        return n;
    }

    // generate an array of n big numbers
    static ArrayList<BigNum> generateNumbers(int n) {
        // n - number of numbers to be generated
        ArrayList<BigNum> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int k = (int) ((Math.random() * ((100 - 10) + 1)) + 10);
            int dig = (int) ((Math.random() * ((10 - 1))) + 1);
            StringBuilder str = new StringBuilder(Integer.toString(dig));
            for (int j = 1; j < k; j++) {
                str.append((int) ((Math.random() * (10) + 1)));
            }
            nums.add(new BigNum(str.toString()));
        }
        return nums;
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<BigNum> nums = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        int n = 3;
        nums = generateNumbers(n);
        // display numbers
        for (int i = 0; i < nums.size(); i++) {
            System.out.println("Number " + (i + 1) + ": " + nums.get(i));
        }

        int oldN = n;

        // raise n until it becomes a power of 2
        n = untilPowerOfTwo(n);
        for (int i = oldN; i < n; i++) {
            nums.add(new BigNum("0"));
        }

        CyclicBarrier barr = new CyclicBarrier(n-1);

        double start = System.currentTimeMillis();
        // start threads
        for (int i = 0; i < n-1; i++) {
            threads.add(new ThreadSum(i, nums, barr));
            threads.get(i).start();
        }

        // join threads
        for (int i = 0; i < n-1; i++) {
            threads.get(i).join();
        }

        double end = System.currentTimeMillis();
        double time = ((double) (end - start) / 1000F);

        System.out.println("Sum: " + nums.get(0));
        System.out.println("Time taken: " + time);
    }
}
