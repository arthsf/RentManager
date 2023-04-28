package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VehicleTest {
	
    @Test
    void return_true_when_number_of_seat_is_correct() {
        Vehicle CorrectVehicle = new Vehicle(1, "Citroen", 4);
        assertTrue(CorrectVehicle.Nb_placesValide());
    }
    @Test
    void return_false_when_number_of_seat_is_correct() {
    	Vehicle IncorrectVehicle  = new Vehicle(1, "Citroen", 1);
        assertFalse(IncorrectVehicle.Nb_placesValide());
    }

}
