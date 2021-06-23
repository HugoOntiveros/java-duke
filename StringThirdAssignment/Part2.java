public class Part2 {
  public float cgRatio (String dna) {
    float cgCount = 0.0f;
    int startIndexC = dna.indexOf("C");
    int startIndexG = dna.indexOf("G");
    while (startIndexC != -1) {
      cgCount = cgCount + 1.0f;
      startIndexC = dna.indexOf("C", startIndexC+1);    
    }
    while (startIndexG != -1) {
      cgCount = cgCount + 1.0f;
      startIndexG = dna.indexOf("G", startIndexG+1);    
    }
    float ratio = cgCount / dna.length();
    return ratio;
  }

  public int countCTG (String dna) {
    int count = 0;
    int startIndex = dna.indexOf("CTG");
    while (startIndex != -1) {
      count++;
      startIndex = dna.indexOf("CTG", startIndex+3);
    }
    return count;
  }

  public void testCGRatio () {
    String dna1 = "ATGCCATAG";
    System.out.println("DNA strand to analyze " + dna1);
    float ratioResult = cgRatio(dna1);
    System.out.println("CG ratio = " + ratioResult);
    int ctgCount = countCTG(dna1);
    System.out.println("How many CTGs? " + ctgCount);
  }
}