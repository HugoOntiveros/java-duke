import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CaesarCipherTwo{
  private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private String shiftedAlphabet1;
  private String shiftedAlphabet2;
  private int key1;
  private int key2;

  public CaesarCipherTwo(int key1, int key2){
    this.key1 = key1;
    this.shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
    this.key2 = key2;
    this.shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
  }

  public String encryptTwoKeys(String input){
    StringBuilder sb = new StringBuilder(input);
    for(int i=0; i<input.length(); i += 2){
      char ch = sb.charAt(i);
      int index = alphabet.indexOf(Character.toUpperCase(ch));
      if (index != -1){
        char encChar = Character.isUpperCase(ch) 
          ? this.shiftedAlphabet1.charAt(index)
          : this.shiftedAlphabet1.toLowerCase().charAt(index);
        sb.setCharAt(i, encChar);
      }
    }
    for(int i=1; i<input.length(); i += 2){
      char ch = sb.charAt(i);
      int index = alphabet.indexOf(Character.toUpperCase(ch));
      if (index != -1){
        char encChar = Character.isUpperCase(ch) 
          ? this.shiftedAlphabet2.charAt(index)
          : this.shiftedAlphabet2.toLowerCase().charAt(index);
        sb.setCharAt(i, encChar);
      }
    }
    return sb.toString();
  }

  public String decryptTwoKeys(String encrypted){
    CaesarCipherTwo cct = new CaesarCipherTwo(26-key1, 26-key2);
    return cct.encryptTwoKeys(encrypted);
  }

  private String halfOfString(String message, int start){
    StringBuilder sb = new StringBuilder();
    for(int i=start; i<message.length(); i+=2){
      sb.append(message.charAt(i));
    }
    return sb.toString();
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
    int key = mostFrequent > 4 
      ? mostFrequent - 4
      : 26 + mostFrequent - 4;
    return key;
  }

  public String breakCaesarCipherTwo(String encrypted){
    int crackedKey1 = getKey(halfOfString(encrypted,0));
    int crackedKey2 = getKey(halfOfString(encrypted,1));
    System.out.println("Cracked key 1 is " + crackedKey1 + " and cracked key 2 is " + crackedKey2);
    CaesarCipherTwo cct = new CaesarCipherTwo(26-crackedKey1, 26-crackedKey2);
    return cct.encryptTwoKeys(encrypted);
  } 

  public void testCaesarTwo(String filename) throws IOException {
    String message2Keys = Files.readString(Path.of("Messages/" + filename + ".txt"));
    String encrypted2Keys = encryptTwoKeys(message2Keys);
    System.out.println("Message encrypted with key 1: " + this.key1 + " and key 2: " + this.key2 + ":\n" + encrypted2Keys);
    String decrypted2Keys = decryptTwoKeys(encrypted2Keys);
    System.out.println("Message decrypted:\n" + 
    decrypted2Keys);
    String cracked2Keys = breakCaesarCipherTwo(message2Keys);
    System.out.println("Cracked message:\n"+
    cracked2Keys + "\n");
  }
}