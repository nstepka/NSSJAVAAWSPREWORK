import edu.duke.*;
import java.io.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (Nicholas Stepka
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
            }
        }
        return -1;
    }

    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        while (true) {
            String currentGene = findGene(dna, startIndex);

            if (currentGene.isEmpty()) {
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();

        }
        return geneList;
    }

    public void testOnStorageResourse() {

        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        System.out.println("TestinggetalleGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        System.out.println(genes.size());
        for (String g : genes.data()) {
            System.out.println(g);
        }


    }
    
    public double cgRatio(String dna){
        double dnaLength = dna.length();
        dna = dna.toLowerCase();
        String gCopy = dna;
        double cGount = dna.length() - dna.replace("c", "").length();
        double gGount = gCopy.length() - gCopy.replace("g", "").length();
        return (cGount+gGount)/dnaLength;
    }
    
    public void processGenes(StorageResource  sr) {
     int count1 = 0;
     int count2 = 0;
        int counting = 0;
        System.out.println("Printing genes with +60 charas:");
        for (String gene60 : sr.data()){
            if (gene60.length() > 60){
                System.out.println(gene60);
            }
        }
        for (String plus9 : sr.data()){
          if (plus9.length() > 60){
              count1 = count1 +1;
            }
        }
        System.out.println("Printing number of strings above: " + count1);
        for (String cgRat : sr.data()){
           cgRatio(cgRat);
            if (cgRatio(cgRat) > 0.35){
               System.out.println("Gene with C-G ratio higher than 0.35 = " + cgRat + " and the ratio is: "+ cgRatio(cgRat));
               count2 = count2 +1;
            }
        }
        System.out.println("Genes with cgRatio greater than 0.35: " +count2);
        for (String longest : sr.data()){
            if (longest.length() > counting){
                counting = longest.length();
            }
        }
        System.out.println("Length of the longest gene= " + counting);
        
    }
    
    public StorageResource  fileReader(){
       
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        System.out.println(dna);
        StorageResource sr = new StorageResource();
        int startIndex = 0;
        while (true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;

            }
            sr.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + 
                         currentGene.length();
           }
           int count = 0;
           for (String gene9 : sr.data()){
           
                count = count + 1;
            
        }
        System.out.println("this file has this genes: " + count);
        
        return sr;
       // FileResource fr = new FileResource("brca1line.fa");
       // String genes = fr.asString();
       // genes = genes.toUpperCase();
       // System.out.println(genes.length());
       // findGene(genes);
       //  System.out.println("testing2");
       // return fr.asString();
        
       
    }
    
    public void testProcessGenes(){
          //StorageResource genes = new StorageResource();
          processGenes(fileReader());
    //    System.out.println("Greater than 60 nucleotides is ");
    //    for (String gene : genes.data()){
    //        System.out.println(gene);
    //        System.out.println("Greaasgasgasdgsadgcleotides is ");
    //    }
        
    }
    
    
    public void testcgRatio(){
                String dna = "ATGCCATAG";
        System.out.println("testing C/G Ratio on dna " + dna);
        System.out.println(cgRatio(dna));
        
        
    }
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public int howMany(String stringa, String stringb) {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = stringb.indexOf(stringa, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += stringa.length();
            }
        }
        return count;
    }

    public void testHowMany() {

        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));

    }


    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }

            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }


    public int countGenes(String dna) {
        int startIndex = 0;
        int count = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                return count;
            }
            count++;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }

    public void testCountGenes() {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));

    }

    public void testOn(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }

    public void testPrintAllGenes() {
        //      ATGv  TAAv  ATG   v  V  TGA
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGCCCGGGAAATAGCCC");

    }

    public void testFindStop() {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) {
            System.out.println("error on 9");
        }
        System.out.println("test finished");
    }

    public void testFindGene() {
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna);
        if (!gene.equals("ATGCCCGGGAAATAA")) {
            System.out.println("Error");
        }
        System.out.println("test finished");
    }
}
