public class WordPlay{
  public boolean isVowel(char ch){
    char chl = Character.toLowerCase(ch);
    if(chl == 'a' || chl == 'e' || chl == 'i' || chl == 'o' || chl == 'u'){
      return true;
    } else {
      return false;
    }
  }

  public String replaceVowels(String phrase, char ch){
    StringBuilder sb = new StringBuilder(phrase);
    for(int i=0; i<phrase.length(); i++){
      if(isVowel(sb.charAt(i))){
        sb.setCharAt(i, ch);
      }
    }
    return sb.toString();
  }

  public String emphasize(String phrase, char ch){
    StringBuilder sb = new StringBuilder(phrase);
    for(int i=0; i<phrase.length(); i++){
      if(Character.toLowerCase(sb.charAt(i)) == Character.toLowerCase(ch)){
        if(i%2 == 0){
          sb.setCharAt(i, '*');
        } else {
          sb.setCharAt(i, '+');
        }
      }
    }
    return sb.toString();
  }
}