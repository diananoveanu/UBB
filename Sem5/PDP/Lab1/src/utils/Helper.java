package utils;

import domain.Account;
import domain.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Helper {

    public Helper() {

    }

    private static Account generateRandomAccount() {
        String name = new RandomString(8, new Random()).nextString();
        Random r = new Random();

        Double randomValue = 50 + (200000 - 50) * r.nextDouble();
        Integer id = r.nextInt(100000);
        return new Account(name, id, randomValue);
    }

    public static HashMap<Integer, Account> generateAccounts(int numAccounts) {
        HashMap<Integer, Account> accounts = new HashMap<>();
        for (int i = 0; i < numAccounts; i++) {
            while (true) {
                Account acc = generateRandomAccount();
                Account inDict = accounts.get(acc.getId());
                if (inDict == null) {
                    accounts.put(acc.getId(), acc);
                    break;
                }
            }
        }
        return accounts;
    }

    public static HashMap<Integer, Transaction> generateTransactions(int numTransactions, HashMap<Integer, Account> accLst){
        // function to generate a specified number of transactions
        HashMap<Integer, Transaction> trans = new HashMap<>();
        for(int i = 0; i < numTransactions; i++){
            while (true){
                Transaction tr = generateRandomTransaction(accLst);
                Transaction inDict = trans.get(tr.getTid());
                if(inDict == null){
                    trans.put(tr.getTid(), tr);
                    break;
                }
            }
        }
        return trans;
    }

    public static Transaction generateRandomTransaction(HashMap<Integer, Account> accLst){
        // function to generate a single transaction
        List<Integer> accounts = new ArrayList<>(accLst.keySet());
        Random rand = new Random();
        Integer randomAcc1 = accounts.get(rand.nextInt(accounts.size()));
        Integer randomAcc2 = accounts.get(rand.nextInt(accounts.size()));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        int type = rand.nextInt(2);
        boolean typeBool;
        typeBool = type == 1;
        Random r = new Random();
        Integer id = r.nextInt(100000);
        Double ammount = rand.nextDouble() * 10000;
        return new Transaction(id, randomAcc1, randomAcc2, now.toString(), ammount, typeBool);
    }
}