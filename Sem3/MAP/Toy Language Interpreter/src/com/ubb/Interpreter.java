package com.ubb;

import com.ubb.controller.Controller;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.expressions.*;
import com.ubb.domain.statements.*;
import com.ubb.domain.statements.files.CloseRFile;
import com.ubb.domain.statements.files.OpenRFile;
import com.ubb.domain.statements.files.ReadFile;
import com.ubb.domain.type.BooleanType;
import com.ubb.domain.type.IntegerType;
import com.ubb.domain.type.RefType;
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

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        IStatement ex3 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntegerType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))),
                                new CompoundStatement(
                                        new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a"))
                                        )
                                )
                        )
                )
        );


        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        IStatement ex4 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntegerType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))),
                                new CompoundStatement(
                                        new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))), new ValueExpression(new IntegerValue(5)), 1))
                                        )
                                )
                        )
                )
        );


        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStatement ex5 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntegerType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(
                                        new WriteHeapStatement("v", new ValueExpression(new IntegerValue(30))),
                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v")))
                                )
                        )
                )
        );

        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement ex6 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntegerType()),
                new CompoundStatement(
                        new AssignStatement("v", new ValueExpression(new IntegerValue(4))),
                        new CompoundStatement(
                                new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntegerValue(0)), 5),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntegerValue(-1)), 1)))),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );
//        int v;
//        Ref int a;
//        v=10;
//        new(a,22);
//        fork(wH(a,30);
//        print(v);
//        print(rH(a)));
//        print(v);
//        print(rH(a))
        IStatement ex7 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntegerType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new RefType(new IntegerType())),
                        new CompoundStatement(
                                new AssignStatement("v", new ValueExpression(new IntegerValue(10))),
                                new CompoundStatement(
                                        new HeapAllocationStatement("a", new ValueExpression(new IntegerValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(new WriteHeapStatement("a", new ValueExpression(new IntegerValue(30)))),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new CompoundStatement(
                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VariableExpression("v")),
                                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
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
        List<ProgramState> prg3 = new ArrayList<>();
        List<ProgramState> prg4 = new ArrayList<>();
        List<ProgramState> prg5 = new ArrayList<>();
        List<ProgramState> prg6 = new ArrayList<>();
        List<ProgramState> prg7 = new ArrayList<>();

        prg1.add(new ProgramState(ex1));
        prg2.add(new ProgramState(ex2));
        prg3.add(new ProgramState(ex3));
        prg4.add(new ProgramState(ex4));
        prg5.add(new ProgramState(ex5));
        prg6.add(new ProgramState(ex6));
        prg7.add(new ProgramState(ex7));

        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctrl1 = new Controller(repo1);
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller ctrl2 = new Controller(repo2);
        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller ctrl3 = new Controller(repo3);
        IRepository repo4 = new Repository(prg4, "log4.txt");
        Controller ctrl4 = new Controller(repo4);
        IRepository repo5 = new Repository(prg5, "log5.txt");
        Controller ctrl5 = new Controller(repo5);
        IRepository repo6 = new Repository(prg6, "log6.txt");
        Controller ctrl6 = new Controller(repo6);
        IRepository repo7 = new Repository(prg7, "log7.txt");
        Controller ctrl7 = new Controller(repo7);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", "\n" + ex1.toString(), ctrl1));
        menu.addCommand(new RunExampleCommand("2", "\n" + ex2.toString(), ctrl2));
        menu.addCommand(new RunExampleCommand("3", "\n" + ex3.toString(), ctrl3));
        menu.addCommand(new RunExampleCommand("4", "\n" + ex4.toString(), ctrl4));
        menu.addCommand(new RunExampleCommand("5", "\n" + ex5.toString(), ctrl5));
        menu.addCommand(new RunExampleCommand("6", "\n" + ex6.toString(), ctrl6));
        menu.addCommand(new RunExampleCommand("7", "\n" + ex7.toString(), ctrl7));
        menu.show();
    }
}
