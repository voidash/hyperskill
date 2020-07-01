package com.company;


import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class TextAnalyzer {
    private static final String[] gradeLevel =
            {"5-6", "6-7", "7-9", "9-10", "10-11", "11-12", "12-13",
                    "13-14", "14-15", "15-16", "16-17", "17-18", "18-24", "24+"};

    public static int getTotalCharacters(String content){

            return content.isBlank() ? 0 : content.replaceAll("\\s","").length();
    }
    public static int getTotalSentence(@NotNull String content){
            return content.isBlank() ? 0 : content.split("[.?!]+").length;
    }
    public static int getTotalWord(@NotNull String content){
            return content.isBlank() ? 0 : content.split("\\s+").length;
    }
    public static int[] getSyllables(@NotNull String content){

        int TotalSyalables = 0;
        int totalPolySyllables=0;
        int[] syllableBank = new int[2];

        if(!content.isBlank()){
                String[] words = content.split("\\s+");
                for(String word:words) {
                    Pattern pattern = Pattern.compile("([aAeEiIoOuU]+)");
                    Matcher matcher = pattern.matcher(word);
                    int syllableCount = 0;

                    while(matcher.find()) {
                        syllableCount++;
                    }
                        if (pattern.matches("^.+e$", word)) {
                            System.out.println(word);
                            syllableCount--;
                        }

                   if(syllableCount == 0){
                       syllableCount++;
                    }
                    if(syllableCount>2){
                        totalPolySyllables++;
                    }

                    TotalSyalables+=syllableCount;
                    }

            }
        syllableBank[0]=TotalSyalables;
        syllableBank[1]=totalPolySyllables;
        return syllableBank;
    }


    public static double AutomatedReadabilityIndex(String content,int chars, int words, int sentences){
        double score = 4.71 * chars / words + 0.5 * words / sentences - 21.43;
        return (Double.compare(score,1.0)<0)?0:(Double.compare(score,14.0)>0?14.0:score);

    }
    public static double FleschKincaidTest(String content,int words, int sentences, int syllables){
        double score = 0.39*words / sentences + 11.8 * syllables/words -15.59;
        return (Double.compare(score,1.0)<0)?0:(Double.compare(score,14.0)>0?14.0:score);
    }

    public static double SMOGTest(String content,int polySyllables,int sentences){
        double score = 1.043  * Math.cbrt(polySyllables * 30/sentences)+3.1291;
        return (Double.compare(score,1.0)<0)?0:(Double.compare(score,14.0)>0?14.0:score);
    }
    public static double colemanLauTest(String content) {
        String[] words = content.isEmpty() ? null :content.split("\\s+");


        int[] l = null;
        int[] s = null;

        if (words == null){
            return 0;
        }

        if(words.length < 100){
            l = new int[1];
            l[0] = getTotalCharacters(content);
            s = new int[1];
            s[0] = getTotalSentence(content);
        }else{

            int totalPieces = (int) Math.ceil(words.length / 100);
            l = new int[totalPieces];
            s = new int[totalPieces];
            for(int counter=0;counter<totalPieces;counter++){
                String pieceContent = "";
                for(int j = counter*100;j<counter*100+100;j++){
                    pieceContent = " ".concat(words[j]);
                    if(j == words.length -1){
                        break;
                    }
                }
                l[counter] = getTotalCharacters(pieceContent);
                s[counter] = getTotalSentence(pieceContent);

            }
        }
        int lSum = 0;
        int sSum = 0;

        for(int i =0 ; i < l.length;i++){
            lSum += l[i];
            sSum += s[i];
        }

        double L = lSum / l.length;
        double S = sSum / s.length;

        double score = 0.588 * L - 0.296 * S -15.8;
        return (Double.compare(score,1.0)<0)?0:(Double.compare(score,14.0)>0?14.0:score);

    }



    public static String ageGroup(double score){
        return gradeLevel[(int) Math.ceil(score)-1];
    }


}
