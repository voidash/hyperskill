package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static com.company.TextAnalyzer.*;
public class Main {
    static String content="";

    public static void main(String[] args){

        if (args.length < 2){
            System.out.println("Usage: java fileName");
        }
        try{
            content = Files.readAllBytes(Paths.get(args[0])).toString();

        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading the file. Exiting........");
            return;
        }
        int chars = getTotalCharacters(content);
        int words = getTotalWords(content);
        int sentences = getTotalSentence(content);
        int[] syllables = getSyllables(content);


    }
}
