package spamfilterpreprocessor;

import IndonesianNLP.IndonesianSentenceFormalization;
import IndonesianNLP.IndonesianStemmer;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author husni
 */
public class SpamFilterPreprocessor {

    /**
     * Normalize and stem "alay" word.
     */
    public static void main(String[] args) {
        // Load csv file to array
        
        ArrayList<String> spamData = new ArrayList();
        spamData = readCSVToList("../data/spam.csv");
        
        ArrayList<String> nonSpamData = new ArrayList();
        nonSpamData = readCSVToList("../data/nonspam.csv");
        
        // Normalize the data
        normalizeData(spamData);
        normalizeData(nonSpamData);
        
        // Stem the data
        stemData(spamData);
        stemData(nonSpamData);
        
        // Output processed data to csv
        outputDataToCSV(spamData, "processedSpamdata");
        outputDataToCSV(nonSpamData, "processedNonSpamdata");
    }
    
    /**
     * Read .csv file and store it to ArrayList.
     */
    public static ArrayList<String> readCSVToList(String filepath) {
        ArrayList<String> data = new ArrayList();
        try {
            CSVReader reader = new CSVReader(new FileReader(filepath));
            String[] nextLine;
            try {
                while ((nextLine = reader.readNext()) != null) {
                    data.add(nextLine[0]);
                }
            } catch (IOException ex) {
                Logger.getLogger(SpamFilterPreprocessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SpamFilterPreprocessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    /**
     * Stem word.
     * @param data 
     */
    public static void stemData(ArrayList<String> data) {
        IndonesianStemmer stemmer = new IndonesianStemmer();
        for (int i = 0; i < data.size(); i++) {
            String stemmedSentence = stemmer.stemSentence(data.get(i));
            data.set(i, stemmedSentence);
        }
    }
    
    /**
     * Normalize alay word.
     * @param data 
     */
    public static void normalizeData(ArrayList<String> data) {
        IndonesianSentenceFormalization sentenceFormalization = new IndonesianSentenceFormalization();
        for (int i = 0; i < data.size(); i++) {
            String normalizedSentence = sentenceFormalization.normalizeSentence(data.get(i));
            data.set(i, normalizedSentence);
        }
    }
    
    public static void outputDataToCSV(ArrayList<String> data, String filename) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("../data/" + filename +".csv"));
            for (String sentence : data) {
                String[] sentences = {sentence};
                writer.writeNext(sentences);
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(SpamFilterPreprocessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

