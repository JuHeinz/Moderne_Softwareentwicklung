package org.juheinz.navigation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GPSTest {
    @Test
    void returnsBerlinCoordinates(){
        double[] result = GPS.getCurrentLocation();
            boolean latitudeCorrect = result[1] <= 52.8 && result[1] >= 52.2;
            boolean longitudeCorrect = result[0] <= 14 && result[0] >= 12.9;
        assertTrue(latitudeCorrect && longitudeCorrect, "Generated location is within Berlin");
    }


}