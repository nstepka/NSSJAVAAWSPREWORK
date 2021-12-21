/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 *
 * @author Nick Stepka
 */

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.*;

import static java.lang.Double.NaN;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallestSoFar = getSmallestHumidityOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }


    public double averageTemperatureInFile(CSVParser parser) {
        double tempTotal = 0;
        double divider = 0;
        for (CSVRecord currentRow : parser) {
            double currTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            tempTotal += currTemp;
            divider++;
        }
        return tempTotal / divider;
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord coldestInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());

            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);


        }
        return smallestSoFar;
    }

    public CSVRecord lowestHumidityInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());

            smallestSoFar = getSmallestHumidityOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }


    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        double number = 0;
        double humidity = 0;
        for (CSVRecord record : parser) {
            if (record.get("Humidity").equals("N/A")) {
                humidity = -999;
            } else {
                humidity = Double.parseDouble(record.get("Humidity"));
            }
            if (humidity >= value) {
                number = number + 1;
                sum = sum + Double.parseDouble(record.get("TemperatureF"));
            }
        }

        return sum / number;
    }


    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        } else {
            Double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            Double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp > largestTemp) {
                largestSoFar = currentRow;
            }
        }

        return largestSoFar;
    }

    public CSVRecord getSmallestHumidityOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        } else {
            Double currentHumid;
            Double smallestHumid;
            if (currentRow.get("Humidity").equals("N/A")) {
                return smallestSoFar;
            }else{
                currentHumid = Double.parseDouble(currentRow.get("Humidity"));
                smallestHumid = Double.parseDouble(smallestSoFar.get("Humidity"));
            }
            if (currentHumid < smallestHumid) {
                smallestSoFar = currentRow;
            }

        }
        return smallestSoFar;
    }


    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        } else {
            Double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            Double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));

            if (currentTemp < smallestTemp  && currentTemp != -9999) {
                smallestSoFar = currentRow;
            }
        }

        return smallestSoFar;
    }


    public void testHottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temp was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }

    public void testColdestInManyDays() {
        CSVRecord smallest = coldestInManyDays();
        System.out.println("coldest temp was " + smallest.get("TemperatureF") + " at " + smallest.get("DateUTC"));
    }

    public void testlowestHumidityInManyDays() {
        CSVRecord smallest = lowestHumidityInManyDays();
        System.out.println("lowest humidity was " + smallest.get("Humidity") + " at " + smallest.get("DateUTC"));
    }


    public void testColdestInDay() {
        FileResource fr = new FileResource("data/2014/weather-2014-05-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                " at " + smallest.get("DateUTC"));
    }

    public void testHottestInDay() {
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                " at " + largest.get("DateUTC"));
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        System.out.println(" The average temperature in that file is: "
                + averageTemperatureInFile(fr.getCSVParser()));
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource("data/2014/weather-2014-04-01.csv");
        CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("lowest humidity was " + smallest.get("Humidity") +
                " at " + smallest.get("DateUTC"));
    }


    //currentRow.get("Humidity").equals("N/A")
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        Double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (average.equals(NaN)) {
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.print("Average temperature with high Humidity is ");
            System.out.println(average);
        }
    }

    public static void main(String[] args) {

        CSVMax csv = new CSVMax();
//        csv.testLowestHumidityInFile();
//        csv.testlowestHumidityInManyDays();
//        csv.testAverageTemperatureWithHighHumidityInFile();
//         csv.testAverageTemperatureInFile();
//        csv.testHottestInManyDays();
        csv.testColdestInManyDays();
//       csv.testColdestInDay();
    }
}
