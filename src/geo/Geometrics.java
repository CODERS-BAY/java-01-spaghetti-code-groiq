package geo;

import java.util.List;

import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class Geometrics {
    private static double x = 10.0;
    private static double y = 5.0;
    private static double z = 3.0;
    private static int x1 = 8;
    private static int y1 = 7;
    private static int z1 = 10;
    private static int x2 = 100;
    private static int y2 = 57;
    private static int z2 = 129;
    private static int x3 = 45;
    private static int y3 = 89;
    private static int z3 = 69;

    public static void main(String[] args) {

        RectangleCollector myColl = RectangleCollector.buildFromRange(4);
        PrintTools.printLines(myColl.getAreas());
        PrintTools.printLines(myColl.getPerimeters());

        SquareCollector mySquares = SquareCollector.buildFromRange(4);
        PrintTools.printLines(mySquares.getAreas());
        PrintTools.printLines(mySquares.getPerimeters());


        System.out.println("We can also calculate some volumes");

        System.out.println("Zone of a sphere");
        System.out.println(ZoneOfASphere.volume(10.0, 5.0, 3.0));

        System.out.println("Sphere with cylinder");
        System.out.println(SphereWithCylinder.volume(3.0));

        System.out.println("Ungula");
        System.out.println(Ungula.volume(45.0, 69.0));
    }


    private class PrintTools {

        static void printLines(String[] lines) {
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println();
        }

        static String[] messageWithHeader(String header, String[] msgs) {
            String[] result = new String[msgs.length + 1];
            result[0] = header;
            for (int i = 0; i < msgs.length; i++) {
                result[i+1] = msgs[i];
            }
            return result;
        }

    }

    private static class RectangleCollector {

        private final int count;
        private final double[] lengths;
        private final double[] widths;

        static RectangleCollector buildFromRange(int limit) {
            double[] lengths = new double[limit * limit];
            double[] widths = new double[limit * limit];

            int k = 0;
            // int l = 0;
            for (int iInt = 0; iInt < limit; iInt++) {
                double i = (double) (iInt+1);
                
                for (int jInt = 0; jInt < limit; jInt++) {
                    double j = (double) (jInt+1);
                    lengths[k] = i;
                    widths[k] = j;
                    k++;
                }
                
            }
            
            return new RectangleCollector(lengths, widths);
        }
        
        RectangleCollector(double[] lengths, double[] widths) {
            this.count = lengths.length;
            if (this.count != widths.length) {
                // backlog: throw a proper exception
                System.out.println("Error: number of lengths and widths must be equal!");
            }
            this.lengths = lengths;
            this.widths = widths;
        }


        String[] getAreas() {
            return PrintTools.messageWithHeader("Rectangle area: ", calculateAreas());
        }

        String[] calculateAreas() {
            String[] result = new String[count];
            for (int i = 0; i < result.length; i++) {
                result[i] = Rectangle.areaMsg(lengths[i], widths[i]);
            }
            return result;
        }

        String[] getPerimeters() {
            return PrintTools.messageWithHeader("Rectangle perimeters: ", calculatePerimeters());
        }

        String[] calculatePerimeters() {
            String[] result = new String[count];
            for (int i = 0; i < result.length; i++) {
                result[i] = Rectangle.perimeterMsg(lengths[i], widths[i]);
            }
            return result;
        }

    }

    private static class SquareCollector {

        private final double[] lengths;

        static SquareCollector buildFromRange(int limit) {
            double[] lengths = new double[limit];
            for (int i = 0; i < lengths.length; i++) {
                lengths[i] = (double) (i + 1);
            }
            return new SquareCollector(lengths);
        }

        SquareCollector(double[] lengths) {
            this.lengths = lengths;
        }

        // backlog: stuff like this could be easily refactored  out to a superclass
        String[] getAreas() {
            return PrintTools.messageWithHeader("Square area: ", calculateAreas());
        }

        String[] calculateAreas() {
            String[] result = new String[lengths.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = Square.areaMsg(lengths[i]);
            }
            return result;
        }

        String[] getPerimeters() {
            return PrintTools.messageWithHeader("Rectangle perimeter: ", calculatePerimeters());
        }

        String[] calculatePerimeters() {
            String[] result = new String[lengths.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = Square.perimterMsg(lengths[i]);
            }
            return result;
        }

    }

    private  class Rectangle {

        static double area(double len, double wid) {
            return len * wid;
        }

        static double perimeter(double len, double wid) {
            return ((len + wid) * 2);
        }


        static String paramsMsg(double len, double wid) {
            return "len: " + len + ", wid: " + wid + ", ";
        }

        static String areaMsg(double len, double wid) {
            return paramsMsg(len, wid) + "area: " + area(len, wid);
        }

        static String perimeterMsg(double len, double wid) {
            return paramsMsg(len, wid) + "perimeter: " + perimeter(len, wid);
        }

    }

    private  class Square extends Rectangle {

        static double area(double len) {
            return area(len, len);
        }

        static double perimeter(double len) {
            return perimeter(len, len);
        }

        static String paramsMsg(double len) {
            return "len: " + len + ", ";
        }

        static String areaMsg(double len) {
            return paramsMsg(len) + "area: " + area(len);
        }

        static String perimterMsg(double len) {
            return paramsMsg(len) + "perimeter: " + perimeter(len);
        }

    }

    private class ZoneOfASphere {
        static double volume(double x, double y, double z) {
            return (Math.PI * z * (3 * Math.pow(y, 2) + 3 * Math.pow(x, 2) + Math.pow(z, 2))) / 6;
        }
    }

    private class SphereWithCylinder {
        static double volume(double z) {
            return Math.PI * Math.pow(z, 3) / 6;
        }
    }

    private class Ungula {
        static double volume(double x, double z) {
            return (2.0 * Math.pow(x, 2) * z) / 3.0;
        }
    }

}
