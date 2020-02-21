package domain.adt.procedure;

import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.utils.ProcedurePair;

import java.util.Map;

public class MyProcedure implements IProcedure {
    private IDictionary<String, ProcedurePair> procedureMap;

    public MyProcedure() {
        procedureMap = new MyDictionary<>();
    }

    @Override
    public void put(String key, ProcedurePair value) {
        procedureMap.put(key, value);
    }

    @Override
    public ProcedurePair get(String key) {
        return procedureMap.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return procedureMap.containsKey(key);
    }

    @Override
    public Map<String, ProcedurePair> getContent() {
        return this.procedureMap.getMap();
    }
}
