package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Client;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientTest {
	
	@Test
    void isLegal_should_return_true_when_age_is_over_18() {
        Client legalClient = new Client("Smith", "John", "smith.john@epf.fr", LocalDate.parse("2000-11-02"));
        assertTrue(legalClient.isLegal(legalClient));
    }

    @Test
    void isLegal_should_return_false_when_age_is_under_18() {
        Client illegalClient = new Client("Smith", "John", "smith.john@epf.fr", LocalDate.now());
        assertFalse(illegalClient.isLegal(illegalClient));
    }
    
    @Test
    void LenghtName_should_return_true_when_last_name_is_over_3() {
        Client longClient = new Client("Smith", "John", "smith.john@epf.fr", LocalDate.parse("2000-11-02"));
        assertTrue(longClient.LenghtName());
    }
    
    @Test
    void LenghtName_should_return_false_when_last_name_is_under_3() {
        Client shortClient = new Client("S", "John", "smith.john@epf.fr", LocalDate.parse("2000-11-02"));
        assertFalse(shortClient.LenghtName());
    }
    
    @Test
    void LenghtLastname_should_return_true_when_name_is_over_3() {
        Client longClient = new Client("Smith", "John", "smith.john@epf.fr", LocalDate.parse("2000-11-02"));
        assertTrue(longClient.LenghtLastname());
    }
    
    @Test
    void LenghtLastname_should_return_false_when_name_is_under_3() {
        Client shortClient = new Client("Smith", "J", "smith.john@epf.fr", LocalDate.parse("2000-11-02"));
        assertFalse(shortClient.LenghtLastname());
    }

}
