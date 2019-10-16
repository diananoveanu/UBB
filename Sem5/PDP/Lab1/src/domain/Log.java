package domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Log {
    private Integer aId;
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
