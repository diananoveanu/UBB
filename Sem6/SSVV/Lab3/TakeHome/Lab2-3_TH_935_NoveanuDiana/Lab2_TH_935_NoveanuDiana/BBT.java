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

public class TestAddStudent {
    private StudentXMLRepository studentXMLRepository;
    private NotaXMLRepository notaXMLRepository;
    private TemaXMLRepository temaXMLRepository;

    private Service service;

    @Before
    public void setup() {
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

            PrintWriter printWriter = new PrintWriter("src/test/java/testing/studenti.xml");

            printWriter.print(defaultFileContent);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddStudentSuccess() {
        assertEquals(service.saveStudent("1", "Diana", 935), 1);
    }

    @Test
    public void testAddStudentFailure() {
        assertEquals(service.saveStudent("2", "Roland", 934), 1);
        assertEquals(service.saveStudent("2", "Roland", 934), 0);
    }

    @Test
    public void TC1_BBT_EC() {
        assertEquals(service.saveStudent("0", "Albert", 934), 1);
    }

    @Test
    public void TC2_BBT_EC() {
        assertEquals(service.saveStudent(null, "Albert", 934), 0);
    }

    @Test
    public void TC3_BBT_EC() {
        assertEquals(service.saveStudent("0", "Albert", 938), 0);
    }


    @Test
    public void TC4_BBT_EC() {
        assertEquals(service.saveStudent("0", "Albert", 939), 0);
    }

    @Test
    public void TC5_BBT_BVA() {
        assertEquals(service.saveStudent("0", "Albert", 111), 1);
    }

    @Test
    public void TC6_BBT_BVA() {
        assertEquals(service.saveStudent("1", "Albert", 109), 0);
    }

    @Test
    public void TC7_BBT_BVA() {
        assertEquals(service.saveStudent("2", "Albert", 937), 1);

    }

    @Test
    public void TC8_BBT_BVA() {
        assertEquals(service.saveStudent("3", "Albert", 938), 0);
    }

    @Test
    public void TC9_BBT_BVA() {
        assertEquals(service.saveStudent("4", "Albert", 400), 1);

    }

    @Test
    public void TC10_BBT_BVA() {
        assertEquals(service.saveStudent("5", "Albert", -1), 0);
    }

    @Test
    public void TC11_BBT_BVA() {
        assertEquals(service.saveStudent("6", "akljklfjsf", 123), 1);
    }

    @Test
    public void TC12_BBT_BVA() {
        assertEquals(service.saveStudent("7", "", 123), 0);
    }

    @Test
    public void TC13_BBT_BVA() {
        assertEquals(service.saveStudent("8", null, 124), 0);
    }
}