import java.io.IOException;

class Main {
  public static void main(String[] args) throws IOException {
    System.out.println("ArrayList and HashMap\n");
/*     WordFrequencies wf = new WordFrequencies();
    wf.test("Test"); */
/*     CharactersInPlay cip = new CharactersInPlay();
    cip.test("Macbeth"); */
/*     CodonsInStrand cis = new CodonsInStrand();
    cis.test("smallDNA"); */
    WordsInFiles wif = new WordsInFiles();
    wif.test("brief", 4);
  }
}