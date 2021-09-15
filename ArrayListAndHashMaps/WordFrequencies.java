import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class WordFrequencies {
  private ArrayList<String> myWords = new ArrayList<String>();
  private ArrayList<Integer> myFreqs = new ArrayList<Integer>();
  
  private int findUnique(String[] words) {
    myWords.clear();
    myFreqs.clear();
    for(String s : words){
      int index = myWords.indexOf(s);
      if(index == -1){
        myWords.add(s);
        myFreqs.add(1);
      } else{
        myFreqs.set(index, myFreqs.get(index)+1);
      }
    }
    return myWords.size();
  }

  private int indexOfMax(){
    int max = 0;
    for(int i=1; i<myFreqs.size(); i++){
      max = myFreqs.get(max) < myFreqs.get(i) ? i : max;
    }
    return max;
  }

  public void test(String fileName) throws IOException {
    Path path = Path.of("Texts/" + fileName + ".txt");
    String[] words = Files.readString(path).toLowerCase().split("\\s+");    
    System.out.println("Number of unique words: " + findUnique(words));
/*     for(int i=0; i<myWords.size(); i++){
      System.out.println(myFreqs.get(i) + " " + myWords.get(i));
    } */
    int mostCommon = indexOfMax();
    System.out.println("The word that occurs most often and its count is: \"" + myWords.get(mostCommon) + "\" " + myFreqs.get(mostCommon) + " times");
  }
}