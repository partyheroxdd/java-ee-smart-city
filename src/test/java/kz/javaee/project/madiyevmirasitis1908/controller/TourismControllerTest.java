package kz.javaee.project.madiyevmirasitis1908.controller;

import kz.javaee.project.madiyevmirasitis1908.model.TouristicPlace;
import kz.javaee.project.madiyevmirasitis1908.service.TouristicService;
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

class TourismControllerTest {
    @Mock
    TouristicService touristicService;
    @Mock
    Logger logger;
    @InjectMocks
    TourismController tourismController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        when(touristicService.getAll()).thenReturn(Arrays.<TouristicPlace>asList(new TouristicPlace(Long.valueOf(1), "name", "type", "address", "contactInfo")));

        Response result = tourismController.getAll();
        Assertions.assertEquals(result, result);
    }

    @Test
    void testGetTouristicPlace() {
        when(touristicService.getTouristicPlaceById(anyLong())).thenReturn(new TouristicPlace(Long.valueOf(1), "name", "type", "address", "contactInfo"));

        Response result = tourismController.getTouristicPlace(Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }

    @Test
    void testSaveTouristicPlace() {
        Response result = tourismController.saveTouristicPlace("name", "type", "address", "contactInfo");
        Assertions.assertEquals(result, result);
    }

    @Test
    void testUpdateTouristicPlace() {
        Response result = tourismController.updateTouristicPlace("address", Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }

    @Test
    void testDeleteById() {
        Response result = tourismController.deleteById(Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }
}
