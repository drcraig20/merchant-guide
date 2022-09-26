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
        int sum =0;
        for (int i = 0; i < romanString.length(); i++){
            char charAt = romanString.charAt(i);
            int digit = getRomanValue(charAt); // constant time retrieval
            if (i != romanString.length()-1){
                int afterDigit = getRomanValue(romanString.charAt(i+1)); // constant time retrieval

                if (digit < afterDigit){
                    sum -= digit;
                }
                else sum += digit;
            }
            else sum += digit;
        }
        return sum;
    }

    private int extractRomanNumberToInteger(String[] inputArr){
        StringBuilder romanNumberBuilder = new StringBuilder();

        int index = 0;
        while (intergalacticDigitCache.containsKey(inputArr[index])){
            /*
             * assumes that there is no more intergalactic digit in the string anymore
             * which also works that way in real life
             */
            romanNumberBuilder.append(intergalacticDigitCache.get(inputArr[index]));
            index++;
        }

        String romanNumber = romanNumberBuilder.toString();

        return romanNumberToInteger(romanNumber);
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
        String[] inputArr = input.split(" ");

        int numberOfMetals = extractRomanNumberToInteger(inputArr);

        float totalCostOfMetal = Float.parseFloat(inputArr[inputArr.length-2]);

        float pricePerMetal = totalCostOfMetal / numberOfMetals;

        String metalName = inputArr[inputArr.length-4];

        metalPriceCache.put(metalName, pricePerMetal);
    }

    public String processQuestion(String input) {
        verifyQuestion(input);

        String[] validInputArr = getValidInput(input, " is ");  //this reduces the number of loops to perform on the input

        int totalNumberOfMetal = extractRomanNumberToInteger(validInputArr);

        StringBuilder response = new StringBuilder();
        for (int i = 0; i < validInputArr.length-1; i++){
            response.append(validInputArr[i]).append(" ");
        }

        String metalName = validInputArr[validInputArr.length-2];

        boolean containsKey = metalPriceCache.containsKey(metalName);

        response.append("is ");

        if (containsKey){
            float priceOfMetal = metalPriceCache.get(metalName);
            float credits = priceOfMetal * totalNumberOfMetal;

            response.append((int) credits);
        }
        else response.append(totalNumberOfMetal);

        if (containsKey)
            response.append(" Credits");

        return "> " + response;
    }

    private void verifyQuestion(String input) {
        String[] inputArr = input.split(" ");
        if (inputArr.length < 2){
            throw new IllegalArgumentException();
        }

        String intergalacticDigit = inputArr[inputArr.length -3];
        if (!intergalacticDigitCache.containsKey(intergalacticDigit))
            throw  new IllegalArgumentException();
    }
}
