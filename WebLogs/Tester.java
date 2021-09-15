
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.IOException;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public static void testLogAnalyzer() throws IOException {
      LogAnalyzer la = new LogAnalyzer();
      la.readFile("weblog2_log");
      //la.printAll();
      int uniqueIPs = la.countUniqueIPs();
      System.out.println("\nNumber of unique IP addresses: " + uniqueIPs);
      //la.printAllHigherThanNum(400);
      String someday = "Sep 24";
      ArrayList<String> uniqueIPOnDay =  la.uniqueIPVisitsOnDay(someday);
      System.out.println("\nUnique visits per IP on day " + someday + ":\n" + uniqueIPOnDay.size());
      int uniqueIPsInRange = la.countUniqueIPsInRange(400,499);
      System.out.println("\nNumber of unique IP addresses with status code ranging from 400 to 499: " + uniqueIPsInRange);
      HashMap<String, Integer> counts = la.countVisitsPerIP();
      //System.out.println("\nVisits per IP count:\n" + counts);
      int mostVisits = la.mostNumberVisitsByIP(counts);
      System.out.println("Most Number of visits by IP: " + mostVisits);
      ArrayList<String> iPsMostVisits = la.getIPsMostVisits(counts, mostVisits);
      System.out.println("IPs with most visits:\n" + iPsMostVisits);
      HashMap<String, ArrayList<String>> daysIPs = la.iPsForDays();
      //System.out.println("\nIP visits per day:\n" + daysIPs);
      String dayMostVisits = la.getDayWithMostIPVisits(daysIPs);
      System.out.println("Day with most visits: " + dayMostVisits);
      String someday2 = "Sep 30";
      ArrayList<String> iPsWithMostVisitsOnDay = la.getIPsWithMostVisitsOnDay(daysIPs, someday2);
      System.out.println("IPs with most visits on day " + someday2 + ":\n" + iPsWithMostVisitsOnDay);
    }
}
