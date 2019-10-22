package domain;

public class Transaction {
    private Integer tid;
    private Integer ac1id;
    private Integer ac2id;
    private String timestamp;
    private Double amount;
    private Boolean type;

    public Transaction(Integer tid, Integer ac1id, Integer ac2id, String timestamp, Double amount, Boolean type) {
        this.tid = tid;
        this.ac1id = ac1id;
        this.ac2id = ac2id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.type = type;
    }

    public Transaction() {
    }

    public Integer getAc1id() {
        return ac1id;
    }

    public void setAc1id(Integer ac1id) {
        this.ac1id = ac1id;
    }

    public Integer getAc2id() {
        return ac2id;
    }

    public void setAc2id(Integer ac2id) {
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

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String toString(){
        return "TransId: " + tid + "\n" + "From: " + ac1id + "\n" + "To: " + ac2id + "\n" +
        "TimeStamp: " + timestamp + "\n" + "Amount: " + amount + "\n" + "Type: " + type + "\n";
    }

}
