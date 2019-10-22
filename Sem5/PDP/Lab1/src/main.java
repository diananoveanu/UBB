import domain.*;
import utils.Helper;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


public class main {
    private static HashMap<Integer, Log> getLogMap(HashMap<Integer, Account> accounts) {
        HashMap<Integer, Log> map = new HashMap<>();
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
            Account acc = entry.getValue();
            map.put(acc.getId(), new Log(acc.getId()));
        }
        return map;
    }

    private static HashMap<Integer, Account> copyAccMap(HashMap<Integer, Account> accMap) {
        HashMap<Integer, Account> accs = new HashMap<>();
        for (Map.Entry<Integer, Account> entry : accMap.entrySet()) {
            Account acc = entry.getValue();
            accs.put(acc.getId(), new Account(acc.getName(), acc.getId(), acc.getBalance()));
        }

        return accs;
    }

    public static void main(String[] args) {
        Integer NUM_OF_THREADS = 1;
        HashMap<Integer, Account> accMap = Helper.generateAccounts(2 * NUM_OF_THREADS);
        HashMap<Integer, Log> logMap = getLogMap(accMap);
        HashMap<Integer, Transaction> transMap = Helper.generateTransactions(NUM_OF_THREADS, accMap);
        List<Record> records = getRecordsFromTrans(transMap);
        List<Thread> threads = new ArrayList<>();
        ReentrantLock mutex = new ReentrantLock();
        HashMap<Integer, Account> accCopyMap = copyAccMap(accMap);

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

        records.forEach(t -> threads.add(new Multithread(accMap, t, mutex, logMap)));

        threads.forEach(Thread::start);

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        boolean same = verify(accCopyMap, records, accMap);
        System.out.println(same);
    }

    private static List<Record> getRecordsFromTrans(HashMap<Integer, Transaction> transMap) {
        List<Record> records = new ArrayList<>();
        Random rand = new Random();
        transMap.values().forEach(t -> records.add(new Record(rand.nextInt(10000), t)));
        return records;
    }

    private static boolean verify(HashMap<Integer, Account> accounts, List<Record> records, HashMap<Integer, Account> newAccMap) {
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
