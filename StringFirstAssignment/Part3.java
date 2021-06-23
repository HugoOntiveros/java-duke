public class Part3 {
  public boolean twoOcurrenes (String a, String b) {
    int index1 = b.indexOf(a);
    if (index1 == -1) {
      return false;
    }
    int index2 = b.indexOf(a, index1+a.length());
    if (index2 == -1) {
      return false;
    }
    return true;
  }

  public String lastPart (String a, String b) {
    int index1 = b.indexOf(a);
    if (index1 == -1) {
      return b;
    }
    String c = b.substring(index1+a.length());
    return c;
  }

  public void testing () {
    boolean result = twoOcurrenes("a", "vanana");
    System.out.println("2 a in vanana? " + result);
    result = twoOcurrenes("hola", "crayola");
    System.out.println("2 hola in crayola? " + result);
    String answer = lastPart("an", "banana");
    System.out.println("Last part of banana after an = " + answer);
    answer = lastPart("zoo", "zoolander");
    System.out.println("Last part of zoolandar after zoo = " + answer);
    answer = lastPart("hello", "world");
    System.out.println("Last part of hello after world = " + answer);
  }
}