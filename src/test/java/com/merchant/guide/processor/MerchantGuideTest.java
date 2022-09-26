package com.merchant.guide.processor;

import com.merchant.guide.MerchantGuide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MerchantGuideTest {
    ByteArrayOutputStream byteArrayOutputStream;

    @BeforeEach
    void setup(){

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

        System.setIn(new ByteArrayInputStream(inputText.getBytes()));

        this.byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Test
    @DisplayName("Validate calling of main method")
    void testMainMethod_thenExpectValidResponse(){
        String actualOutputTest = """
                ### Test Output:
                > pish tegj glob glob is 42
                > glob prok Silver is 68 Credits
                > glob prok Gold is 57800 Credits
                > glob prok Iron is 782 Credits
                > I have no idea what you are talking about""";

        MerchantGuide.main(new String[0]);

        String outputText = byteArrayOutputStream.toString();
        outputText = outputText.replaceFirst("### Test Input:", "").trim();

        assertEquals(actualOutputTest, outputText.trim());
    }

    @AfterEach
    void destroy() throws IOException {
        byteArrayOutputStream.close();
    }
}
