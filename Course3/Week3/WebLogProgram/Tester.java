
/**
 * Write a description of class Tester here.
 *
 * @author Nick Stepka
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "examp le request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("short-test_log");
        lg.printAll();
    }

    public void testUniqIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);

    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String,Integer> counts= la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(counts));
    }


    public void testUniqueIPOnDay() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("weblog1_log");
        System.out.println(lg.uniqueIPVisitsOnDay("Mar 17").size());
        lg.readFile("weblog1_log");
        System.out.println("should be ...");
        System.out.println(lg.countUniqueIPsInRange(300, 399));
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);

    }

    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
       HashMap<String,Integer> counts= la.countVisitsPerIP();
        System.out.println(la.iPsMostVisits(counts));
    }
    
    public void testIPsForDays() {
        LogAnalyzer read = new LogAnalyzer();
        read.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> daysIPs = read.iPsForDays();
        System.out.println(daysIPs);
    }
      
    public void testDayWithMostIPVisits(){
    LogAnalyzer la = new LogAnalyzer();     
    la.readFile("weblog1_log");
    HashMap<String,ArrayList<String>> counts=la.iPsForDays();
    //System.out.println(counts);
    System.out.println(la.dayWithMostIPVisits(counts));    
    }
    
    public void testIPsWithMostVisitsOnDay() {
    LogAnalyzer la = new LogAnalyzer();     
    la.readFile("weblog3-short_log");
    HashMap<String,ArrayList<String>> counts=la.iPsForDays();
    System.out.println(la.IPsWithMostVisitsOnDay(counts,"Sep 30"));
    }
    
}
