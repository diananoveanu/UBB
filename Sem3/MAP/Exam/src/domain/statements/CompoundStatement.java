package domain.statements;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.stack.IStack;
import domain.exceptions.GenericException;
import domain.types.Type;

public class CompoundStatement implements IStatement {
    IStatement s1;
    IStatement s2;

    public CompoundStatement(IStatement s1, IStatement s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        IStack<IStatement> stack = state.getExecutionStack();
        stack.push(s2);
        stack.push(s1);
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        return s2.typeCheck(s1.typeCheck(typeEnv));
    }

    @Override
    public String toString() {
        return s1.toString() + ";\n" + s2.toString();
    }
}
