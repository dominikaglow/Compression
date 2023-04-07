package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compression implements CompressonInterface{
    String toCompress = "";
    int wordsToCom = 0; // number of words that can be compressed
    List<String> comp = new ArrayList<>(); // a list containing only the items that need to be compressed
    Map<String, String> notComp = new HashMap<>(); // uncompressed word map
    Map<String, String> header = new HashMap<>(); // the first parameter is the compressed word and the second is the
    // original word
    String finalString = new String(); // string containing all words (compressed and uncompressed)

    @Override
    public void addWord(String word) {
        toCompress = word;
    }

    // a function that checks whether the words have the correct length
    boolean checkIfValid(String word){
        boolean res = true;
        String checker = new String(word);
        int lengthOfWord = checker.indexOf("\s"); // the index of the first space is the length of one word
        if(lengthOfWord >= 9){
            res = false;
        }
        return res;
    }

    // a function that counts the number of occurrences of a given word
    Map<String, Integer> countNum(String word){
        Map<String, Integer> sequence = new HashMap<>();
        String sometxt = new String(word);
        int i = 0;
        while(sometxt.length() >= 0){
            int ind = sometxt.indexOf("\s");
            if(ind >= 0) {
                String part = sometxt.substring(0, ind);
                if (sequence.containsKey(part) == false) {
                    sequence.put(part, 1);
                } else if (sequence.containsKey(part) == true) {
                    sequence.put(part, sequence.get(part) + 1);
                }
                sometxt = sometxt.substring(ind + 1);
            }
            else{
                String lastWord = sometxt;
                if (sequence.containsKey(lastWord) == false) {
                    sequence.put(lastWord, 1);
                } else if (sequence.containsKey(lastWord) == true) {
                    sequence.put(lastWord, sequence.get(lastWord) + 1);
                }
                sometxt = "";
            }
            if (sometxt.length() == 0) {
                break;
            }
        }
        return sequence;
    }

    Integer numOfBits(Integer number){
        Integer ret = 0;
        if(number == 1){
            ret = 1;
        }
        else if(number > 1 && number <= 2){
            ret = 2;
        }
        else if(number > 2 && number <= 3){
            ret = 3;
        }
        else if(number > 3 && number <= 7){
            ret = 4;
        }
        else if(number > 7 && number <= 15){
            ret = 5;
        }
        else if(number > 15 && number <= 31){
            ret = 6;
        }
        else if(number > 31 && number <= 63){
            ret = 7;
        }
        else if(number > 63 && number <= 127){
            ret = 8;
        }
        return ret;
    }

    @Override
    public void compress() {
        Map<String, Integer> findToComp = countNum(toCompress);
        for(Map.Entry<String, Integer> entry: findToComp.entrySet()){
            if(entry.getValue() > 1){
                wordsToCom++;
                comp.add(entry.getKey());
            }
            else{
                String nComWord = entry.getKey();
                notComp.put(nComWord, "1" + nComWord);
            }
        }
        for(int i = 0; i < wordsToCom; i++){
            String compressed = Integer.toBinaryString(i);
            if(compressed.length() < numOfBits((wordsToCom))){
                compressed = "0" + compressed; // ensuring that the compressed word has a 0 at the beginning
            }
            header.put(compressed, comp.get(i));
        }

        String originaltxt = new String(toCompress);
        while(originaltxt.length() >= 0){
            int ind = originaltxt.indexOf("\s");
            if(ind >= 0) {
                String part = originaltxt.substring(0, ind);
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    if (entry.getValue().equals(part)) {
                        finalString = finalString + " " + entry.getKey();
                        break;
                    }
                }
                for (Map.Entry<String, String> entry : notComp.entrySet()) {
                    if (entry.getKey().equals(part)) {
                        finalString = finalString + " " + entry.getValue();
                        break;
                    }
                }
                originaltxt = originaltxt.substring(ind + 1);
            }
            else{
                String lastWord = originaltxt;
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    if (entry.getValue().equals(lastWord)) {
                        finalString = finalString + " " + entry.getKey();
                        break;
                    }
                }
                for (Map.Entry<String, String> entry : notComp.entrySet()) {
                    if (entry.getValue().equals(lastWord)) {
                        finalString = finalString + " " + entry.getValue();
                        break;
                    }
                }
                originaltxt = "";
            }
            if(originaltxt.length() == 0){
                break;
            }
        }
    }

    @Override
    public Map<String, String> getHeader() {
        for(Map.Entry<String, String> entry: header.entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        return header;
    }

    @Override
    public String getWord() {
        System.out.println(finalString);
        return finalString;
    }
}
