import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CaesarCipher{
  private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private String shiftedAlphabet;
  private int key;

  public CaesarCipher(int key){
    this.key = key;
    this.shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
  }

  public String encrypt(String input){
    StringBuilder sb = new StringBuilder(input);
    for(int i=0; i<input.length(); i++){
      char ch = sb.charAt(i);
      int index = alphabet.indexOf(Character.toUpperCase(ch));
      if (index != -1){
        char encChar = Character.isUpperCase(ch) 
          ? shiftedAlphabet.charAt(index)
          : shiftedAlphabet.toLowerCase().charAt(index);
        sb.setCharAt(i, encChar);
      }
    }
    return sb.toString();
  }

  public String decrypt(String encrypted){
    CaesarCipher cc = new CaesarCipher(26 - key);
    return cc.encrypt(encrypted);
  }

  private int[] countLetters(String message){
    int[] letterCounts = new int[26];
    message = message.toUpperCase();
    for(int i=0; i<message.length(); i++){
      int index = alphabet.indexOf(message.charAt(i));
      if(index != -1){
        letterCounts[index]++;
      }
    }
    return letterCounts;
  }

  private int maxIndex(int[] counts){
    int index = 0;
    for(int i=1; i<counts.length; i++){
      index = counts[i] > counts[index] ? i : index; 
    }
    return index;
  }

  private int getKey(String encrypted){
    int[] counts = countLetters(encrypted);
    int mostFrequent = maxIndex(counts);
    //Compute encryption key, assumming the mostFrequent letter corresponds to e, the most frequent letter in English.
    int key = mostFrequent > 4 
      ? mostFrequent - 4
      : 26 + mostFrequent - 4;
    return key;
  }

  public String breakCaesarCipher(String encrypted){
    int crackedKey = getKey(encrypted);
    System.out.println("Cracked key is: " + crackedKey);
    CaesarCipher cc = new CaesarCipher(26-crackedKey);
    return cc.encrypt(encrypted);
  }  
   
  public void testCaesar(String file) throws IOException{
    String filename = file;
    Path path = Path.of("Messages/" + filename + ".txt");
    String message = Files.readString(path);
    String encrypted = encrypt(message);
    String decrypted = decrypt(encrypted);
    System.out.println("Encrypted message:\n" +
    encrypted + "\n" +
    "Decrypted message:\n" +
    decrypted);
    String cracked = breakCaesarCipher(encrypted);
    System.out.println("Cracked message:\n"+
    cracked);
  }
}