import edu.duke.*;
import java.util.*;

public class GladMap {
	private HashMap<String, ArrayList<String>> myMap;
  
  private HashMap<String, ArrayList<String>> used;
  private int substituted;
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladMap(){
    myMap = new HashMap<String, ArrayList<String>>();
    used = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladMap(String source){
    myMap= new HashMap<String, ArrayList<String>>();
    used = new HashMap<String, ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
    String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
    for(String s : labels) {
      ArrayList<String> list = readIt(source+"/"+s+".txt");
      myMap.put(s, list);
    }	
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
    if(label.equals("number")){
      return ""+myRandom.nextInt(50)+5;
    }
    return myMap.containsKey(label) 
      ? randomFrom(myMap.get(label))
      : "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
    String label = w.substring(first+1,last);
		String sub = getSubstitute(label);
    if(used.containsKey(label)){
      while(used.get(label).contains(sub)){
        sub = getSubstitute(label);
      }
    } else {
      ArrayList<String> list = new ArrayList<String>();
      used.put(label, list);
    }
    used.get(label).add(sub);
    substituted++;
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
    System.out.println("\nNumber of words substituted: " + substituted);
    System.out.println("Number of words to choose from: " + totalWordsInMap());
    System.out.print("Number of words considered: " + totalWordsConsidered());
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}

  private int totalWordsInMap() {
    int totalWords = 0;
    for(ArrayList<String> al : myMap.values()){
      totalWords += al.size();
    }
    return totalWords;
  }

  private int totalWordsConsidered(){
    int wordsConsidered = 0;
    for(String k : used.keySet()){
      wordsConsidered = myMap.containsKey(k) 
        ? myMap.get(k).size() + wordsConsidered 
        : wordsConsidered;
    }
    return wordsConsidered;
  }
	
	public void makeStory(){
	  System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
	}
	
}