package domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Log {
    private String aId;
    private List<Record> records = new ArrayList<>();

    public Log(String aId) {
        this.aId = aId;
    }

    public void addRecord(Record r) {
       records.add(r);
    }

    public List<Record> getRecords() {
        return this.records;
    }
}
