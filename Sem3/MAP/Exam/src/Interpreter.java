import controller.Controller;
import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.expressions.ArithmeticExpression;
import domain.expressions.ReadHeapExpression;
import domain.expressions.ValueExpression;
import domain.expressions.VariableExpression;
import domain.expressions.enums.ArithmeticOperation;
import domain.statements.*;
import domain.statements.exam.ForStatement;
import domain.statements.exam.barrier.AwaitStatement;
import domain.statements.exam.barrier.NewBarrierStatement;
import domain.statements.heap.AllocateHeapStatement;
import domain.statements.heap.WriteHeapStatement;
import domain.types.IntegerType;
import domain.types.ReferenceType;
import domain.types.Type;
import domain.values.IntegerValue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafxControllers.ToyLanguageController;
import repository.IRepository;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class Interpreter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Toy Language Interpreter");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./view/ToyLanguageInterpreter.fxml"));
        Pane pane;
        pane = (Pane) fxmlLoader.load();
        ToyLanguageController controller = fxmlLoader.getController();
        controller.setControllers(getControllers());
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();

    }

    public ArrayList<Controller> getControllers() {

        /*
         Ref int a;
         new(a,20);
         for (v=0;v<3;v=v+1) {
            fork{
                print(v);
                v=v*rh(a)
            }
         };
         print(rh(a))
         */

        IStatement exFor = new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntegerType())),
                new CompoundStatement(new AllocateHeapStatement("a", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(
                                new ForStatement(new ValueExpression(new IntegerValue(0)),
                                        new ValueExpression(new IntegerValue(3)),
                                        new ValueExpression(new IntegerValue(1)),
                                        new ForkStatement(
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new AssignStatement("v",
                                                                new ArithmeticExpression(
                                                                        new VariableExpression("v"),
                                                                        new ReadHeapExpression(
                                                                                new VariableExpression("a")),
                                                                        ArithmeticOperation.MULTIPLICATION))
                                                )
                                        )),
                                new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))));


        /*
        Ref int v1;
        Ref int v2;
        Ref int v3;
        int cnt;
        new(v1,2);
        new(v2,3);
        new(v3,4);
        newBarrier(cnt,rH(v2));
        fork{
            await(cnt);
            wh(v1,rh(v1)*10);
            print(rh(v1));
        };
        fork{
            await(cnt);
            wh(v2,rh(v2)*10);
            print(rh(v2));

        };
        wh(v2,rh(v2)*10)
        await(cnt);
        print(rH(v3))
         */
        IStatement exBarrier = new CompoundStatement(
                new VariableDeclarationStatement("v1",
                        new ReferenceType(
                                new IntegerType())),
                new CompoundStatement(
                        new VariableDeclarationStatement("v2",
                                new ReferenceType(
                                        new IntegerType())),
                        new CompoundStatement(
                                new VariableDeclarationStatement("v3",
                                        new ReferenceType(
                                                new IntegerType())),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("cnt",
                                                new IntegerType()),
                                        new CompoundStatement(
                                                new AllocateHeapStatement("v1",
                                                        new ValueExpression(
                                                                new IntegerValue(2))),
                                                new CompoundStatement(
                                                        new AllocateHeapStatement("v2",
                                                                new ValueExpression(
                                                                        new IntegerValue(3))),
                                                        new CompoundStatement(
                                                                new AllocateHeapStatement("v3",
                                                                        new ValueExpression(
                                                                                new IntegerValue(4))),
                                                                new CompoundStatement(
                                                                        new NewBarrierStatement("cnt",
                                                                                new ReadHeapExpression(
                                                                                        new VariableExpression("v2"))),
                                                                        new CompoundStatement(
                                                                                new ForkStatement(
                                                                                        new CompoundStatement(
                                                                                                new AwaitStatement("cnt"),
                                                                                                new CompoundStatement(
                                                                                                        new WriteHeapStatement("v1",
                                                                                                                new ArithmeticExpression(
                                                                                                                        new ReadHeapExpression(
                                                                                                                                new VariableExpression("v1")),
                                                                                                                        new ValueExpression(
                                                                                                                                new IntegerValue(10)),
                                                                                                                        ArithmeticOperation.MULTIPLICATION)),
                                                                                                        new PrintStatement(
                                                                                                                new ReadHeapExpression(
                                                                                                                        new VariableExpression("v1")))
                                                                                                )
                                                                                        )
                                                                                ),
                                                                                new CompoundStatement(
                                                                                        new ForkStatement(
                                                                                                new CompoundStatement(
                                                                                                        new AwaitStatement("cnt"),
                                                                                                        new CompoundStatement(
                                                                                                                new WriteHeapStatement("v2",
                                                                                                                        new ArithmeticExpression(
                                                                                                                                new ReadHeapExpression(
                                                                                                                                        new VariableExpression("v2")),
                                                                                                                                new ValueExpression(
                                                                                                                                        new IntegerValue(10)),
                                                                                                                                ArithmeticOperation.MULTIPLICATION)),
                                                                                                                new PrintStatement(
                                                                                                                        new ReadHeapExpression(
                                                                                                                                new VariableExpression("v2")))
                                                                                                        )
                                                                                                )
                                                                                        ),
                                                                                        new CompoundStatement(
                                                                                                new WriteHeapStatement("v2",
                                                                                                        new ArithmeticExpression(
                                                                                                                new ReadHeapExpression(
                                                                                                                        new VariableExpression("v2")),
                                                                                                                new ValueExpression(
                                                                                                                        new IntegerValue(10)),
                                                                                                                ArithmeticOperation.MULTIPLICATION)),
                                                                                                new CompoundStatement(
                                                                                                        new AwaitStatement("cnt"),
                                                                                                        new PrintStatement(
                                                                                                                new ReadHeapExpression(
                                                                                                                        new VariableExpression("v3")))
                                                                                                )
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        List<ProgramState> prgFor = new ArrayList<>();
        List<ProgramState> prgBarrier = new ArrayList<>();

        IDictionary<String, Type> dictFor = new MyDictionary<>();
        IDictionary<String, Type> dictBarrier = new MyDictionary<>();

        exFor.typeCheck(dictFor);
        exBarrier.typeCheck(dictBarrier);

        prgFor.add(new ProgramState(exFor));
        prgBarrier.add(new ProgramState(exBarrier));

        IRepository repository1 = new Repository(prgFor, "logFor.txt");
        Controller controller1 = new Controller(repository1);
        IRepository repository2 = new Repository(prgBarrier, "logBarrier.txt");
        Controller controller2 = new Controller(repository2);

        ArrayList<Controller> controllers = new ArrayList<>();

        controllers.add(controller1);
        controllers.add(controller2);
        return controllers;
    }
}
