package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static com.company.TextAnalyzer.*;
public class Main {
    static String content="";

    public static void main(String[] args){

        if (args.length < 1){
            System.out.println("Usage: java fileName");
            System.out.println(args.length);

            return;
        }
        try{
            content = Files.readString(Paths.get(args[0]));

        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading the file. Exiting........");
            return;
        }
        int chars = getTotalCharacters(content);
        int words = getTotalWord(content);
        int sentences = getTotalSentence(content);
        int[] syllables = getSyllables(content);

        System.out.println("the Text is: \n" + content);

        System.out.println("Words: "+ words);
        System.out.println("Sentences: "+ sentences);
        System.out.println("Characters: "+ chars);
        System.out.println("Syllables: "+ syllables[0]);
        System.out.println("Polysyllables: " + syllables[1]);








    }
}
