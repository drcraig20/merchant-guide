package com.merchant.guide.processor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MerchantProcessor {

    private final MerchantCalculator calculator = new MerchantCalculator();
    private final List<String> resultSet = new ArrayList<>();

    public void processInput(InputStream in, PrintStream out){
        out.println("### Test Input:");
        try (Scanner scanner = new Scanner(in)) {
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                if (input.equals("EOL"))
                    break;
                if (!input.isBlank())
                    this.figureOutActionToTake(input);
            }
        }

        this.printResult(out);
    }

    private void figureOutActionToTake(String input){
        try {
            if (input.startsWith(">"))
                input = input.replaceFirst(">","").trim();

            if (calculator.isEndWithRomanDigit(input))
                calculator.computeIntergalacticDigits(input);
            else if (input.toLowerCase().endsWith("credits"))
                calculator.computeMetalCost(input);
            else {
                String response = calculator.processQuestion(input);
                resultSet.add(response);
            }
        } catch (IllegalArgumentException exception){
            resultSet.add("> I have no idea what you are talking about");
        }
    }

    public void printResult(PrintStream out) {
        out.println();
        out.println("### Test Output:");
        for (String result : resultSet) {
            out.println(result);
        }
    }
}
