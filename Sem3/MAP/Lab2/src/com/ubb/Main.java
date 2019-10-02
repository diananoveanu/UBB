package com.ubb;

import com.ubb.controller.Controller;
import com.ubb.repository.Repository;
import com.ubb.view.Console;


public class Main {

    public static void main(String[] args) {
        Repository repository = new Repository();
        Controller controller = new Controller(repository);
        Console ui = new Console(controller);

        ui.runApplication();
    }
}
