import java.io.IOException;

class Main {
  public static void main(String[] args) throws IOException {
/*     System.out.println("Simple Cryptography exercises.");
    WordPlay wp = new WordPlay();
    System.out.println(wp.isVowel('A'));
    System.out.println(wp.isVowel('F'));
    System.out.println(wp.replaceVowels("Hello World", '*'));
    System.out.println(wp.emphasize("Mary Bella Abracadabra", 'a')); */
/*     CaesarCipher cc23 = new CaesarCipher(23);
    cc23.testCaesar("Message1");
    CaesarCipher cc8 = new CaesarCipher(8);
    cc8.testCaesar("Message2"); */
    CaesarCipherTwo cct = new CaesarCipherTwo(23,17);
    cct.testCaesarTwo("Message1");
    CaesarCipherTwo cct1 = new CaesarCipherTwo(8,21);
    cct1.testCaesarTwo("Message2");
/*     WordLength wl = new WordLength();
    wl.testCount("Message3"); */
  }
}