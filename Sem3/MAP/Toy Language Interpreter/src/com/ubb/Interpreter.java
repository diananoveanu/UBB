package com.ubb;

import com.ubb.controller.Controller;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.expressions.RelationalExpression;
import com.ubb.domain.expressions.ValueExpression;
import com.ubb.domain.expressions.VariableExpression;
import com.ubb.domain.statements.*;
import com.ubb.domain.statements.files.CloseRFile;
import com.ubb.domain.statements.files.OpenRFile;
import com.ubb.domain.statements.files.ReadFile;
import com.ubb.domain.type.BooleanType;
import com.ubb.domain.type.IntegerType;
import com.ubb.domain.type.StringType;
import com.ubb.domain.value.BooleanValue;
import com.ubb.domain.value.IntegerValue;
import com.ubb.domain.value.StringValue;
import com.ubb.repository.IRepository;
import com.ubb.repository.Repository;
import com.ubb.view.ExitCommand;
import com.ubb.view.RunExampleCommand;
import com.ubb.view.TextMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    public static void main(String[] args) throws GenericException, IOException {
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("a", new BooleanType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BooleanValue(true))),
                                new CompoundStatement(new IfStatement(new RelationalExpression(new ValueExpression(new IntegerValue(2)), new ValueExpression(new IntegerValue(4)), 5),
                                        new AssignStatement("v", new ValueExpression(new IntegerValue(2))),
                                        new AssignStatement("v", new ValueExpression(new IntegerValue(3)))),
                                        new CompoundStatement(new OpenRFile(new ValueExpression(new StringValue("test.in"))),
                                                new CompoundStatement(new ReadFile(new ValueExpression(new StringValue("test.in")), "v")
                                                        , new PrintStatement(new VariableExpression("v")))
                                        )))));
        IStatement ex2 = new CompoundStatement(
                new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(
                        new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(
                                new OpenRFile(new VariableExpression("varf")),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("varc", new IntegerType()),
                                        new CompoundStatement(
                                                new ReadFile(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(
                                                                new ReadFile(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseRFile(new VariableExpression("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );


        List<ProgramState> prg1 = new ArrayList<>();
        List<ProgramState> prg2 = new ArrayList<>();
        prg1.add(new ProgramState(ex1));
        prg2.add(new ProgramState(ex2));
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctrl1 = new Controller(repo1);
        IRepository repo2 = new Repository(prg1, "log2.txt");
        Controller ctrl2 = new Controller(repo2);
        ctrl1.allStep();
        ctrl2.allStep();


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", "\n" + ex1.toString(), ctrl1));
        menu.addCommand(new RunExampleCommand("2", "\n" + ex2.toString(), ctrl1));
        menu.show();
    }
}
