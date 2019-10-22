package domain;

import java.util.ArrayList;
import java.util.List;

public class Log {
    private Integer aId;
    private Integer serialNum;
    private List<Record> records = new ArrayList<>();

    public Log(Integer aId) {
        this.aId = aId;
    }

    public void addRecord(Record r) {
        records.add(r);
    }

    public List<Record> getRecords() {
        return this.records;
    }
}
