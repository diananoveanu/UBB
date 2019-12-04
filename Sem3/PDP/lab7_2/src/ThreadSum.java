import java.util.ArrayList;
import java.lang.Math;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreadSum extends Thread{
    private ArrayList<BigNum> numbers;
    private int id;
    private CyclicBarrier barrier;

    public ThreadSum(int id, ArrayList<BigNum> nums, CyclicBarrier barr){
        this.id = id;
        this.numbers = nums;
        this.barrier = barr;
    }

    public void run(){
        int log = (int)((int)Math.log(numbers.size()) / Math.log(2)) + 1;
        //33
        //|  \
        //|   \
        //|    \
        //|     \
        //|      \
        //12      21
        //| \     |\
        //|  \    | \
        //|   \   |  \
        //|    \  |   \
        //6    6  6   15
        //|\  | \ | \ | \
        //1 5 4 2 1 5 6 9
        //0 1 2 3 4 5 6 7
        //0 1   //0 2 //0 4
        //2 3   //4 6
        //4 5
        //6 7
        int p2 = 2;
        for(int level = 0; level < log; level ++){
            if(id % p2 == 0){
                int to_add = id + p2 / 2;
                numbers.set(id, numbers.get(id).add(numbers.get(to_add)));
            }
            p2 *= 2;
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
