public class Part2 {
  public String findSimpleGene(String dna, String startCodon, String stopCodon) {
    int indexStart = dna.indexOf(startCodon);
    if (indexStart == -1) {
      return "";
    }
    int indexStop = dna.indexOf(stopCodon, indexStart+3);
    if (indexStop == -1) {
      return "";
    }
    String gene = dna.substring(indexStart, indexStop + 3);
    if (gene.length() % 3 == 0) {
      return gene;
    } else {
      return "";
    }
  }

  public void testSimpleGene() {
    String startCodon = "ATG";
    String stopCodon = "TAA";
    String dna1 = "TTAGGGCTCTATTAA";
    System.out.println("DNA string #1 (no ATG) is = " + dna1);
    String result = findSimpleGene(dna1, startCodon, stopCodon);
    System.out.println("Gene " + result);
    String dna2 = "TTATGGGGCTCTATTA";
    System.out.println("DNA string #2 (no TAA) is = " + dna2);
    result = findSimpleGene(dna2, startCodon, stopCodon);
    System.out.println("Gene is " + result);
    String dna3 = "TTATCGGGGCTCTATTA";
    System.out.println("DNA string #3 (no ATG nor TAA) is = " + dna3);
    result = findSimpleGene(dna3, startCodon, stopCodon);
    System.out.println("Gene is " + result);
    String dna4 = "TTATGGGGCTCTATTAACT";
    result = findSimpleGene(dna4, startCodon, stopCodon);
    System.out.println("DNA string #4 (Gene) is = " + dna4);
    System.out.println("Gene is " + result);
    String dna5 = "TTATGGGGCTCTCATTAAC";
    System.out.println("DNA string #5 (no ORF) is = " + dna5);
    result = findSimpleGene(dna5, startCodon, stopCodon);
    System.out.println("Gene is " + result);
    result = findSimpleGene("AAATGCCCTAACTAGATTAAGAAACC", startCodon, stopCodon);
    System.out.println("Gene located in string AAATGCCCTAACTAGATTAAGAAACC is: " + result);
  }
}