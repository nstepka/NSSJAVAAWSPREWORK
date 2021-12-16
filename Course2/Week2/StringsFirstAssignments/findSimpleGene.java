import edu.duke.*;
import java.io.*;

/**
 * Write a description of findSimpleGene here.
 * 
 * @author Nicholas Stepka
 * @version 1,0
 */
public class findSimpleGene {
    public String findProteinATG(String dna) {
        int start = dna.toLowerCase().indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop = dna.toLowerCase().indexOf("tag", start + 3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop + 3);
        } else {
            return "";
        }
    }

    //in instruction it should be called part2 but i dont like that name
    //changing method to..
    public String findProteinWithIndex(String dna, int startCondon, int stopCodon) {
        dna = dna.toLowerCase();
        if (startCondon == -1) {
            return "";
        }
        if ((stopCodon - startCondon) % 3 == 0) {
            return dna.substring(startCondon, stopCodon + 3);
        } else {
            return "";
        }
    }

    //not my naming conventions
    public boolean twoOccurrences(String stringa, String stringb) {
        int lenghtA = stringa.length();
        int lengthB = stringb.length();

        int start = stringb.indexOf(stringa);
        if (start == -1) {
            return false;
        }
        int checkTwice = stringb.toLowerCase().indexOf(stringa.toLowerCase(), start
                + stringa.length());
        if (checkTwice != -1) {
            return true;
        }
        return false;
    }

    // Write the method named lastPart that has two String parameters named stringa and stringb.
    // This method finds the first occurrence of stringa in stringb, and returns the part of
    // stringb that follows stringa.  If stringa does not occur in stringb, then return stringb.
    // For example, the call lastPart(“an”, “banana”) returns the string “ana”, the part of the
    // string after the first “an”. The call lastPart(“zoo”, “forest”) returns the string “forest”
    // since “zoo” does not appear in that word.
    public String lastPart(String stringa,String stringb){
        findSimpleGene lastPart = new findSimpleGene();
        int start = stringb.indexOf(stringa);
        if(lastPart.twoOccurrences(stringa,stringb) == true){
            return stringb.substring(start+stringa.length());
        }
       
        return stringb;
    }

    
   
    //Part4
    public void findLinks(String url) {
        URLResource urlResource = new URLResource(url);
        for (String line : urlResource.lines()) {
            int youtubeIndex = line.toLowerCase().indexOf("youtube.com"); // Index where youtube.com starts
            if (youtubeIndex != -1) {
                int startIndex = line.lastIndexOf("\"", youtubeIndex); // Index of '"' just before youtube.com
                int endIndex = line.indexOf("\"", youtubeIndex); // Index of '"' just after youtube.com
                System.out.println("Youtube Link = " + line.substring(startIndex + 1, endIndex));
            }
        }
    }


    public void testingLastPart(){
        System.out.println("Answer should be ana: " +lastPart("an","banana"));
    }

    public void testingtwoOccurrences() {
        String a = "by";
        String ap = "A story by Abby Long";
        if (twoOccurrences(a, ap) == true) {
            System.out.println("There are at least two occurrences");
        } else {
            System.out.println("Negative ghost rider");
        }

    }


    public void testing() {
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ATGCCCTAG";
        // String ap = "ATGCCCTAG";
        String result = findProteinATG(a);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        } else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }

    public void testingFindProteinWithIndex() {
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ATGCCCTAG";
        //String ap = "ATGCCCTAG";

        String result = findProteinWithIndex(a, a.indexOf("atg"), a.indexOf("tag"));
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        } else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }



    public static void main(String[] args) {
        findSimpleGene sg = new findSimpleGene();
        //sg.testing();
        //sg.testingFindProteinWithIndex();
        //sg.testingtwoOccurrences();
        //sg.testingLastPart();
        String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        sg.findLinks(url);
    }
}
