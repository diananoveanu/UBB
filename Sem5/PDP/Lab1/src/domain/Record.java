package domain;

public class Record {

    public Record(Integer id, Transaction transaction) {
        this.id = id;
        this.transaction = transaction;
    }

    private Integer id;
    private Transaction transaction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }


}
