package org.juheinz.navigation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GPSTest {
    @Test
    void returnsBetweenOneAndTen(){
        int result = GPS.getCurrentLocation();
        assertTrue(result >= 1 && result <= 10, "Generated number is out of range (1 to 10)");
    }

    @Test
    void returnsPositiveNumber(){
        assertTrue(GPS.getCurrentLocation() > 0);
    }
}