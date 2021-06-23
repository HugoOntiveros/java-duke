import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class babyNames {
  public void totalBirths (FileResource fr) {
    int totalBirths = 0;
    int totalBoys = 0;
    int totalGirls = 0;
    int boysNames = 0;
    int girlsNames = 0;
    for (CSVRecord record : fr.getCSVParser(false)) {
      int numBorn = Integer.parseInt(record.get(2));
      totalBirths += numBorn;
      if (record.get(1).equals("M")) {
        totalBoys += numBorn;
        boysNames++;
      } else {
        totalGirls += numBorn;
        girlsNames++;
      }
    }
    System.out.println("Total births: " + totalBirths);
    System.out.println("Total boys born: " + totalBoys);
    System.out.println("Total boys' names: " + boysNames);
    System.out.println("Total girls born: " + totalGirls);
    System.out.println("Total girls' names: " + girlsNames);
  }

  public int getRank (int year, String name, String gender) {
    int rank = -1;
    int count = 0;
    int girlsNames = 0;
    String filename = "us_babynames_by_year/yob" + year + ".csv";
    FileResource fr = new FileResource(filename);
    for (CSVRecord record : fr.getCSVParser(false)) {
      count++;
      String currName = record.get(0);
      String currGender = record.get(1);
      girlsNames = (currGender.equals("F")) ? girlsNames+1 : girlsNames;
      if (currName.equals(name) && currGender.equals(gender)) {
        rank = (currGender.equals("F")) ? count : count-girlsNames;
      }
    }
    return rank;
  }

  public String getName (int year, int rank, String gender) {
    String name = "NO NAME";
    int count = 0;
    int girlsNames = 0;
    String filename = "us_babynames_by_year/yob" + year + ".csv";
    FileResource fr = new FileResource(filename);
    for (CSVRecord record : fr.getCSVParser(false)) {
      count++;
      String currName = record.get(0);
      String currGender = record.get(1);
      if (currGender.equals("F")) {
        girlsNames++;
        name = (count == rank && currGender.equals(gender)) ? currName : name;
      } else if (currGender.equals("M")) {
        int boysRank = count - girlsNames;
        name = (boysRank == rank && currGender.equals(gender)) ? currName : name;
      }
    }
    return name;
  }

  public int getTotalBirthsRankedHigher (int year, String name, String gender) {
    int births = 0;
    int count = 1;
    int rank = getRank(year, name, gender);
    String filename = "us_babynames_by_year/yob" + year + ".csv";
    FileResource fr = new FileResource(filename);
    for (CSVRecord record : fr.getCSVParser(false)) {
      if (record.get(1).equals(gender) && count<rank) {
        count++;
        births += Integer.parseInt(record.get(2));
      }
    }
    return births;
  }

  public void tester () {
    FileResource fr = new FileResource("us_babynames_by_year/yob2014.csv");
    totalBirths(fr);
    int rank = getRank(2014, "Frank", "M");
    System.out.println("Rank is: " + rank);
    String name = getName(1980, 350, "F");
    System.out.println("Name is: " + name);
    int birthsRankedHigher = getTotalBirthsRankedHigher(1990, "Drew", "M");
    System.out.println("Total births ranked higher is: " + birthsRankedHigher);
  }

  public void whatIsNameInYear (String name, int year, int newYear, String gender) {
    int rank = getRank(year, name, gender);
    System.out.println(name + " rank is " + rank);
    String nameYear = getName(newYear, rank, gender);
    System.out.println(name + " born in " + year+ " would have been named " + nameYear + " in " + newYear);
  }

  public int yearOfHighestRank (String name, String gender) {
    int highestRank = -1;
    int yearOfHighestRank = -1;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      String yearName = f.getName().substring(3,7);
      int year = Integer.parseInt(yearName);
      int rank = getRank(year, name, gender);
      if (highestRank == -1 && rank != -1) {
        highestRank = rank;
        yearOfHighestRank = year;
      }
      if (highestRank > rank && rank != -1) {
        highestRank = rank;
        yearOfHighestRank = year;
      }
    }
    return yearOfHighestRank;
  }

  public double getAverageRank (String name, String gender) {
    double average = -1.0;
    int total = 0;
    double count = 0.0;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      count++;
      String year = f.getName().substring(3,7);
      int rank = getRank(Integer.parseInt(year), name, gender);
      total = (rank == -1) ? total : total + rank;
    }
    average = (total == 0) ? -1.0 : total / count;
    return average;
  }

  public void rankTester() {
    String name = "Robert";
    String gender = "M";
    int highestRankYear = yearOfHighestRank(name, gender);
    System.out.println("Year of highest rank is " + highestRankYear);
    double averageRank = getAverageRank(name, gender);
    System.out.println("Average rank is " + averageRank);
  }
}