package geo;

import java.util.List;
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

        RectangleCollector myColl = RectangleCollector.buildFromRange(3);
        PrintTools.printLines(myColl.getAreas());
        PrintTools.printLines(myColl.getPerimeters());


        // System.out.println("Rectangle area");
        // System.out.println("x * y = " + x * y);
        // System.out.println("x1 * y1 = " + x1 * y1);
        // System.out.println("x2 * y2 = " + x2 * y2);
        // System.out.println("x3 * y3 = " + x3 * y3);

        // System.out.println("Rectangle perimeter");
        // System.out.println("x + y = " + (x + y));
        // System.out.println("x1 + y1 = " + x1 + y1);
        // System.out.println("x2 + y2 = " + x2 + y2);
        // System.out.println("x3 + y3 = " + x3 + y3);

        // System.out.println("We can also calculate some volumes");
        // /*
        //     z is our height
        //     y is our radius
        //  */
        // System.out.println("Zone of a sphere");
        // double v = (Math.PI * z * (3 * Math.pow(y, 2) + 3 * Math.pow(x, 2) + Math.pow(z, 2))) / 6;
        // System.out.println(v);

        // System.out.println("Sphere with cylinder");
        // System.out.println(Math.PI * Math.pow(z, 3) / 6);

        // System.out.println("Ungula");
        // System.out.println((double) (2 * x3 * z3) / 3);
        // System.out.println(Rectangle.area(1.0, 2.0));
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
            return paramsMsg(len, wid) + "perimter: " + perimeter(len, wid);
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

        static String areaMsg(double len, double wid) {
            return paramsMsg(len) + "area: " + area(len, wid);
        }

        static String perimterMsg(double len, double wid) {
            return paramsMsg(len) + "perimter: " + perimeter(len, wid);
        }

    }



}
