public class Part2 {
  public int howMany (String a, String b) {
    int count = 0;
    int startIndex = b.indexOf(a);
    while (startIndex != -1) {
      count++;
      startIndex = b.indexOf(a,startIndex+a.length());
    }
    return count;
  }

  public void testHowMany () {
    int result = howMany("GAA", "ATGAACGAATTGAATC");    
    System.out.println("How many GAA in ATGAACGAATTGAATC? " + result);
    result = howMany("AA", "AATTAAAA");
    System.out.println("How many AA in AATTAAAA? " + result);
    result = howMany("ATG", "ATCATC");
    System.out.println("How many ATG in ATCATC? " + result);
  }
}