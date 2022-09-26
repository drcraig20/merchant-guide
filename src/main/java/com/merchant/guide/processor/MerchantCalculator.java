package com.merchant.guide.processor;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class MerchantCalculator {

    private final Set<Character> romanSet = Set.of('I','V','X','L','C','D','M');
    // decided not to use a map here because search worst case is O(n) Linear in map but O(1) Constant in set


    private final Map<String, String> intergalacticDigitCache = new HashMap<>();
    private final Map<String, Float> metalPriceCache = new Hashtable<>();

    public Map<String, String> getIntergalacticDigitCache() {
        return intergalacticDigitCache;
    }

    public Map<String, Float> getMetalPriceCache() {
        return metalPriceCache;
    }

    private int getRomanValue(char c){
        /*
         * Decided to trade space for time here this reduced the search time to a constant time
         */
        return switch (c){
            case 'I'-> 1;
            case 'V'-> 5;
            case 'X'-> 10;
            case 'L'-> 50;
            case 'C'-> 100;
            case 'D'-> 500;
            case 'M'-> 1000;
            default -> throw new IllegalArgumentException("Unexpected value: " + c);
        };
    }

    private int romanNumberToInteger(String romanString) {
        return 0;
    }

    private int extractRomanNumberToInteger(String[] inputArr){
        return 0;
    }

    private String[] getValidInput(String input, String delimiter){
        String validInput = input.split(delimiter)[1].trim();
        return validInput.split(" ");
    }

    public boolean isEndWithRomanDigit(String input){
        return romanSet.contains(input.charAt(input.length()-1));
    }

    public void computeIntergalacticDigits(String input){
        String[] inputArr = input.split(" ");
        intergalacticDigitCache.put(inputArr[0],inputArr[inputArr.length-1]);
    }

    public void computeMetalCost(String input){
    }

    public String processQuestion(String input) {
        return "";
    }

    private void verifyQuestion(String input) {

    }
}
