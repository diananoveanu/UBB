package controllers;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.adt.list.MyIList;
import com.ubb.adt.stack.MyIStack;
import com.ubb.controller.Controller;
import com.ubb.domain.ProgramState;
import com.ubb.domain.statements.IStatement;
import com.ubb.domain.value.StringValue;
import com.ubb.domain.value.Value;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;

public class ToyLanguageController {
    @FXML
    public Button runBtn;
    @FXML
    public ListView<ProgramState> prgStatesList;
    @FXML
    public ListView<MyIList<Value>> outList;
    @FXML
    public ListView<MyIDictionary<StringValue, BufferedReader>> fileList;
    @FXML
    public ListView<MyIStack<IStatement>> exeStackList;
    @FXML
    public TableView<MyIDictionary<String, Value>> symTable;
    @FXML
    public TableView<MyIDictionary<Integer, Value>> heapTable;
    @FXML
    public TextField progStatesNumField;
    @FXML
    private ListView<String> programsList;
    private Controller ctr;

    public ToyLanguageController() {

    }

    private void initialize() {
        //Link tables and columns
        //add data to lists and tables
    }

    public void setCtr(Controller ctr) {
        this.ctr = ctr;
    }

    public void runOneStepForAll(ActionEvent actionEvent) {
    }
}
