import edu.duke.URLResource;

public class Part4 {
  public void findWebLinks () {
    URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
    String findURL = "youtube.com";
    String url = "";
    String result = "";
    int index = 0;
    for (String word : ur.words()) {
      if (word.startsWith("href")) {
        url = word.replace("href=\"", "").replace("\">", "");
        index = url.toLowerCase().indexOf(findURL);
        if (index != -1) {
          System.out.println(url);
        }
      }
    }
  }
}