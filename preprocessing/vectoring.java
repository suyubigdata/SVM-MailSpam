/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author PTIT
 */
public class vectoring {
    public static String PATH_DATA_TRAIN = "data/data_train.txt";
    public static String PATH_DICTIONARY_TRAIN = "data/dic_data_train.txt";
    public static String PATH_VECTOR_TRAIN = "data/vector_data_train.txt";
    public static void main(String[] args) {
        
    }
    public static void generateDic(String data_train,String output,  String dict) throws FileNotFoundException, IOException {
        HashMap<String, Integer> hmDict = new HashMap<String, Integer>();
        BufferedReader brDict = new BufferedReader(new FileReader(dict));
        String data="";
        while ((data = brDict.readLine()) != null) {
            String[] vals=data.split(":");
            hmDict.put(vals[1], Integer.parseInt(vals[0]));
        }
        PrintStream ps = new PrintStream(new FileOutputStream(new File(output)));
        BufferedReader brdata = new BufferedReader(new FileReader(data_train));
        ArrayList<String> listTokens = new ArrayList<>();
        while ((data = br.readLine()) != null) {
            data = data.substring(2, data.length() - 1).toLowerCase();
            data = cleanText(data);
            String[] arr = data.split(" ");
            for (String word : arr) {
                if (hm.get(word)==null) {
                    hm.put(word, 1);
                    listTokens.add(word);
                } else {
                    int count = hm.get(word);
                    hm.put(word, count + 1);
                }
            }
        }
        //==========================================================
        // Sắp xếp từ điển theo alphabe                            ||
        //==========================================================
        Set<String> dictionary = new TreeSet<String>(new Comparator<String>() {

            @Override
            public int compare(String token1, String token2) {
                return token1.compareToIgnoreCase(token2);
            }
        });
        dictionary.addAll(listTokens);
        int index=1;
        for(String word: dictionary){
            ps.println(index+" "+word);
            index++;
        }
    }

    public static String cleanText(String text) {
        String cleanText = text;
        String[] stripChars = {"[", "]", ",", "(", ")", "'", "?", "|", "!", "&", "”", "“", "’",
            "‘", "\"", "`", "...", "※", "..", ">", "<", "#", "⋆", ";", "......", "^", "__", "*", "+",
            "----", "--", ".:", ".:.", ".::", ":.", "::", "::.", "=", ";", ",", "—", "_", "^", "~", "",
            "\"", "{", "}", "*", "&", "", "•",
            "$", "@", "%", "`", "#", "®", "©", "\"", "...", "”", "“", "﻿", "’", "‘", "»", "…"};
        for (String removingtt : stripChars) {
            cleanText = cleanText.replace(removingtt, " ");//to remove unncecessary characters 
        }
        cleanText = cleanText.replaceAll("\n", " ");
        cleanText = cleanText.replaceAll("\t", " ");
        cleanText = cleanText.replaceAll("\\s+", " ");
        cleanText = cleanText.replaceAll("(^\\s+|\\s+$)", "");
        return cleanText;
    }
}
