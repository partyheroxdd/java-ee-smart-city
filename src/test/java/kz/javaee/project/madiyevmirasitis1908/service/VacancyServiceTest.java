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
  void testGetVacancyById() {
    when(vacancyRepository.findById(anyLong(), any())).thenReturn(
        new Vacancy(Long.valueOf(1), "title", "company", "description", "salary"));

    Vacancy result = vacancyService.getVacancyById(Long.valueOf(1));
    Assertions.assertEquals(
        new Vacancy(Long.valueOf(1), "title", "company", "description", "salary"), result);
  }

  @Test
  void testCreateNewVacancy() {
    vacancyService.createNewVacancy(
        new Vacancy(Long.valueOf(1), "title", "company", "description", "salary"));
  }

  @Test
  void testUpdateVacancy() {
    vacancyService.updateVacancy(
        new Vacancy(Long.valueOf(1), "title", "company", "description", "salary"));
  }

  @Test
  void testDeleteVacancyById() {
    when(vacancyRepository.findById(anyLong(), any())).thenReturn(
        new Vacancy(Long.valueOf(1), "title", "company", "description", "salary"));

    vacancyService.deleteVacancyById(Long.valueOf(1));
  }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme