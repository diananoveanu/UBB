import domain.*;
import utils.Helper;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/*
At a bank, we have to keep track of the balance of some accounts.
Also, each account has an associated log (the list of records of operations performed on that account).
Each operation record shall have a unique serial number, that is incremented for each operation performed in the bank.

We have concurrently run transfer operations, to be executer on multiple threads.
Each operation transfers a given amount of money from one account to someother account,
and also appends the information about the transfer to the logs of both accounts.

From time to time, as well as at the end of the program, a consistency check shall be executed.
It shall verify that the amount of money in each account corresponds with the operations records associated to that account,
and also that all operations on each account appear also in the logs of the source or destination of the transfer.
*/

public class main {
    private static HashMap<Integer, Log> getLogMap(HashMap<Integer, Account> accounts) {
        // Creates a log for every account
        HashMap<Integer, Log> map = new HashMap<>();
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
            Account acc = entry.getValue();
            map.put(acc.getId(), new Log(acc.getId()));
        }
        return map;
    }

    private static HashMap<Integer, Account> copyAccMap(HashMap<Integer, Account> accMap) {
        // Copies the account map (for sequential use)
        HashMap<Integer, Account> accs = new HashMap<>();
        for (Map.Entry<Integer, Account> entry : accMap.entrySet()) {
            Account acc = entry.getValue();
            accs.put(acc.getId(), new Account(acc.getName(), acc.getId(), acc.getBalance()));
        }

        return accs;
    }

    public static void main(String[] args) {

        Integer NUM_OF_THREADS = 10; //number of threads
        Integer NUM_ACCOUTNS = 20000; //number of accounts
        Integer NUM_TRANS = 500; //number of transactions
        Integer NUM_ITER = 5; //number of iterations
        Double parallelSum = 0.0; //total parallel sum
        Double seqSum = 0.0; //total sequential sum
        boolean final_same = true; //final consistency

        for(int iter = 0; iter < NUM_ITER; iter ++) {
            HashMap<Integer, Account> accMap = Helper.generateAccounts(NUM_ACCOUTNS); //generate random accounts
            HashMap<Integer, Log> logMap = getLogMap(accMap); //create log for each account
            HashMap<Integer, Transaction> transMap = Helper.generateTransactions(NUM_TRANS, accMap); //create random transactions
            List<Record> records = getRecordsFromTrans(transMap); //create records for transactions
            List<Thread> threads = new ArrayList<>(); //array of threads
            ReentrantLock mutex = new ReentrantLock(); //mutex
            HashMap<Integer, Account> accCopyMap = copyAccMap(accMap); //copy account map

        /*
        for (Map.Entry<Integer, Account> entry : accMap.entrySet()) {
            System.out.println(entry.getValue());
        }

        System.out.println("======================================");
        for (Map.Entry<Integer, Transaction> entry : transMap.entrySet()) {
            System.out.println(entry.getValue());
        }

        System.out.println("======================================");
        records.forEach(System.out::println);

        System.out.println("======================================");
        */

            /* THREADS LOGIC */
            int perThread = NUM_TRANS / NUM_OF_THREADS; //number of transactions performed by each thread
            int remain = NUM_TRANS % NUM_OF_THREADS; //number of transactions which would remain undone
            int start = 0; //start position
            int stop = 0; //end position
            long startTime = System.currentTimeMillis(); //calculate startTime
            for (int i = 0; i < NUM_OF_THREADS; i++) {
                stop = start + perThread;   //stop index of current tread
                if (remain > 0) { //if remain left (nb of transactions that remained undone)
                    stop += 1; //add one more step to the current thread
                    remain -= 1;
                }
                Thread th = new Multithread(accMap, records, start, stop, mutex, logMap);
                threads.add(th); //create a thread and add it to the thread list
                start = stop; //next start index will be the last stop index
            }
            threads.forEach(Thread::start); //start all threads

            threads.forEach(thread -> {
                try {
                    thread.join();   //join threads
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            long endTime = System.currentTimeMillis(); //calculate end time
            Double parallelTime = (double) (endTime - startTime) / (1000F); //convert time difference to seconds

            //Sequential Logic
            startTime = System.currentTimeMillis();
            boolean same = verify(accCopyMap, records, accMap);
            if(same == false){
                final_same = false;
            }
            endTime = System.currentTimeMillis();
            Double seqTime = (double) (endTime - startTime) / (1000F);
            parallelSum += parallelTime;
            seqSum += seqTime;
        }
        System.out.println("Parallel time: " + parallelSum / NUM_ITER);
        System.out.println("Sequential time: " + seqSum / NUM_ITER);
        System.out.println("Consistency: " + final_same);
    }

    private static List<Record> getRecordsFromTrans(HashMap<Integer, Transaction> transMap) {
        List<Record> records = new ArrayList<>();
        Random rand = new Random();
        transMap.values().forEach(t -> records.add(new Record(rand.nextInt(10000), t)));
        return records;
    }

    private static boolean verify(HashMap<Integer, Account> accounts, List<Record> records, HashMap<Integer, Account> newAccMap) {
        // Verification done by executing all the transactions sequentially, then comparing the results (balance of each account)
        records.forEach(r -> {
            Transaction tr = r.getTransaction();
            Integer accid1 = tr.getAc1id();
            Integer accid2 = tr.getAc2id();
            Double amount = tr.getAmount();
            Account acc1 = accounts.get(accid1);
            Account acc2 = accounts.get(accid2);
            if (tr.getType()) {
                if (acc1.getBalance() - amount > 0) {
                    acc1.setBalance(acc1.getBalance() - amount);
                    acc2.setBalance(acc2.getBalance() + amount);
                    accounts.put(accid1, acc1);
                    accounts.put(accid2, acc2);
                }
            } else {
                if (acc2.getBalance() - amount > 0) {
                    acc2.setBalance(acc2.getBalance() - amount);
                    acc1.setBalance(acc1.getBalance() + amount);
                    accounts.put(accid1, acc1);
                    accounts.put(accid2, acc2);
                }
            }
        });

        for (Map.Entry<Integer, Account> entrySet : accounts.entrySet()) {
            Integer key = entrySet.getKey();
            Account acc1 = accounts.get(key);
            Account acc2 = newAccMap.get(key);
            if (!acc1.getBalance().equals(acc2.getBalance())) {
                return false;
            }
        }
        return true;
    }
}
