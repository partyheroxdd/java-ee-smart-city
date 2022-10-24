package kz.javaee.project.madiyevmirasitis1908.service;

import kz.javaee.project.madiyevmirasitis1908.model.TouristicPlace;
import kz.javaee.project.madiyevmirasitis1908.repository.TouristicPlaceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TouristicServiceTest {

  @Mock
  TouristicPlaceRepository touristicPlaceRepository;
  @InjectMocks
  TouristicService touristicService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testGetTouristicPlaceById() {
    when(touristicPlaceRepository.findById(anyLong(), any())).thenReturn(
        new TouristicPlace(Long.valueOf(1), "name", "type", "address", "contactInfo"));

    TouristicPlace result = touristicService.getTouristicPlaceById(Long.valueOf(1));
    Assertions.assertEquals(
        new TouristicPlace(Long.valueOf(1), "name", "type", "address", "contactInfo"), result);
  }

  @Test
  void testCreateNewTouristicPlace() {
    touristicService.createNewTouristicPlace(
        new TouristicPlace(Long.valueOf(1), "name", "type", "address", "contactInfo"));
  }

  @Test
  void testUpdateTouristicPlace() {
    touristicService.updateTouristicPlace(
        new TouristicPlace(Long.valueOf(1), "name", "type", "address", "contactInfo"));
  }

  @Test
  void testDeleteTouristicPlaceById() {
    when(touristicPlaceRepository.findById(anyLong(), any())).thenReturn(
        new TouristicPlace(Long.valueOf(1), "name", "type", "address", "contactInfo"));

    touristicService.deleteTouristicPlaceById(Long.valueOf(1));
  }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme