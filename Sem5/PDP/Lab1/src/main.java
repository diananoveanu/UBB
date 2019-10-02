import domain.Account;
import domain.Log;
import domain.Multithread;
import domain.Transaction;
import utils.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

    private static Map<String, Log> getLogMap(List<Account> accounts){
        Map<String, Log> map = new HashMap<>();
        for(Account acc : accounts){
            map.put(acc.getId(), new Log(acc.getId()));
        }

        return map;
    }

    public static void main(String[] args) {
        Map<String, Log> logMap = new HashMap<>();
        List<Account> accList = Helper.generateAccounts(10);
        logMap = getLogMap(accList);
        ArrayList<Transaction> transactions;
        int n = 8; // Number of threads
        for (int i=0; i<8; i++)
        {
            Thread object = new Thread(new Multithread());
            object.start();
        }
    }
}
