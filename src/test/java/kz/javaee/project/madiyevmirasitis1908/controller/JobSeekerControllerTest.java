package kz.javaee.project.madiyevmirasitis1908.controller;

import kz.javaee.project.madiyevmirasitis1908.model.Vacancy;
import kz.javaee.project.madiyevmirasitis1908.service.VacancyService;
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

class JobSeekerControllerTest {
    @Mock
    VacancyService vacancyService;
    @Mock
    Logger logger;
    @InjectMocks
    JobSeekerController jobSeekerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        when(vacancyService.getAll()).thenReturn(Arrays.<Vacancy>asList(new Vacancy(Long.valueOf(1), "title", "company", "description", 0d, "createdAt")));

        Response result = jobSeekerController.getAll();
        Assertions.assertEquals(result, result);
    }

    @Test
    void testGetVacancy() {
        when(vacancyService.getVacancyById(anyLong())).thenReturn(new Vacancy(Long.valueOf(1), "title", "company", "description", 0d, "createdAt"));

        Response result = jobSeekerController.getVacancy(Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }

    @Test
    void testSaveVacancy() {
        Response result = jobSeekerController.saveVacancy("title", "company", "description", 0d, "createdAt");
        Assertions.assertEquals(result, result);
    }

    @Test
    void testUpdateVacancy() {
        Response result = jobSeekerController.updateVacancy(0d, Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }

    @Test
    void testDeleteById() {
        Response result = jobSeekerController.deleteById(Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }
}
