import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;


public class main {
    static int untilPowerOfTwo(int n){
        while ((n & (n - 1)) != 0){
            n ++;
        }
        return n;
    }

    static ArrayList<BigNum> generateNumbers(int n){
        ArrayList<BigNum> nums = new ArrayList<>();
        for(int i = 0; i<n; i++) {
            int k = (int) ((Math.random() * ((100 - 10) + 1)) + 10);
            int dig = (int) ((Math.random() * ((10 - 1))) + 1);
            StringBuilder str = new StringBuilder(Integer.toString(dig));
            for (int j = 1; j < k; j++) {
                str.append((int) ((Math.random() * (10) + 1)));
                nums.add(new BigNum(str.toString()));
            }
        }
        return nums;
    }


    public static void main(String[] args) throws InterruptedException {
        ArrayList<BigNum> nums = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        int n = 3;
        nums = generateNumbers(n);
        int oldN = n;
        n = untilPowerOfTwo(n);
        for(int i = oldN; i < n; i++){
            nums.add(new BigNum("0"));
        }
        CyclicBarrier barr = new CyclicBarrier(n);


        for(int i = 0; i<n; i++){
            threads.add(new ThreadSum(i, nums, barr));
            threads.get(i).start();
        }

        for(int i = 0; i<n; i++){
            threads.get(i).join();
        }

        System.out.println(nums.get(0));
    }
}
