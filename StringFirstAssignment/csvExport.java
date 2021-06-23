import edu.duke.*;
import org.apache.commons.csv.*;

public class csvExport {
  public void tester () {
    FileResource fr = new FileResource("exportdata.csv");
    CSVParser parser = fr.getCSVParser();
    String countryInfo = countryInfo(parser, "Nauru");
    System.out.println(countryInfo);
    parser = fr.getCSVParser();
    System.out.println("Countries that export both cotton and flowers");
    listExportersTwoProducts(parser, "cotton", "flowers");
    parser = fr.getCSVParser();
    String exportItem = "cocoa";
    int counter = numberOfExporters(parser, exportItem);
    System.out.println("Number of exporters of " + exportItem + " is " + counter);
    parser = fr.getCSVParser();
    bigExporters(parser, "$999,999,999,999");
  }

  public String countryInfo (CSVParser parser, String country) {
    String currCountry = "";
    String goods = "";
    String value = "";
    String result = "";
    for (CSVRecord record : parser) {
      currCountry = record.get("Country");
      if (currCountry.equals(country)) {
        System.out.print(currCountry + ": ");
        goods = record.get("Exports");
        if (goods.isEmpty()) {
          System.out.print("Not Found: ");
        } else {
          System.out.print(goods + ": ");
        }
        value = record.get("Value (dollars)");
        if (value.isEmpty()) {
          System.out.println("Not Found");
        } else {
          System.out.println(value);
        }
        result = currCountry + ": " + goods + ": " + value;
        return result;
      }
    }
    result = "Not found";
    return result;
  }

  public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2) {
    String export = "";
    for (CSVRecord record : parser) {
      export = record.get("Exports");
      if (export.contains(exportItem1) && export.contains(exportItem2)) {
        System.out.println(record.get("Country"));
      }
    }
  }

  public int numberOfExporters (CSVParser parser, String exportItem) {
    int count = 0;
    String exports = "";
    for (CSVRecord record : parser) {
      exports = record.get("Exports");
      if (exports.contains(exportItem)) {
        count++;
      }
    }
    return count;
  }

  public void bigExporters (CSVParser parser, String amount) {
    String value = "";
    for (CSVRecord record : parser) {
      value = record.get("Value (dollars)");
      if (amount.length() < value.length()) {
        System.out.println(record.get("Country") + " " + value);
      }
    }
  }
}