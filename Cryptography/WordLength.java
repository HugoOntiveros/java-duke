import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WordLength{
  public int[] countWordLength(String text, int[] counts){
    String[] words = text.split(" |\n|\t");
    for(String s : words){
      int length = s.length();
      if(length <= 0){
        continue;
      }
      if(!Character.isLetter(s.charAt(0)) && length>1){
        length--;
      }
      if(!Character.isLetter(s.charAt(length-1))){
        length--;
      }
      int arrLen = counts.length - 1;
      if(length > arrLen){
        length = arrLen;
      } if(length>0) {
        counts[length]++;
      }
    }
    return counts;
  }

  public void testCount (String filename) throws IOException {
    String message = Files.readString(Path.of("Messages/" + filename + ".txt"));
    int[] counts = new int[31];
    countWordLength(message, counts);
    for(int i=0; i<counts.length; i++){
      if(counts[i] != 0){
        System.out.println("Words of length " + i + ": " + counts[i]);
      }
    }
  }
}