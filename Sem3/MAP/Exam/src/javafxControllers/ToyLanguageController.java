package javafxControllers;

import controller.Controller;
import domain.ProgramState;
import domain.adt.barrier.MyBarrier;
import domain.adt.dictionary.IDictionary;
import domain.adt.heap.IHeap;
import domain.adt.latch.MyLatch;
import domain.adt.list.IList;
import domain.adt.lock.MyLock;
import domain.adt.procedure.MyProcedure;
import domain.adt.semaphore.MySemaphoreTable;
import domain.adt.stack.IStack;
import domain.adt.toySemaphore.MyToySemaphoreTable;
import domain.adt.utils.Pair;
import domain.adt.utils.ProcedurePair;
import domain.adt.utils.Tuple;
import domain.statements.IStatement;
import domain.values.StringValue;
import domain.values.Value;
import dtos.GenericDto;
import dtos.HeapDto;
import dtos.SymDto;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToyLanguageController {
    @FXML
    public Button runBtn;
    @FXML
    public ListView<String> prgStatesList;
    @FXML
    public ListView<String> outList;
    @FXML
    public ListView<String> fileList;
    @FXML
    public ListView<String> exeStackList;
    @FXML
    public TableView<SymDto> symTable;
    @FXML
    public TableView<HeapDto> heapTable;
    @FXML
    public TextField progStatesNumField;
    @FXML
    public ListView<String> programsList;
    @FXML
    public TableColumn<SymDto, String> varNameColumn;
    @FXML
    public TableColumn<SymDto, Value> symValueColumn;
    @FXML
    public TableColumn<HeapDto, Integer> addressColumn;
    @FXML
    public TableColumn<HeapDto, Value> heapValueColumn;
    @FXML
    public TableView<GenericDto> genericTable;
    @FXML
    public TableColumn<GenericDto, String> genericKeyColumn;
    @FXML
    public TableColumn<GenericDto, String> genericValueColumn;
    @FXML
    private ListProperty<String> programsText = new SimpleListProperty<>();
    @FXML
    private ListProperty<HeapDto> heapDtos = new SimpleListProperty<>();
    @FXML
    private ListProperty<GenericDto> genericDtos = new SimpleListProperty<>();
    @FXML
    private ListProperty<SymDto> symDtos = new SimpleListProperty<>();
    @FXML
    private ListProperty<String> outLst = new SimpleListProperty<>();
    @FXML
    private ListProperty<String> files = new SimpleListProperty<>();
    @FXML
    private ListProperty<String> exeStackElements = new SimpleListProperty<>();
    @FXML
    private ListProperty<String> prgStatesIdentifiers = new SimpleListProperty<>();
    private ArrayList<Controller> ctrs;


    public ToyLanguageController() {

    }

    @FXML
    private void initialize() {
        ctrs = new ArrayList<>();
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        heapValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        symValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        varNameColumn.setCellValueFactory(new PropertyValueFactory<>("varName"));
        genericKeyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        genericValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        programsList.setOnMouseClicked(event -> {
            System.out.println("clicked on " + programsList.getSelectionModel().getSelectedIndex());
            initAllWidgets(programsList.getSelectionModel().getSelectedIndex(), 0);
        });
        prgStatesList.setOnMouseClicked(event -> {
            initAllWidgets(programsList.getSelectionModel().getSelectedIndex(), prgStatesList.getSelectionModel().getSelectedIndex());
        });
    }

    private void initAllWidgets(int selectedIndex, int pState) {
        Controller ctr = this.ctrs.get(selectedIndex);
        ProgramState prgState = ctr.getProgramStates().get(pState);
        initHeapTbl(prgState);
        initSymTbl(prgState);
        initOutList(prgState);
        initFileLst(prgState);
        initExeStackLst(prgState);
        initPrgStatesLst(ctr);
        initNumProgStates(ctr);
        initGenericTable(prgState);
    }

    private void initGenericTable(ProgramState programState) {
        //Semaphore
//        initSemaphore(programState);
        //Barrier
//        initBarrier(programState);
        //ToySemaphore
//        initToySemaphore(programState);
        //LockMechanism
//        initLock(programState);
        //CountDownLatch
//        initCountDownLatch(programState);
        //Procedures
        initProcedures(programState);
    }

    private void initProcedures(ProgramState programState) {
        MyProcedure myProcedure = programState.getProcedureTable();
        List<GenericDto> genericDtoList = new ArrayList<>();
        for (Map.Entry<String, ProcedurePair> entry : myProcedure.getContent().entrySet()) {
            genericDtoList.add(new GenericDto(entry.getKey().toString(), entry.getValue().toString()));
        }
        genericTable.itemsProperty().bind(genericDtos);
        genericDtos.set(FXCollections.observableArrayList(genericDtoList));
    }

    private void initCountDownLatch(ProgramState programState) {
        MyLatch myLatch = programState.getLatchTable();
        List<GenericDto> genericDtoList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : myLatch.getContent().entrySet()) {
            genericDtoList.add(new GenericDto(entry.getKey().toString(), entry.getValue().toString()));
        }
        genericTable.itemsProperty().bind(genericDtos);
        genericDtos.set(FXCollections.observableArrayList(genericDtoList));
    }

    private void initLock(ProgramState programState) {
        MyLock myLock = programState.getLockTable();
        List<GenericDto> genericDtoList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : myLock.getContent().entrySet()) {
            genericDtoList.add(new GenericDto(entry.getKey().toString(), entry.getValue().toString()));
        }
        genericTable.itemsProperty().bind(genericDtos);
        genericDtos.set(FXCollections.observableArrayList(genericDtoList));
    }

    private void initToySemaphore(ProgramState programState) {
        MyToySemaphoreTable mySemaphoreTable = programState.getToySemaphoreTable();
        List<GenericDto> genericDtoList = new ArrayList<>();
        for (Map.Entry<Integer, Tuple> entry : mySemaphoreTable.getContent().entrySet()) {
            genericDtoList.add(new GenericDto(entry.getKey().toString(), entry.getValue().toString()));
        }
        genericTable.itemsProperty().bind(genericDtos);
        genericDtos.set(FXCollections.observableArrayList(genericDtoList));
    }

    private void initBarrier(ProgramState programState) {
        MyBarrier myBarrier = programState.getBarrierTable();
        List<GenericDto> genericDtoList = new ArrayList<>();
        for (Map.Entry<Integer, Pair> entry : myBarrier.getContent().entrySet()) {
            genericDtoList.add(new GenericDto(entry.getKey().toString(), entry.getValue().toString()));
        }
        genericTable.itemsProperty().bind(genericDtos);
        genericDtos.set(FXCollections.observableArrayList(genericDtoList));
    }

    private void initSemaphore(ProgramState programState) {
        MySemaphoreTable mySemaphoreTable = programState.getSemaphoreTable();
        List<GenericDto> genericDtoList = new ArrayList<>();
        for (Map.Entry<Integer, Pair> entry : mySemaphoreTable.getContent().entrySet()) {

            genericDtoList.add(new GenericDto(entry.getKey().toString(), entry.getValue().toString()));
        }
        genericTable.itemsProperty().bind(genericDtos);
        genericDtos.set(FXCollections.observableArrayList(genericDtoList));
    }

    private void initNumProgStates(Controller ctr) {
        int numPrgs = ctr.getIdentifiers().size();
        progStatesNumField.setText(numPrgs + " ");
    }

    private void initPrgStatesLst(Controller ctr) {
        List<String> identifiers = ctr.getIdentifiers();
        prgStatesList.itemsProperty().bind(prgStatesIdentifiers);
        prgStatesIdentifiers.set(FXCollections.observableArrayList(identifiers));
    }

    private void initExeStackLst(ProgramState prgState) {
        List<String> exeStack = new ArrayList<>();

        IStack<IStatement> exStack = prgState.getExecutionStack();
        for (IStatement stmt : exStack.getAll()) {
            exeStack.add(stmt.toString());
        }
        exeStackList.setItems(FXCollections.observableList(exeStack));
        exeStackList.refresh();

    }

    private void initFileLst(ProgramState prgState) {
        List<String> filesLst = new ArrayList<>();

        IDictionary<StringValue, BufferedReader> filesState = prgState.getFileTable();

        for (Map.Entry<StringValue, BufferedReader> entry : filesState.getContent()) {
            filesLst.add(entry.getKey().getValue());
        }

        this.fileList.itemsProperty().bind(files);
        files.set(FXCollections.observableArrayList(filesLst));

    }

    private void initOutList(ProgramState prgState) {
        List<String> outTxt = new ArrayList<>();

        IList<Value> out = prgState.getOut();
        for (int i = 0; i < out.size(); i++) {
            outTxt.add(out.getFromIndex(i).toString());
        }
        outList.itemsProperty().bind(outLst);
        outLst.set(FXCollections.observableArrayList(outTxt));
    }

    private void initSymTbl(ProgramState prgState) {
        IDictionary<String, Value> symTbl = prgState.getSymbolTable();
        List<SymDto> symDtoList = new ArrayList<>();
        for (Map.Entry<String, Value> entry : symTbl.getContent()) {

            symDtoList.add(new SymDto(entry.getKey(), entry.getValue()));
        }
        symTable.itemsProperty().bind(symDtos);
        symDtos.set(FXCollections.observableArrayList(symDtoList));
    }

    private void initHeapTbl(ProgramState programState) {
        IHeap<Value> heap = programState.getHeapTable();
        List<HeapDto> heapDtoList = new ArrayList<>();
        for (Map.Entry<Integer, Value> entry : heap.getContent().entrySet()) {

            heapDtoList.add(new HeapDto(entry.getKey(), entry.getValue()));
        }
        heapTable.itemsProperty().bind(heapDtos);
        heapDtos.set(FXCollections.observableArrayList(heapDtoList));
    }

    private void initList() {
        List<String> prgsTxt = getProgramsText();
        programsList.itemsProperty().bind(programsText);
        programsText.set(FXCollections.observableArrayList(prgsTxt));
    }

    private List<String> getProgramsText() {
        List<String> progs = new ArrayList<>();
        int cnt = 1;
        System.out.println(ctrs.size());
        for (Controller ctr : this.ctrs) {
            progs.add("ID: " + cnt + " " + ctr.getProgram().getExecutionStack());
            cnt += 1;
        }
        return progs;
    }


    public void addController(Controller ctr) {
        this.ctrs.add(ctr);
    }

    public void runOneStepForAll(ActionEvent actionEvent) {
        int programIndex = programsList.getSelectionModel().getSelectedIndex();
        int progStateIndex = prgStatesList.getSelectionModel().getSelectedIndex();
        if (progStateIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No program state selected", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (programIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        Controller controller = ctrs.get(programIndex);

        boolean programStateLeft = controller.getProgramStates().get(progStateIndex).getExecutionStack().isEmpty();
        if (programStateLeft) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        controller.executeOneStep();
        initAllWidgets(programIndex, progStateIndex);
    }

    public void setControllers(ArrayList<Controller> controllers) {
        this.ctrs = controllers;
        initList();
    }
}
