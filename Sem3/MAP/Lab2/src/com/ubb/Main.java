package com.ubb;

import com.ubb.controller.Controller;
import com.ubb.model.Pasare;
import com.ubb.model.Vaca;
import com.ubb.model.Array;
import com.ubb.model.Porc;
import com.ubb.repository.Repository;


public class Main {

    public static void main(String[] args) {
        Repository repository = new Repository();
        Controller controller = new Controller(repository);


        controller.add(new Vaca(1, 30));
        controller.add(new Vaca(2, 55));
        controller.add(new Vaca(3, 400));
        controller.add(new Porc(4, 2));
        controller.add(new Pasare(5, 1));
        controller.add(new Pasare(12, 4));
        controller.add(new Porc(22, 4));

        Array over200 = controller.getAllOver();

        for (int i = 0; i < over200.getSize(); i++) {
            System.out.println(over200.getObjects()[i].getType());
        }

    }
}
