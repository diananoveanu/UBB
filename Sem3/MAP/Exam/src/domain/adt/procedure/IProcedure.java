package domain.adt.procedure;

import domain.adt.utils.ProcedurePair;

import java.util.Map;

public interface IProcedure {

    void put(String key, ProcedurePair value);

    ProcedurePair get(String key);

    boolean containsKey(String key);

    Map<String, ProcedurePair> getContent();
}
