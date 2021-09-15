
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class LogAnalyzer
{
  private ArrayList<LogEntry> records;
     
  public LogAnalyzer() {
    records = new ArrayList<LogEntry>();    
  }
        
  public void readFile(String filename) throws IOException {
    Path path = Path.of("Logs/" + filename);
    String[] lines = Files.readString(path).split("\\n+");
    WebLogParser lp = new WebLogParser();
    for(String l : lines) {
      records.add(lp.parseEntry(l));
    }
  }
       
  public void printAll() {
    for (LogEntry le : records) {
       System.out.println(le);
    }
  }

  public int countUniqueIPs() {
    ArrayList<String> uniqueIPs = new ArrayList<String>();
    for(LogEntry le : records) {
      String ip = le.getIpAddress();
      if(!uniqueIPs.contains(ip)) {
        uniqueIPs.add(ip);
      }
    }
    return uniqueIPs.size();
  }

  public void printAllHigherThanNum(int n) {
    System.out.println("\nLogs with status code higher than " + n + ":");
    for(LogEntry le : records) {
      int statusCode = le.getStatusCode();
      if(statusCode > n) {
        System.out.println(le);
      }
    }
  }

  public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
    StringBuilder sb = new StringBuilder(someday.toLowerCase());
    sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
    String dayF = sb.toString();
    ArrayList<String> uniqueIPOnDay = new ArrayList<String>();
    for(LogEntry le : records) {
      String ip = le.getIpAddress();
      String date = le.getAccessTime().toString().substring(4,10);
      if(!uniqueIPOnDay.contains(ip) && date.equals(dayF)) {
        uniqueIPOnDay.add(ip);
      }
    }
    return uniqueIPOnDay;
  }

  public int countUniqueIPsInRange(int low, int high) {
    ArrayList<String> uniqueIPs = new ArrayList<String>();
    for(LogEntry le : records) {
      String ip = le.getIpAddress();
      int status = le.getStatusCode();
      if(low <= status && status <= high && !uniqueIPs.contains(ip)) {
        uniqueIPs.add(ip);
      }
    }
    return uniqueIPs.size();
  }

  public HashMap<String, Integer> countVisitsPerIP() {
    HashMap<String, Integer> counts = new HashMap<String, Integer>();
    for(LogEntry le : records) {
      String ip = le.getIpAddress();
      if(counts.containsKey(ip)) {
        counts.put(ip, counts.get(ip) + 1);
      } else {
        counts.put(ip, 1);
      }
    }
    return counts;
  }

  public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
    int max = 0;
    for(Integer visits : counts.values()){
      max = visits.intValue() > max 
        ? visits.intValue()
        : max;
    }
    return max;
  }

  public ArrayList<String> getIPsMostVisits(HashMap<String, Integer> counts, int max) {
    ArrayList<String> iPsMostVisits = new ArrayList<String>();
    for(String ip : counts.keySet()) {
      if(counts.get(ip) == max) {
        iPsMostVisits.add(ip);
      }
    }
    return iPsMostVisits;
  }

  public HashMap<String, ArrayList<String>> iPsForDays() {
    HashMap<String, ArrayList<String>> daysIPs = new HashMap<String, ArrayList<String>>();
    for(LogEntry le : records) {
      String day = le.getAccessTime().toString().substring(4,10);
      String ip = le.getIpAddress();
      if(!daysIPs.containsKey(day)) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(ip);
        daysIPs.put(day, list);
      } else {
        daysIPs.get(day).add(ip);
      }
    }
    return daysIPs;
  }

  public String getDayWithMostIPVisits(HashMap<String, ArrayList<String>> daysIPs) {
    String maxDay = null;
    for(String day : daysIPs.keySet()) {
      if(maxDay == null || daysIPs.get(day).size() > daysIPs.get(maxDay).size()) {
        maxDay = day;
      }
    }
    return maxDay;
  }  

  public ArrayList<String> getIPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> daysIPs, String someday) {
    HashMap<String, Integer> countsSomeday = new HashMap<String, Integer>();
    for(String ip : daysIPs.get(someday)) {
      if(countsSomeday.containsKey(ip)) {
        countsSomeday.put(ip, countsSomeday.get(ip) + 1);
      } else {
        countsSomeday.put(ip, 1);
      }
    }
    int mostVisits = mostNumberVisitsByIP(countsSomeday);
    ArrayList<String> iPsWithMostVisitsOnDay = getIPsMostVisits(countsSomeday, mostVisits);
    return iPsWithMostVisitsOnDay;
  }

}
