import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numOfPoints = 0;
        for(Point point : s.getPoints()){
            numOfPoints++;
        }
        return numOfPoints  ;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        double avgLength = 0;
        for(Point currPt : s.getPoints()){
            avgLength = avgLength + prevPt.distance(currPt);
            prevPt = currPt;
        }
        return avgLength/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        double largestSide = 0;
        for(Point currPt : s.getPoints()){
            if(largestSide < prevPt.distance(currPt)){
                largestSide = prevPt.distance(currPt);
            }
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        double largestX = 0;
        for(Point currPt : s.getPoints()){
            if(largestX < currPt.getX()){
                largestX = currPt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        
        for (File f : dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape s = new Shape(file);
            double perim = getPerimeter(s);
            if(perim > largestPerim) {
                largestPerim = perim;
            }
            
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        double bigPerim = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double filePerim = getPerimeter(s);
            if (filePerim > bigPerim) {
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of Points = " + getNumPoints(s));
        System.out.println("Average length of line = " + getAverageLength(s));
        System.out.println("Longest side = " + getLargestSide(s));
        System.out.println("largest X = " + getLargestX(s));
        
        
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        System.out.println("Largest Perimeter multiple files = " + getLargestPerimeterMultipleFiles());
       
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("File with Largest Perimeter = " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
