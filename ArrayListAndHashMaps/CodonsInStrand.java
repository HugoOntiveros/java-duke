import java.util.HashMap;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class CodonsInStrand {
  private HashMap<String, Integer> codonCount = new HashMap<String, Integer>();

  private int buildCodonMap(int start, String strand) {
    codonCount.clear();
    for(int i=start; i<strand.length()-2; i += 3) {
      String codon = strand.substring(i, i+3);
      if(codonCount.containsKey(codon)) {
        codonCount.put(codon, codonCount.get(codon)+1);
      } else{
        codonCount.put(codon, 1);
      }
    }
    return codonCount.size();
  }

  private String getMostCommonCodon() {
    String maxKey = null;
    for(String c : codonCount.keySet()) {
      if(maxKey == null || codonCount.get(c).compareTo(codonCount.get(maxKey)) > 0) {
        maxKey = c;
      } 
    }
    return maxKey;
  }

  public void printCodonCounts(int start, int end) {
    System.out.println("Codons with counts between " + start + " and " + end + " are:");
    for(String c : codonCount.keySet()){
      if(codonCount.get(c) >= start && codonCount.get(c) <= end) {
        System.out.println(c + ": " + codonCount.get(c));
      }
    }
  }

  public void test(String fileName) throws IOException {
    Path path = Path.of("Texts/" + fileName + ".txt");
    String strand = Files.readString(path).toUpperCase().trim();
    for(int i=0; i<3; i++) {
      int unique = buildCodonMap(i, strand);
      System.out.println("\nAnalyzing strand with ORF " + i + ": " + unique + " codons found!");
      String mostCommon = getMostCommonCodon();
      System.out.println("Most common codon is " + mostCommon + " with " + codonCount.get(mostCommon) + " counts.");
      printCodonCounts(7,7);
    }
  }
} 