package com.js.sample;

import static org.junit.Assert.*;
import org.junit.*;

public class SampleTest {

    @Test public void sample() {
        assertEquals("Gradle is gr8", "Gradle is gr8");
    }

    @Test public void sample2() {
        assertEquals("Gradle is gr8", "Gradle is gr9");
    }

}
