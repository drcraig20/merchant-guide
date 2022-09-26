package com.merchant.guide.processor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MerchantProcessorTest {

    MerchantProcessor processor;
    InputStream in;
    PrintStream out;
    ByteArrayOutputStream byteArrayOutputStream;
    ByteArrayInputStream byteArrayInputStream;

    @BeforeEach
    void setup(){
        this.processor = new MerchantProcessor();

        String inputText = """
                > glob is I
                > prok is V
                > pish is X
                > tegj is L
                > glob glob Silver is 34 Credits
                > glob prok Gold is 57800 Credits
                > pish pish Iron is 3910 Credits
                > how much is pish tegj glob glob ?
                > how many Credits is glob prok Silver ?
                > how many Credits is glob prok Gold ?
                > how many Credits is glob prok Iron ?
                
                > how much wood could a woodchuck chuck if a woodchuck could chuck wood ?
                EOL
                """;

        this.in = new ByteArrayInputStream(inputText.getBytes());

        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.out = new PrintStream(byteArrayOutputStream);
    }

    @Test
    @DisplayName("Validate calling of processInput")
    void testProcessInput_thenExpectValidResponse(){

        String actualOutputTest = """
                ### Test Output:
                > pish tegj glob glob is 42
                > glob prok Silver is 68 Credits
                > glob prok Gold is 57800 Credits
                > glob prok Iron is 782 Credits
                > I have no idea what you are talking about""";

        processor.processInput(in, out);

        String outputText = byteArrayOutputStream.toString();
        outputText = outputText.replaceFirst("### Test Input:", "").trim();

        assertEquals(actualOutputTest, outputText.trim());
    }

    @Test
    @DisplayName("Validate calling of printResult")
    void testPrintResult_thenExpectValidResponse(){
        String actualOutputTest = "### Test Output:";

        processor.printResult(out);

        String outputText = byteArrayOutputStream.toString();

        assertEquals(actualOutputTest.trim(), outputText.trim());
    }

    @AfterEach
    void destroy() throws IOException {
        in.close();
        out.close();
        byteArrayOutputStream.close();
    }
}
