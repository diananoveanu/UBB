package domain;

public class Account {
    private String name;
    private Integer id;
    private Double balance;

    public Account(String name, Integer id, Double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String toString() {
        return "Name: " + this.name + "\n" + "AccId: " + this.id + "\n" + "Balance: " + this.balance + "\n";
    }
}



