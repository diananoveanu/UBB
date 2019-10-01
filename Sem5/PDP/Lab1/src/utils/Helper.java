package utils;

import domain.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Helper {

    public Helper() {

    }

    private static Account generateRandomAccount(){
        String name = new RandomString(8, new Random()).nextString();
        String id = new RandomString(8, new Random()).nextString();
        Random r = new Random();

        Double randomValue = 50 + (20000000 - 50) * r.nextDouble();
        return new Account(name, id, randomValue);
    }

    public static List<Account> generateAccounts(int numAccounts){
        List<Account> accounts = new ArrayList<>();
        for(int i = 0; i<numAccounts; i++){
            accounts.add(generateRandomAccount());
        }

        return accounts;
    }

}
