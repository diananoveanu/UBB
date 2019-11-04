package com.ubb.view;

import com.ubb.adt.list.MyList;
import com.ubb.controller.Controller;
import com.ubb.domain.statements.IStmt;

public class Console {

    private Controller ctrl;
    private MyList<IStmt> statements;

    public Console(Controller ctrl, MyList<IStmt> statements) {
        this.ctrl = ctrl;
        this.statements = statements;
    }
}
