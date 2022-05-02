package kz.javaee.project.madiyevmirasitis1908.controller;

import kz.javaee.project.madiyevmirasitis1908.model.StudentPlace;
import kz.javaee.project.madiyevmirasitis1908.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

class StudentControllerTest {
    @Mock
    StudentService studentService;
    @Mock
    Logger logger;
    @InjectMocks
    StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        when(studentService.getAll()).thenReturn(Arrays.<StudentPlace>asList(new StudentPlace(Long.valueOf(1), "name", "category", "address", "contactInfo")));

        Response result = studentController.getAll();
        Assertions.assertEquals(result, result);
    }

    @Test
    void testGetStudentPlace() {
        when(studentService.getStudentPlaceById(anyLong())).thenReturn(new StudentPlace(Long.valueOf(1), "name", "category", "address", "contactInfo"));

        Response result = studentController.getStudentPlace(Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }

    @Test
    void testSaveStudentPlace() {
        Response result = studentController.saveStudentPlace("name", "category", "address", "contactInfo");
        Assertions.assertEquals(result, result);
    }

    @Test
    void testUpdateStudentPlace() {
        Response result = studentController.updateStudentPlace("address", Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }

    @Test
    void testDeleteById() {
        Response result = studentController.deleteById(Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }
}
