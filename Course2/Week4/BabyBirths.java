/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 *
 * @author Nicholas Stepka
 */

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            //  int numBorn = Integer.parseInt(rec.get(2));
            //    if (numBorn <= 100) {
            System.out.println("Name " + rec.get(0) +
                    " Gender " + rec.get(1) +
                    " Num Born " + rec.get(2));
            //  }
        }
    }

    private int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int rank = 1000000;
        int yearHigh = 0;

        for (File fi : dr.selectedFiles()) {

            String fileName = fi.getName();

            FileResource fr = new FileResource(fi);

            int currRank = -1;
            int pivot = 0;
            for (CSVRecord record : fr.getCSVParser(false)) {
                if (record.get(1).equals(gender)) {
                    pivot++;
                    if (record.get(0).equals(name)) {
                        currRank = pivot;
                        break;
                    }
                }
            }

            if (currRank != -1 && currRank < rank) {
                rank = currRank;
                yearHigh = Integer.parseInt(fileName.replaceAll("[\\D]", ""));
            }
        }

        return yearHigh;
    }

    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double ranktonow = 0;
        int findall = 0;
        for (File f : dr.selectedFiles()) {
            int rank = 0;
            int find = 0;
            String fileName = f.getName();
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord record : parser) {
                if (record.get(1).equals(gender)) {
                    rank += 1;
                    if (record.get(0).equals(name)) {
                        find = 1;
                        break;
                    }
                }
            }
            if (find == 1) {
                findall += 1;
                ranktonow += rank;
            }

        }

        if (findall == 0) return -1;
        else return ranktonow / findall;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String nameyear = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(nameyear);
        CSVParser parser = fr.getCSVParser(false);
        int find = 0;
        int sum = 0;
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                if (record.get(0).equals(name)) {
                    find = 1;
                    break;
                }
                sum += Integer.parseInt(record.get(2));
            }
        }
        if (find == 1) return sum;
        else return -1;
    }

    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public void testYearOfHighestRank() {
        int ranktonow = yearOfHighestRank("Mich", "M");
        System.out.println("Highest rank is " + ranktonow);
    }


    public int getRank(int year, String name, String gender) {

        FileResource fr = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);//false?
        int rank = 0;
        int number = 0;
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                rank++;
                if (record.get(0).equals(name)) {
                    number = 1;
                    break;
                }
            }
        }
        if (number == 1) {
            return rank;
        } else {
            return -1;
        }
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        System.out.println(getName(newYear, rank, gender));
    }


    public String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int counter = 0;
        String returnName = "NO NAME";
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                counter++;
                if (counter == rank) {
                    returnName = record.get(0);
                    break;
                }
            }

        }

        return returnName;
    }

    public void testGetRank() {
        int rank = getRank(1960, "Emily", "F");
        System.out.println(rank);
    }

    public void testGetAverageRank() {
        double average = getAverageRank("Robert", "M");
        System.out.println("Average rank is " + average);
    }

    public void testGetNameF() {
        String name = getName(1971, 350, "F");
        System.out.println(name);
    }

    public void testGetNameM() {
        String name = getName(1982, 450, "M");
        System.out.println(name);
    }

    public void testTotalBirths() {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }

    public void testWhatIsNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }

    public void testGetTotalBirthsRankedHigher() {
        int sum = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("The total briths higher is " + sum);
    }

    public static void main(String[] args) {
        BabyBirths bb = new BabyBirths();
        //bb.testTotalBirths();
        //bb.printNames();
        //bb.testGetRank();
        //bb.testGetNameF();
        //bb.testGetNameM();
        //   bb.testWhatIsNameInYear();
        // bb.testYearOfHighestRank();
        //   bb.testGetAverageRank();
        bb.testGetTotalBirthsRankedHigher();
    }
}
