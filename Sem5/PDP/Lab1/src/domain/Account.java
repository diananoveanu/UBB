package domain;
/*
At a bank, we have to keep track of the balance of some accounts.
Also, each account has an associated log (the list of records of operations performed on that account).
Each operation record shall have a unique serial number, that is incremented for each operation performed in the bank.

We have concurrently run transfer operations, to be executer on multiple threads.
Each operation transfers a given amount of money from one account to someother account,
and also appends the information about the transfer to the logs of both accounts.

From time to time, as well as at the end of the program, a consistency check shall be executed.
It shall verify that the amount of money in each account corresponds with the operations records associated to that account,
and also that all operations on each account appear also in the logs of the source or destination of the transfer. * */

import utils.RandomString;

import java.util.List;

public class Account {
    private String name;
    private String id;
    private Double balance;

    public Account(String name, String id, Double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String toString(){
        return "Name: " + this.name + "\n" + "AccId: " + this.id + "\n" + "Balance: " + this.balance + "\n";
    }
}



