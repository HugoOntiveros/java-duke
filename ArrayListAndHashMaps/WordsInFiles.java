import java.util.HashMap;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class WordsInFiles {
  private HashMap<String, ArrayList<String>> wordFiles = new HashMap<String, ArrayList<String>>();

  private void addWordsFromFile(String fileName) throws IOException {
    Path path = Path.of(fileName);
    String[] words = Files.readString(path).split("\\s+");
    for(String w : words) {
      if(wordFiles.containsKey(w)) {
        if(!wordFiles.get(w).contains(fileName)) {
          wordFiles.get(w).add(fileName);
        }
      } else {
        ArrayList<String> list = new ArrayList<String>();
        list.add(fileName);
        wordFiles.put(w, list);
      }
    }
  }

  private void buildWordMap(String prefix, int files) throws IOException {
    for(int i=1; i<=files; i++){
      String fileName = "WordsInFiles/" + prefix + i + ".txt";
      addWordsFromFile(fileName);
    }
  }

  private int maxNumber() {
    int max = 0;
    for(ArrayList<String> al : wordFiles.values()){
      max = al.size() > max ? al.size() : max;
    }
    return max;
  }

  private ArrayList<String> wordsInNumFiles(int number) {
    ArrayList<String> list = new ArrayList<String>();
    for(String k : wordFiles.keySet()){
      if(wordFiles.get(k).size() == number){
        list.add(k);
      }
    }
    return list;
  }

  private void printFilesIn(String word) {
    System.out.println(word + " in:");
    for(String f : wordFiles.get(word)) {
      System.out.println("\t" + f.substring(f.indexOf("/")+1));
    }
  }

  public void test(String prefix, int files) throws IOException {
    buildWordMap(prefix, files);
/*     for(String w : wordFiles.keySet()){
      System.out.println(w + ": " + wordFiles.get(w));
    } */
    for(String w : wordFiles.keySet()) {
      System.out.println(w + " appears in " + wordFiles.get(w).size() + " file(s).");
    }
    int max = maxNumber();
    ArrayList<String> maxWords = wordsInNumFiles(max);
    System.out.println("\n" + maxWords.size() + " words that appear most files (" + max + ") are:");
    for(String s : maxWords) {
      printFilesIn(s);
    }
    ArrayList<String> in4Files = wordsInNumFiles(4);
    System.out.println("\n" + in4Files.size() + " words that appear in 4 files are:");
    for(String s : in4Files) {
      printFilesIn(s);
    }
  }
}