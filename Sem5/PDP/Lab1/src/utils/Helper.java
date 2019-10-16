package utils;

import domain.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Helper {

    public Helper() {

    }

    private static Account generateRandomAccount() {
        String name = new RandomString(8, new Random()).nextString();
        Random r = new Random();

        Double randomValue = 50 + (20000000 - 50) * r.nextDouble();
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
}