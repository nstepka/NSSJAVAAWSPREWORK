
/**
 * Write a description of AllCodons here.
 * 
 * @author Nicholas Stepka
 * @version 1.0
 */
public class AllCodons {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1){
         int diff = currIndex - startIndex;
            if(diff % 3 == 0){
                return currIndex;
            }else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return -1;
    }
    
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna, startIndex,"TAG");
        int tgaIndex = findStopCodon(dna, startIndex,"TGA");
        int minIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }else{
            minIndex = taaIndex;
        }
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        if(minIndex == -1){
            return "";
        }
        return dna.substring(startIndex,minIndex+3);
    }
    
    public int howMany(String stringa, String stringb){
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
    
     public void testHowMany(){
         
          System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
          System.out.println(howMany("AA", "ATAAAA"));
          
        }
    
    
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna, startIndex,"TAG");
        int tgaIndex = findStopCodon(dna, startIndex,"TGA");
        int minIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }else{
            minIndex = taaIndex;
        }
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        if(minIndex == -1){
            return "";
        }
        return dna.substring(startIndex,minIndex+3);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
        
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    
        public int countGenes (String dna) {
        int startIndex = 0;
        int count = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                return count;
            }
            count++;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public void testCountGenes(){
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
        
    }
    public void testOn(String dna){
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
        int dex = findStopCodon(dna,0,"TAA");
        if(dex!=9){
        System.out.println("error on 9");
        }
        System.out.println("test finished");
    }
    
    public void testFindGene() {
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna);
        if(!gene.equals("ATGCCCGGGAAATAA")){
            System.out.println("Error");
        }
        System.out.println("test finished");
    }
}
