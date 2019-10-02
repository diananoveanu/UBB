package com.ubb;

import com.ubb.controller.Controller;
import com.ubb.model.Apple;
import com.ubb.model.Array;
import com.ubb.model.ObjectInter;
import com.ubb.repository.Repository;


public class Main {

    public static void main(String[] args) {
        Repository repository = new Repository();
        Controller controller = new Controller(repository);


        controller.add(new Apple(1,30));
        controller.add(new Apple(2,55));
        controller.add(new Apple(3,400));

        Array over200  = controller.getAllOver();

        for(int i = 0; i<over200.getSize(); i++){
            System.out.println(over200.getObjects()[i].getType());
        }

    }
}
