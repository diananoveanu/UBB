package domain;

public class Transaction {
    private String tid;
    private String ac1id;
    private String ac2id;
    private String timestamp;
    private Double amount;
    private Boolean type;

    public Transaction(String tid, String ac1id, String ac2id, String timestamp, Double amount, Boolean type) {
        this.tid = tid;
        this.ac1id = ac1id;
        this.ac2id = ac2id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.type = type;
    }

    public Transaction() {
    }

    public String getAc1id() {
        return ac1id;
    }

    public void setAc1id(String ac1id) {
        this.ac1id = ac1id;
    }

    public String getAc2id() {
        return ac2id;
    }

    public void setAc2id(String ac2id) {
        this.ac2id = ac2id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String toString(){
        return "TransId: " + tid + "\n" + "From: " + ac1id + "\n" + "To: " + ac2id + "\n" +
        "TimeStamp: " + timestamp + "\n" + "Amount: " + amount + "\n" + "Type: " + type + "\n";
    }

}
