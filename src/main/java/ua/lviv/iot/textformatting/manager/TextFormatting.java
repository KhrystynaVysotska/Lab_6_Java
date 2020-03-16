package ua.lviv.iot.textformatting.manager;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFormatting {
    private List<String> foundedWords = new LinkedList<String>();

    public List<String> findWordsWithSelectedLetter(final char letterToFind, String inputedText) {
        String regEx = "[a-zA-Z]*[" + letterToFind + "][a-zA-Z]*";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(inputedText);
        while (matcher.find()) {
            foundedWords.add(matcher.group().toString());
        }
        return foundedWords;
    }

    public static int countAmountOfSelectedLetterInWord(String word, char letterToFind) {
        int amountOfSelectedLetter = 0;
        for (char letter : word.toCharArray()) {
            if (letter == letterToFind) {
                amountOfSelectedLetter++;
            }
        }
        return amountOfSelectedLetter;
    }

    public void sortFoundedWordsByAmountOfSelectedLetter(List<String> foundedWords, char letterToFind) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String firstWord, String secondWord) {
                int difference = TextFormatting.countAmountOfSelectedLetterInWord(firstWord, letterToFind)
                        - TextFormatting.countAmountOfSelectedLetterInWord(secondWord, letterToFind);
                if (difference != 0) {
                    return difference;
                } else {
                    return firstWord.compareTo(secondWord);
                }
            }
        };
        foundedWords.sort(comparator);
    }
}
