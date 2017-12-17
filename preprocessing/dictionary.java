/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PTIT
 */
public class dictionary {

    public static String PATH_DATA_TRAIN = "data/data_train.txt";
    public static String PATH_DICTIONARY_TRAIN = "data/dic_data_train.txt";

    public static void main(String[] args) {
        try {
            generateDic(PATH_DATA_TRAIN, PATH_DICTIONARY_TRAIN);
        } catch (IOException ex) {
            Logger.getLogger(dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void generateDic(String data_train, String output) throws FileNotFoundException, IOException {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        BufferedReader br = new BufferedReader(new FileReader(data_train));
        PrintStream ps = new PrintStream(new FileOutputStream(new File(output)));
        String data = "";
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
            ps.println(index+":"+word);
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
