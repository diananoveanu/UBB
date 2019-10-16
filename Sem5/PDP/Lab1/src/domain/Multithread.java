package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class Multithread implements Callable<HashMap<Integer, Account>> {

    private Transaction trans;
    private HashMap<Integer, Account> accounts;

    public Multithread(Transaction t, HashMap<Integer, Account>  accs) {
        this.trans = t;
        this.accounts = accs;
    }

    @Override
    public HashMap<Integer, Account>  call() {
        Account acc1 = this.accounts.get(trans.getAc1id());
        Account acc2 = this.accounts.get(trans.getAc2id());
        Double amount = trans.getAmount();
        Boolean type = trans.getType();
        if(type){
            if(acc1.getBalance() - amount > 0) {
                acc1.setBalance(acc1.getBalance() - amount);
                acc2.setBalance(acc2.getBalance() + amount);
            }
        }else{
            if(acc2.getBalance() - amount > 0) {
                acc2.setBalance(acc2.getBalance() - amount);
                acc1.setBalance(acc1.getBalance() + amount);
            }
        }

        accounts.put(acc1.getId(), acc1);
        accounts.put(acc2.getId(), acc2);
        return accounts;
    }
}
