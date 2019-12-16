package com.ubb.view;

import com.ubb.controller.Controller;

public class RunExampleCommand extends Command {
    private Controller controller;

    public RunExampleCommand(String key, String desc, Controller controller) {
        super(key, desc);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.allStep();
 //           controller.printPrg();
        } catch (Exception e) { //todo add other exceptions
            e.printStackTrace();
        }
    }
}