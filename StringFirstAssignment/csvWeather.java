import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class csvWeather {
  public CSVRecord coldestHourInFile (CSVParser parser) {
    CSVRecord Lowest = null;
    for (CSVRecord record : parser) {
      if (Lowest == null) {
        Lowest = record;
      } else {
        double LowestTemp = Double.parseDouble(Lowest.get("TemperatureF"));
        LowestTemp = (LowestTemp != -9999) ? LowestTemp : 1000.0;
        double currTemp = Double.parseDouble(record.get("TemperatureF"));
        if (currTemp < LowestTemp && currTemp != -9999) {
          Lowest = record;
        }
      }
    }
    return Lowest;
  }

  public void testColdestHourInFile (String filename) {
    FileResource fr = new FileResource(filename);
    CSVParser parser = fr.getCSVParser();
    CSVRecord LowestHour = coldestHourInFile(parser);
    System.out.println("Coldest hour is " + LowestHour.get("TemperatureF") + " at " + LowestHour.get("DateUTC"));
  }

  public String fileWithColdestTemperature () {
    File answer = null;
    CSVRecord lowest = null;
    double lowestTemp = 0.0;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      CSVParser parser = fr.getCSVParser();
      CSVRecord record = coldestHourInFile(parser);
      if (lowest == null) {
        lowest = record;
        lowestTemp = Double.parseDouble(lowest.get("TemperatureF"));
        answer = f;
      } else {
        double currTemp = Double.parseDouble(record.get("TemperatureF"));
        if (currTemp < lowestTemp) {
          lowestTemp = currTemp;
          answer = f;
        }
      }
    }
    return answer.getName();
  }

  public void testFileWithColdestTemperature () {
    String result = fileWithColdestTemperature();
    System.out.println("File with coldest temperature is " + result);
    result = "2014/" + result;
    testColdestHourInFile(result);
  }

  public CSVRecord lowestHumidityInFile (CSVParser parser) {
    CSVRecord lowest = null;
    for (CSVRecord record : parser) {
      if (lowest == null) {
        lowest = record;
      } else {
        double lowestHum = (lowest.get("Humidity") != "N/A") ? Double.parseDouble(lowest.get("Humidity")) : 101.0;
        double currHum = (record.get("Humidity") != "N/A") ? Double.parseDouble(record.get("Humidity")) : 101.0;
        if (currHum < lowestHum) {
          lowest = record;
        }
      }
    }
    return lowest;
  }

  public void testLowestHumidityHourInFile (String filename) {
    FileResource fr = new FileResource(filename);
    CSVParser parser = fr.getCSVParser();
    CSVRecord LowestHour = lowestHumidityInFile(parser);
    System.out.println("Lowest humidity hour is " + LowestHour.get("Humidity") + " at " + LowestHour.get("DateUTC"));
  }

  public CSVRecord lowestHumidityInManyFiles () {
    CSVRecord lowest = null;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      CSVParser parser = fr.getCSVParser();
      CSVRecord record = lowestHumidityInFile(parser);
      if (lowest == null) {
        lowest = record;
      } else {
        double lowestHum = Double.parseDouble(lowest.get("Humidity"));
        double currHum = Double.parseDouble(record.get("Humidity"));
        if (currHum < lowestHum) {
          lowest = record;
        }
      }
    }
    return lowest;
  }

  public void testLowestHumidityInManyFiles () {
    CSVRecord result = lowestHumidityInManyFiles();
    System.out.println("Lowest humidity is " + result.get("Humidity") + " at " + result.get("DateUTC"));
  }

  public double averageTemperatureInFile (CSVParser parser) {
    double total = 0.0;
    int count = 0;
    for (CSVRecord record : parser) {
      total = total + Double.parseDouble(record.get("TemperatureF"));
      count++;
    }
    double average = total/count;
    return average;
  }

  public void testAverageTemperatureInFile (String filename) {
    FileResource fr = new FileResource(filename);
    CSVParser parser = fr.getCSVParser();
    double result = averageTemperatureInFile(parser);
    System.out.println("Average temperature in file " + filename + " is " + result);
  }

  public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
    double total = 0.0;
    int count = 0;
    for (CSVRecord record : parser) {
      if ( Integer.parseInt(record.get("Humidity")) >= value) {
        total = total + Double.parseDouble(record.get("TemperatureF"));
        count++;
      }
    }
    double average = total/count;
    return average;        
  }

  public void testAverageTemperatureWithHighHumidityInFile (String filename) {
    FileResource fr = new FileResource(filename);
    CSVParser parser = fr.getCSVParser();
    double result = averageTemperatureWithHighHumidityInFile(parser, 80);
    System.out.println("Average temperature with humidity 80 or more in file " + filename + " is " + result);
  }
}