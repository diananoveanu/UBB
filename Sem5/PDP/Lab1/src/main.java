import domain.Account;
import domain.Log;
import domain.Multithread;
import domain.Transaction;
import utils.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//TODO do not use futures (only starting from lab 3)

public class main {

    private static HashMap<Integer, Log> getLogMap(HashMap<Integer, Account> accounts) {
        HashMap<Integer, Log> map = new HashMap<>();
        for (Map.Entry<Integer,Account> entry : accounts.entrySet()){
            Account acc = entry.getValue();
            map.put(acc.getId(), new Log(acc.getId()));
        }
        return map;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HashMap<Integer, Log> logMap = new HashMap<>();
        HashMap<Integer, Account> accMap = Helper.generateAccounts(10);
        logMap = getLogMap(accMap);
        ArrayList<Transaction> transactions;
        for(Map.Entry<Integer, Account> entry : accMap.entrySet()){
            System.out.println(entry.getValue());
        }

        Transaction t = new Transaction("t1", 58155, 34412, "124124", (double) 10000.0f, true);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<HashMap<Integer, Account>> result = executorService.submit(new Multithread(t, accMap));
        for(Map.Entry<Integer, Account> entry : result.get().entrySet()){
            System.out.println(entry.getValue());
        }


    }
}
