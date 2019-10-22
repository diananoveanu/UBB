package domain;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Multithread extends Thread {

    private HashMap<Integer, Account> accounts;
    private List<Record> recs;
    private ReentrantLock mutex;
    private HashMap<Integer, Log> logs;
    private int start;
    private int stop;

    public Multithread(HashMap<Integer, Account> accounts, List<Record> tr, int start, int stop, ReentrantLock m, HashMap<Integer, Log> logs) {
        super();
        this.accounts = accounts;
        this.recs = tr;
        this.mutex = m;
        this.logs = logs;
        this.start = start;
        this.stop = stop;
    }

    @Override
    public void start() {
        for (int i = start; i < stop; i++) {
            Record rec = recs.get(i);
            Transaction tr = rec.getTransaction();
            Integer accid1 = tr.getAc1id();
            Integer accid2 = tr.getAc2id();
            Double amount = tr.getAmount();
            Account acc1 = this.accounts.get(accid1);
            Account acc2 = this.accounts.get(accid2);
            if (tr.getType()) {
                if (acc1.getBalance() - amount > 0) {
                    Log acc1Log = this.logs.get(accid1);
                    Log acc2Log = this.logs.get(accid2);
                    acc1Log.addRecord(rec);
                    acc2Log.addRecord(rec);
                    mutex.lock();
                    this.logs.put(accid1, acc1Log);
                    this.logs.put(accid2, acc2Log);
                    acc1.setBalance(acc1.getBalance() - amount);
                    acc2.setBalance(acc2.getBalance() + amount);
                    accounts.put(accid1, acc1);
                    accounts.put(accid2, acc2);
                    mutex.unlock();
                }
            } else {
                if (acc2.getBalance() - amount > 0) {
                    Log acc1Log = this.logs.get(accid1);
                    Log acc2Log = this.logs.get(accid2);
                    acc1Log.addRecord(rec);
                    acc2Log.addRecord(rec);
                    mutex.lock();
                    this.logs.put(accid1, acc1Log);
                    this.logs.put(accid2, acc2Log);
                    acc2.setBalance(acc2.getBalance() - amount);
                    acc1.setBalance(acc1.getBalance() + amount);
                    accounts.put(accid1, acc1);
                    accounts.put(accid2, acc2);
                    mutex.unlock();
                }
            }
        }
    }
}
