import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class CharactersInPlay {
  private ArrayList<String> characters = new ArrayList<String>();
  private ArrayList<Integer> counts = new ArrayList<Integer>();
  
  private void update(String person) {
      int index = characters.indexOf(person);
      if(index == -1){
        characters.add(person);
        counts.add(1);
      } else{
        counts.set(index, counts.get(index)+1);
      }
  }

  private void findAllCharacters(String[] lines){
    for(String l : lines){
      int index = l.indexOf('.');
      if(index != -1){
        update(l.substring(0, index));
      }
    }
  }

  private void charactersWithNumParts(int n1, int n2){
    for(int i=0; i<characters.size(); i++){
      if(counts.get(i)>=n1 && counts.get(i)<=n2){
        System.out.println(characters.get(i) + ": " + counts.get(i));
      }
    }
  }

  public void test(String fileName) throws IOException {
    Path path = Path.of("Texts/" + fileName + ".txt");
    String[] lines = Files.readString(path).toUpperCase().split("\\n+");    
    findAllCharacters(lines);
    charactersWithNumParts(10, 15);
  }
}