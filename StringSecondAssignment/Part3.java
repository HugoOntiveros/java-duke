public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
    int currIndex = dna.indexOf(stopCodon, startIndex+3);
    while ( currIndex != -1 ) {
      if ((currIndex - startIndex) % 3 == 0) {
      return currIndex;
    } else {
      currIndex = dna.indexOf(stopCodon, currIndex+1);
      }
    }
    return dna.length();
  }

  public int countGenes (String dna) {
    int count = 0;
    int startIndex = dna.indexOf("ATG");
    int currBegIndex = startIndex;
    String result = "";
    int indexTAA = dna.length();
    int indexTAG = dna.length();
    int indexTGA = dna.length();    
    if (startIndex == -1) {
      System.out.println("No gene found");
      return count;
    }
    while(currBegIndex < dna.length()) {
      indexTAA = findStopCodon(dna, currBegIndex, "TAA");
      if (indexTAA < dna.length()) {
      result = dna.substring(startIndex, indexTAA+3);
      System.out.println("Gene with termination codon TAA is: " + result);
      count++;
      } else {
        System.out.println("Reached end of DNA strand, did not found more TAA");
      }
      currBegIndex = indexTAA+3;
    }
    currBegIndex = startIndex;
    while(currBegIndex < dna.length()) {
      indexTAG = findStopCodon(dna, currBegIndex, "TAG");
      if (indexTAG < dna.length()) {
      result = dna.substring(startIndex, indexTAG+3);
      System.out.println("Gene with termination codon TAG is: " + result);
      count++;
      } else {
        System.out.println("Reached end of DNA strand, did not found more TAG");
      }
      currBegIndex = indexTAG+3;
    }
    currBegIndex = startIndex;
    while(currBegIndex < dna.length()) {
      indexTGA = findStopCodon(dna, currBegIndex, "TGA");
      if (indexTGA < dna.length()) {
      result = dna.substring(startIndex, indexTGA+3);
      System.out.println("Gene with termination codon TGA is: " + result);
      count++;
      } else {
        System.out.println("Reached end of DNA strand, did not found more TGA");
      }
      currBegIndex = indexTGA+3;
    }
    return count;    
  }

  public void testCountGenes () {
    String dna1 = "TTAGGGCTCTATTAA";
    System.out.println("DNA string #1 (no ATG) is = " + dna1);
    int result = countGenes(dna1);
    System.out.println("Number of genes found = " + result);
    String dna2 = "TTATGGGGCTCTATTA";
    System.out.println("DNA string #2 (no TAA) is = " + dna2);
    result = countGenes(dna2);
    System.out.println("Number of genes found = " + result);
    String dna3 = "TTATCGGGGCTCTATTA";
    System.out.println("DNA string #3 (no ATG nor TAA) is = " + dna3);
    result = countGenes(dna3);
    System.out.println("Number of genes found = " + result);
    String dna4 = "TTATGGGGCTCTATTAACT";
    System.out.println("DNA string #4 (Gene) is = " + dna4);
    result = countGenes(dna4);
    System.out.println("Number of genes found = " + result);
    String dna5 = "TTATGGGGCTCTCATTAAC";
    System.out.println("DNA string #5 (no ORF) is = " + dna5);
    result = countGenes(dna5);
    System.out.println("Number of genes found = " + result);
    String dna6 = "TTATGGGGCTTCATTAGTGATAAC";
    System.out.println("DNA string #6 (multiple stop codns) is = " + dna6);
    result = countGenes(dna6);
    System.out.println("Number of genes found = " + result);
    String dna7 = "ATGCCCCCCCCCTAACCCTAATGACCCTGATAGCCCTAG";
    System.out.println("DNA string #7 (multiple stop codns) is = " + dna7);
    result = countGenes(dna7);
    System.out.println("Number of genes found = " + result);
  }
}