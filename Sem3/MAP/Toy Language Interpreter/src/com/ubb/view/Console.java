package com.ubb.view;

import com.ubb.adt.list.MyList;
import com.ubb.controller.Controller;
import com.ubb.domain.exceptions.InputException;
import com.ubb.domain.statements.IStatement;

import java.io.IOException;
import java.util.Scanner;

public class Console {

    private static Controller ctrl;
    private static MyList<IStatement> statements;

    public Console(Controller ctrl, MyList<IStatement> statements) {
        this.ctrl = ctrl;
        this.statements = statements;
    }

    private static void menu() {
        System.out.println("\n Welcome to my very own toy language interpreter");
        System.out.println("Now you have to choose between the statements I hardcoded in this marvellous app.");
        System.out.println("\n\t1\n\t2\n\t");
        System.out.println("-1: Exit.");
    }

    private static int getInteger(Scanner scanner) throws InputException {
        try {
            return Integer.parseInt((scanner.nextLine()));
        } catch (NumberFormatException e) {
            throw new InputException("Invalid integer");
        }
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        {
            while (true) {
                menu();
                System.out.print("Choose one oprion: ");
                int option = -1;
                try {
                    option = getInteger(scanner);
                    if (option == -1) break;
                    if (option < statements.size()) {

                        //ctrl.setProgram(new ProgramState(statements.get(option)));
                        ctrl.allStep();
                    }
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
            scanner.close();
        }
    }
}

