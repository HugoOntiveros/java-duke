public class Part1 {
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
  
  public String findGene(String dna) {
    int startIndex = dna.indexOf("ATG");
    if (startIndex == -1) {
      return "";
    }
    int indexTAA = findStopCodon(dna, startIndex, "TAA");
    int indexTAG = findStopCodon(dna, startIndex, "TAG");
    int indexTGA = findStopCodon(dna, startIndex, "TGA");
    int minIndex = dna.length();
    if (indexTAA == indexTAG && indexTAA == indexTGA) {
      return ""; 
    } 
    if (indexTAA >= indexTAG) {
      if (indexTAG > indexTGA) {
        minIndex = indexTGA;
      } else {
        minIndex = indexTAG;
      }
    } else {
      if (indexTAA > indexTGA) {
        minIndex = indexTGA;
      } else {
        minIndex = indexTAA;
      }
    } 
    System.out.println(minIndex);
    String gene = dna.substring(startIndex, minIndex+3);
    return gene;
  }

  public void testGene() {
    String dna1 = "TTAGGGCTCTATTAA";
    System.out.println("DNA string #1 (no ATG) is = " + dna1);
    String result = findGene(dna1);
    System.out.println("Gene " + result);
    String dna2 = "TTATGGGGCTCTATTA";
    System.out.println("DNA string #2 (no TAA) is = " + dna2);
    result = findGene(dna2);
    System.out.println("Gene is " + result);
    String dna3 = "TTATCGGGGCTCTATTA";
    System.out.println("DNA string #3 (no ATG nor TAA) is = " + dna3);
    result = findGene(dna3);
    System.out.println("Gene is " + result);
    String dna4 = "TTATGGGGCTCTATTAACT";
    result = findGene(dna4);
    System.out.println("DNA string #4 (Gene) is = " + dna4);
    System.out.println("Gene is " + result);
    String dna5 = "TTATGGGGCTCTCATTAAC";
    System.out.println("DNA string #5 (no ORF) is = " + dna5);
    result = findGene(dna5);
    System.out.println("Gene is " + result);
    String dna6 = "TTATGGGGTGAAATTAGTGATAAC";
    System.out.println("DNA string #6 (multiple stop codons) is = " + dna6);
    result = findGene(dna6);
    System.out.println("Gene is " + result);
    String dnaExam = "AATGCTAACTAGCTGACTAAT";
    System.out.println("DNA string from exam is: " + dnaExam);
    result = findGene(dnaExam);
    System.out.println("Gene is " + result);
  }

  public void printAllGenes (String dna) {
    int startIndex = dna.indexOf("ATG");
    int currBegIndex = startIndex;
    String result = "";
    int indexTAA = dna.length();
    int indexTAG = dna.length();
    int indexTGA = dna.length();    
    if (startIndex == -1) {
      System.out.println("No gene found");
      return;
    }
    while(currBegIndex < dna.length()) {
      indexTAA = findStopCodon(dna, currBegIndex, "TAA");
      if (indexTAA < dna.length()) {
      result = dna.substring(startIndex, indexTAA+3);
      System.out.println("Gene with termination codon TAA is: " + result);
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
      } else {
        System.out.println("Reached end of DNA strand, did not found more TGA");
      }
      currBegIndex = indexTGA+3;
    }    
  }
}