package domain.adt.utils;

import domain.statements.IStatement;

import java.util.ArrayList;

public class ProcedurePair implements IPair<ArrayList<String>, IStatement> {
    private ArrayList<String> parameters;
    private IStatement statement;

    public ProcedurePair(ArrayList<String> params, IStatement stmt){
        this.parameters = params;
        this.statement = stmt;
    }

    @Override
    public ArrayList<String> getKey() {
        return parameters;
    }

    @Override
    public IStatement getValue() {
        return statement;
    }

    @Override
    public void setKey(ArrayList<String> strings) {
        parameters = strings;
    }

    @Override
    public void setValue(IStatement iStatement) {
        statement = iStatement;
    }

    @Override
    public String toString() {
        return "( " + parameters.toString() +" ," + statement.toString() +" )";
    }
}
