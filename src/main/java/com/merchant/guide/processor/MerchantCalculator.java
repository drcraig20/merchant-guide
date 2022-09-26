package com.merchant.guide.processor;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MerchantCalculator {
    private final Map<String, String> intergalacticDigitCache = new HashMap<>();
    private final Map<String, Float> metalPriceCache = new Hashtable<>();

    public Map<String, String> getIntergalacticDigitCache() {
        return intergalacticDigitCache;
    }

    public Map<String, Float> getMetalPriceCache() {
        return metalPriceCache;
    }

    private int getRomanValue(char c){
        return 0;
    }

    private int romanNumberToInteger(String romanString) {
        return 0;
    }

    private int extractRomanNumberToInteger(String[] inputArr){
        return 0;
    }

    private String[] getValidInput(String input, String delimiter){
        return null;
    }

    public boolean isEndWithRomanDigit(String input){
        return false;
    }

    public void computeIntergalacticDigits(String input){
    }

    public void computeMetalCost(String input){
    }

    public String processQuestion(String input) {
        return "";
    }

    private void verifyQuestion(String input) {

    }
}
