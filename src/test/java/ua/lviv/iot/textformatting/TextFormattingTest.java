package ua.lviv.iot.textformatting;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.lviv.iot.textformatting.manager.TextFormatting;

class TextFormattingTest {
    private TextFormatting formator;
    private char inputedLetter;
    private String inputedText;
    private TextFormattingTest tester;

    @BeforeEach
    public void createTextFormattingAndTextFormattingTestObjects() {
        formator = new TextFormatting();
        tester = new TextFormattingTest();
    }

    public char inputLetterToFindBy() {
        String letter = "e";
        try(InputStream in = new ByteArrayInputStream(letter.getBytes())) {
        System.setIn(in);

        Scanner input = new Scanner(System.in);
        inputedLetter = input.next().charAt(0);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return inputedLetter;
    }

    public String inputTextToFindSuitableWordsIn() {
        String text = "To succeed in life, you need three things: a wishbone, a backbone, and a funny bone.";
        try(InputStream in = new ByteArrayInputStream(text.getBytes())){
        System.setIn(in);

        Scanner input = new Scanner(System.in);
        inputedText = input.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputedText;
    }

    @Test
    public void findWordsWithSelectedLetterTest() {
        List<String> suitableWords = formator.findWordsWithSelectedLetter(tester.inputLetterToFindBy(),
                tester.inputTextToFindSuitableWordsIn());
        String wordLine = "";
        for (String word : suitableWords) {
            wordLine += word;
        }
        assertEquals("succeed" + "life" + "need" + "three" + "wishbone" + "backbone" + "bone", wordLine);
    }

    @Test
    public void sortFoundedWordsByAmountOfSelectedLetterTest() {
        List<String> foundedWords = formator.findWordsWithSelectedLetter(tester.inputLetterToFindBy(),
                tester.inputTextToFindSuitableWordsIn());
        formator.sortFoundedWordsByAmountOfSelectedLetter(foundedWords, tester.inputLetterToFindBy());
        String wordLine = "";
        for (String word : foundedWords) {
            wordLine += word;
        }
        assertEquals("backbone" + "bone" + "life" + "wishbone" + "need" + "succeed" + "three", wordLine);
    }
}