

/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 *
 * @author Duke Software Team
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }

    public String countryInfo(CSVParser parser, String country) {
        String returnString = "";
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Country");
            if (export.contains(country)) {
                returnString = record.get("Country") + ":" + record.get("Exports") + record.get("Value (dollars)");

            }


        }
        if (returnString.isEmpty()) {
            return "Not Found";
        }
        return returnString;
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int countryCounter = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                countryCounter++;
            }
        }
        return countryCounter;
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void bigExporters(CSVParser parser, String dollar) {
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Value (dollars)");
            if (export.length() > dollar.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }

        }
    }

    public void bigExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }


    public void testCountryInfo() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));

    }

    public void testlistExportersTwoProducts() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "fish", "nuts");

    }


    public void testnumberOfExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));
    }


    public static void main(String[] args) {
        WhichCountriesExport export = new WhichCountriesExport();
        //export.testCountryInfo();
          export.bigExporters();

        //export.testnumberOfExporters();
        //export.testlistExportersTwoProducts();
        //export.whoExportsCoffee();
    }
}
