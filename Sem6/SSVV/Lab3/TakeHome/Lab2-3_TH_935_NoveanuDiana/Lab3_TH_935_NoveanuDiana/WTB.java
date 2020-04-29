package testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testing.domain.Nota;
import testing.domain.Student;
import testing.domain.Tema;
import testing.repository.NotaXMLRepository;
import testing.repository.StudentXMLRepository;
import testing.repository.TemaXMLRepository;
import testing.service.Service;
import testing.validation.NotaValidator;
import testing.validation.StudentValidator;
import testing.validation.TemaValidator;
import testing.validation.Validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class TestAddAssignment {
    private StudentXMLRepository studentXMLRepository;
    private NotaXMLRepository notaXMLRepository;
    private TemaXMLRepository temaXMLRepository;

    private Service service;

    @Before
    public void setUp() {
        Validator<Student> vs = new StudentValidator();
        Validator<Nota> ns = new NotaValidator();
        Validator<Tema> ts = new TemaValidator();

        studentXMLRepository = new StudentXMLRepository(vs, "src/test/java/testing/studenti.xml");
        notaXMLRepository = new NotaXMLRepository(ns, "src/test/java/testing/note.xml");
        temaXMLRepository = new TemaXMLRepository(ts, "src/test/java/testing/teme.xml");
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);

    }

    @After
    public void tearDown() {
        try {
            String defaultFileContent = new String(Files.readAllBytes(Paths.get("src/test/java/testing/defaultFile.xml")), StandardCharsets.UTF_8);

            PrintWriter printWriter = new PrintWriter("src/test/java/testing/teme.xml");

            printWriter.print(defaultFileContent);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC1_WBT_AddAssignment() {
        assertEquals(service.saveTema("", "hw", 5, 3), 0);
    }

    @Test
    public void TC2_WBT_AddAssignment(){
        assertEquals(service.saveTema("0", "", 5, 3), 0);
    }

    @Test
    public void TC3_WBT_AddAssignment(){
        assertEquals(service.saveTema("1", "hw", 1, 9), 0);
    }

    @Test
    public void TC4_WBT_AddAssignment(){
        assertEquals(service.saveTema("2", "hw", 4, 0), 0);
    }

    @Test
    public void TC5_WBT_AddAssignment(){
        assertEquals(service.saveTema("0", "hw", 5, 3), 1);
        assertEquals(service.saveTema("0", "hw", 5, 3), 0);
    }

    @Test
    public void TC6_WBT_AddAssignment(){
        assertEquals(service.saveTema("3", "hw", 5, 3), 1);
    }

    @Test
    public void TC7_WBT_AddAssignment(){
        assertEquals(service.saveTema("4", "hw", 5, 3), 1);
    }

    @Test
    public void TC8_WBT_AddAssignment(){
        assertEquals(service.saveTema("5", "hw", 5, 3), 1);
    }

    @Test
    public void TC9_WBT_AddAssignment(){
        assertEquals(service.saveTema("6", "hw", 5, 3), 1);
    }

    @Test
    public void TC10_WBT_AddAssignment() {
        assertEquals(0, service.saveTema(null, "hw", 5, 3));
    }

    @Test
    public void TC11_WBT_AddAssignment() {
        assertEquals(0, service.saveTema("7", null, 5, 3));
    }

    @Test
    public void TC12_WBT_AddAssignment() {
        assertEquals(0, service.saveTema("8", "hw", 21, 9));
    }

    @Test
    public void TC13_WBT_AddAssignment() {
        assertEquals(0, service.saveTema("9", "hw", 0, 0));
    }

    @Test
    public void TC14_WBT_AddAssignment() {
        assertEquals(0, service.saveTema("10", "hw", 9, 21));
    }

    @Test
    public void testAddAssignmentSuccess() {

        assertEquals(service.saveTema("1", "Primul Laborator", 8, 6), 1);
    }

    @Test
    public void testAddAssignmentFailure() {
        assertEquals(service.saveTema("7", "g", 8, 7), 1);
        assertEquals(service.saveTema("7", "g", 8, 7), 0);
    }
}
