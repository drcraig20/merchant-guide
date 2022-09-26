package com.merchant.guide.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MerchantCalculatorTest {

    MerchantCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new MerchantCalculator();
    }

    @Test
    @DisplayName("Checking if input ends with roman numeral should be true")
    void testValidStringWithRomanNumeral_thenExpectValidResponse(){
        String input = "glob is I";
        boolean endWithRomanDigit = calculator.isEndWithRomanDigit(input);
        assertTrue(endWithRomanDigit);
    }

    @Test
    @DisplayName("Intergalactic cache hit should be true")
    void testComputeIntergalacticDigit_thenExpectCacheHit(){
        String input = "glob is I";
        calculator.computeIntergalacticDigits(input);
        assertEquals(1, calculator.getIntergalacticDigitCache().size());
    }

    @Test
    @DisplayName("Metal Price cache hit should be true")
    void testComputeMetalCost_thenExpectCacheHit(){
        String input = "glob Silver is 17 Credits";
        calculator.computeMetalCost(input);
        assertEquals(1, calculator.getMetalPriceCache().size());
    }

    @Test
    @DisplayName("Process Question should produce valid Not Empty String")
    void testProcessQuestion_thenExpectValidResponse(){
        calculator.getMetalPriceCache().put("Silver", 50F);
        calculator.getIntergalacticDigitCache().put("glob", "I");
        String input = "how many Credits is glob Silver ?";
        String response = calculator.processQuestion(input);
        assertEquals("glob Silver is 50 Credits", response);
    }

    @Test
    @DisplayName("Process Question should produce error")
    void testProcessQuestion_thenExpectIllegalArgumentException(){
        String input = "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";
        assertThrows(IllegalArgumentException.class, () -> calculator.processQuestion(input));
    }

    @Test
    @DisplayName("Process Question should produce error")
    void testProcessQuestionWithInvalidInputLength_thenExpectIllegalArgumentException(){
        String input = "how ?";
        assertThrows(IllegalArgumentException.class, () -> calculator.processQuestion(input));
    }

}
