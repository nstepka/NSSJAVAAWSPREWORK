
/**
 * Write a description of class LogAnalyzer here.
 *
 * @author Nicholas Stepka
 * @version 2.0
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;

     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }

     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
        for (String line : fr.lines()){
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
     }

     public int countUniqueIPs(){
         HashMap<String,Integer> counts = countVisitsPerIP();
         return counts.size();
     }

    public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (LogEntry lg : records){
            String ip =lg.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }
            else{
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
    }


      public ArrayList<Integer> printAllHigherThanNum(int num){
        ArrayList<Integer> unique=new ArrayList<Integer>();
        for (LogEntry lg : records){
            if (!unique.contains(lg.getStatusCode())){
                unique.add(lg.getStatusCode());
            }
        }

        for (int i:unique){
            if (i>=num){
                    System.out.println(i);
            }
        }
        return unique;
     }

      public ArrayList<String> uniqueIPVisitsOnDay(String someday){

         ArrayList<String> ipsOneDay = new ArrayList<String>();

         for (LogEntry le : records) {
             String leTime = le.getAccessTime().toString();
             String leIP = le.getIpAddress();
             if(leTime.indexOf(someday)!=-1)
                 if (!ipsOneDay.contains(leIP)) ipsOneDay.add(leIP);
         }

         return ipsOneDay;

     }

     public Integer countUniqueIPsInRange(Integer low,Integer high){
        ArrayList<String> stat= new ArrayList<String>();
        for (LogEntry lg:records){
            if((lg.getStatusCode()>=low) && (lg.getStatusCode()<=high)){
                if (!stat.contains(lg.getIpAddress())){
                    stat.add(lg.getIpAddress());
                }
            }
        }
        return stat.size();
     }

    public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
        Integer most=0;
        for (Integer v : counts.values()){
            if(most <= v){
                most = v;
            }
        }
        return most;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
         Integer max = mostNumberVisitsByIP(counts);
        ArrayList<String> mostVisit=new ArrayList<String>();
        for (String s: counts.keySet()){
            if (counts.get(s)==max){
                mostVisit.add(s);
            }
        }
        return mostVisit;

    }

    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> counts = new HashMap<String,ArrayList<String>>();
        for(LogEntry le : records){
            String date = le.getAccessTime().toString().substring(4,10);
            if(!counts.containsKey(date)){
                ArrayList<String> ip = new ArrayList<String>();
                ip.add(le.getIpAddress());
                counts.put(date,ip);
            }
            else{
                ArrayList<String> a = counts.get(date);
                a.add(le.getIpAddress());
                counts.put(date,a);
            }
        }
        return counts;
     }
     
             
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> counts){

        int indexMap = 0;
        
        for (ArrayList s: counts.values()){
            if(indexMap < s.size()){
                indexMap = s.size();
            }
        }
        for(String s: counts.keySet()){
            ArrayList ips = counts.get(s);
            if(indexMap == ips.size()){
                return s;
            }
        }
        return null; 
    } 
    
    public ArrayList<String> IPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> counts, String day){
        System.out.println("The " + day + ", this IPs visited our website: ");
      
        for (String s: counts.keySet()){
            if (s.contains(day)){
                return counts.get(s);
            }
        }
        return null;
    
     
    }
     

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
     }


}
