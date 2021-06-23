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
  
  public String findGene(String dna, int startPos) {
    int startIndex = dna.indexOf("ATG", startPos);
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
    String gene = dna.substring(startIndex, minIndex+3);
    return gene;
  }

  public void testGene() {
    String dna1 = "TTAGGGCTCTATTAA";
    System.out.println("DNA string #1 (no ATG) is = " + dna1);
    String result = findGene(dna1,0);
    System.out.println("Gene " + result);
    String dna2 = "TTATGGGGCTCTATTA";
    System.out.println("DNA string #2 (no TAA) is = " + dna2);
    result = findGene(dna2,0);
    System.out.println("Gene is " + result);
    String dna3 = "TTATCGGGGCTCTATTA";
    System.out.println("DNA string #3 (no ATG nor TAA) is = " + dna3);
    result = findGene(dna3,0);
    System.out.println("Gene is " + result);
    String dna4 = "TTATGGGGCTCTATTAACT";
    result = findGene(dna4,0);
    System.out.println("DNA string #4 (Gene) is = " + dna4);
    System.out.println("Gene is " + result);
    String dna5 = "TTATGGGGCTCTCATTAAC";
    System.out.println("DNA string #5 (no ORF) is = " + dna5);
    result = findGene(dna5,0);
    System.out.println("Gene is " + result);
    String dna6 = "TTATGGGGTGAAATTAGTGATAAC";
    System.out.println("DNA string #6 (multiple stop codons) is = " + dna6);
    result = findGene(dna6,0);
    System.out.println("Gene is " + result);
    String dnaExam = "AATGCTAACTAGCTGACTAAT";
    System.out.println("DNA string from exam is: " + dnaExam);
    result = findGene(dnaExam,0);
    System.out.println("Gene is " + result);
  }

  public StorageResource getAllGenes (String dna) {
    StorageResource geneList = new StorageResource();
    int startIndex = dna.indexOf("ATG");
    String gene = "";    
    while(startIndex != -1 && startIndex < dna.length()) {
      gene = findGene(dna, startIndex);
      if (gene.isEmpty()) {
        startIndex = dna.indexOf("ATG", startIndex+3);
      } else {
        geneList.add(gene);
        // Para encontrar todos los genes posibles: startIndex = startIndex + gene.length();
        startIndex = dna.indexOf("ATG", startIndex+gene.length());
      }
    }
  return geneList;
  }

  public void testAllGenes(String dna) {
    System.out.println("Testing getAllGenes on " + dna);
    StorageResource genes = getAllGenes(dna);
    for (String g : genes.data()) {
      System.out.println(g);
    }
  }
}