package com.merchant.guide.processor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MerchantProcessorTest {

    MerchantProcessor processor;
    InputStream in;
    PrintStream out;
    ByteArrayOutputStream byteArrayOutputStream;

    @BeforeEach
    void setup(){
        this.processor = new MerchantProcessor();
        this.in = System.in;

        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.out = new PrintStream(byteArrayOutputStream);
    }

    @Test
    @DisplayName("Validate calling of processInput")
    void testProcessInput_thenExpectValidResponse(){
        processor.processInput(in, out);

        String outputText = byteArrayOutputStream.toString();

        assertEquals("### Test Input:", outputText.trim());
    }

    @Test
    @DisplayName("Validate calling of printResult")
    void testPrintResult_thenExpectValidResponse(){
        String actualOutputTest = """
                ### Test Output:
                > pish tegj glob glob is 42
                > glob prok Silver is 68 Credits
                > glob prok Gold is 57800 Credits
                > glob prok Iron is 782 Credits
                > I have no idea what you are talking about
                """;

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
