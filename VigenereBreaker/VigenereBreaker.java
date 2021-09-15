import edu.duke.*;
import java.io.File;
import java.util.HashSet;
import java.util.HashMap;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices) {
          sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i=0; i<klength; i++){
          String slice = sliceString(encrypted, i, klength);
          key[i] = cc.getKey(slice);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr) {
      HashSet<String> words = new HashSet();
      for(String line : fr.lines()) {
        if(!words.contains(line.toLowerCase())) {
          words.add(line.toLowerCase());
        }
      }
      return words;
    }

    public int countWords(HashSet<String> words, String message) {
      int count = 0;
      String[] messageWords = message.toLowerCase().split("\\W");
      for(String s : messageWords) {
        count = words.contains(s) ? count+1 : count;
      }
      return count;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
      int max = 0;
      int[] freqs = new int[26];
      String alphabet = "abcdefghijklmnopqrstuvwxyz";
      for(String s : dictionary) {
        char[] chars = s.toCharArray();
        for(int i=0; i<chars.length; i++) {
          int index = alphabet.indexOf(chars[i]);
          if(index != -1) {
            freqs[index]++;
          }
        }
      }
      for(int i=1; i<freqs.length; i++) {
        if(freqs[max] < freqs[i]) {
          max = i;
        }
      }
      return alphabet.charAt(max);
    }

    public String breakForLanguage (String encrypted, HashSet<String> dictionary) {
      int maxMatches = 0;
      int kLength = 0;
      String bestDecryption = null;
      char mostCommon = mostCommonCharIn(dictionary); 
      for(int i=1; i<=100; i++) {
        int[] key = tryKeyLength(encrypted, i, mostCommon);
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        int matches = countWords(dictionary, decrypted);
        if(matches > maxMatches) {
          maxMatches = matches;
          bestDecryption = decrypted;
          kLength = i;
        }
      }
/*       System.out.println("Cracked key length is: " + kLength + "\nWord matches: " + maxMatches); */
      return bestDecryption;
    }

    public String breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> dictionaries) {
      int maxMatches = 0;
      String bestDecryption = null;
      String decryptLanguage = null;
      for(String language : dictionaries.keySet()) {
        HashSet<String> dictionary = dictionaries.get(language);
        String decrypted = breakForLanguage(encrypted, dictionary);
        int matches = countWords(dictionary, decrypted);
        if(matches > maxMatches) {
          maxMatches = matches;
          bestDecryption = decrypted;
          decryptLanguage = language;
        }
      }
      System.out.println("Language is " + decryptLanguage + "\nWord matches: " + maxMatches);
      return bestDecryption;
    }

    public void breakVigenere() {
      FileResource fr = new FileResource();
      String encrypted = fr.asString();
      HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
      DirectoryResource dr = new DirectoryResource();
      for(File f : dr.selectedFiles()) {
        String fileName = f.getName();
        FileResource dic = new FileResource(f);
        HashSet<String> dictionary = readDictionary(dic);
        dictionaries.put(fileName, dictionary);
      }
      String decrypted = breakForAllLanguages(encrypted, dictionaries);


/*       FileResource dic = new FileResource();
      HashSet<String> dictionary = readDictionary(dic); */
      //System.out.println(decrypted);
      //Only print the first lines of the decrypted text
      System.out.println(decrypted.substring(0,60));
    }    
    
}
