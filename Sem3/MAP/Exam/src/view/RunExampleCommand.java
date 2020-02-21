package view;

import controller.Controller;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}