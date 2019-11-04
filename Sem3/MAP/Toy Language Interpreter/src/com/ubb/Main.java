package com.ubb;

import com.ubb.controller.Controller;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.expressions.ValueExpression;
import com.ubb.domain.expressions.VariableExpression;
import com.ubb.domain.statements.*;
import com.ubb.domain.type.BooleanType;
import com.ubb.domain.type.IntegerType;
import com.ubb.domain.value.BooleanValue;
import com.ubb.domain.value.IntegerValue;
import com.ubb.repository.IRepository;
import com.ubb.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// oh shit, how boring this class is
        //Console.run();
        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BooleanType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BooleanValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignStatement("v",new ValueExpression(new IntegerValue(2))),
                                        new AssignStatement("v", new ValueExpression(new IntegerValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));

        List<ProgramState> prg1 = new ArrayList<>();
        prg1.add(new ProgramState(ex3));
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctrl1 = new Controller(repo1);
        try {
            ctrl1.allStep();
        } catch (GenericException e) {
            e.printStackTrace();
        }

        System.out.println(prg1.get(0).getOriginalProgram());
        System.out.println(prg1.get(0).getOut());
    }


}
