package kz.javaee.project.madiyevmirasitis1908.service;

import kz.javaee.project.madiyevmirasitis1908.model.Vacancy;
import kz.javaee.project.madiyevmirasitis1908.repository.VacancyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class VacancyServiceTest {
    @Mock
    VacancyRepository vacancyRepository;
    @InjectMocks
    VacancyService vacancyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        when(vacancyRepository.getAll()).thenReturn(Arrays.<Vacancy>asList(new Vacancy(Long.valueOf(1), "title", "company", "description", 0d, "createdAt")));

        List<Vacancy> result = vacancyService.getAll();
        Assertions.assertEquals(Arrays.<Vacancy>asList(new Vacancy(Long.valueOf(1), "title", "company", "description", 0d, "createdAt")), result);
    }

    @Test
    void testGetVacancyById() {
        when(vacancyRepository.findById(anyLong(), any())).thenReturn(new Vacancy(Long.valueOf(1), "title", "company", "description", 0d, "createdAt"));

        Vacancy result = vacancyService.getVacancyById(Long.valueOf(1));
        Assertions.assertEquals(new Vacancy(Long.valueOf(1), "title", "company", "description", 0d, "createdAt"), result);
    }

    @Test
    void testCreateNewVacancy() {
        vacancyService.createNewVacancy(new Vacancy(Long.valueOf(1), "title", "company", "description", 0d, "createdAt"));
    }

    @Test
    void testUpdateVacancySalary() {
        when(vacancyRepository.findById(anyLong(), any())).thenReturn(new Vacancy(Long.valueOf(1), "title", "company", "description", 0d, "createdAt"));

        vacancyService.updateVacancySalary(Long.valueOf(1), 0d);
    }

    @Test
    void testDeleteVacancyById() {
        when(vacancyRepository.findById(anyLong(), any())).thenReturn(new Vacancy(Long.valueOf(1), "title", "company", "description", 0d, "createdAt"));

        vacancyService.deleteVacancyById(Long.valueOf(1));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme