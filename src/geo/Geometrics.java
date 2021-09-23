package geo;

import java.util.List;

// import geo.Geometrics.Shape3DCollection.CuboidCollection;

// import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class Geometrics {
    // issue: I tried doing stuff like make Rectangle.area(double[] dimensions)  a static method, 
    // but static abstract methods are impossible.

    private abstract static class Shape {

        final double[] dimensions;

        Shape(double[] dimensions) {
            this.dimensions = dimensions;
        }

        abstract String paramsMsg();



    }

    private abstract static class Shape2D extends Shape {

        Shape2D(double[] dimensions) {
            super(dimensions);
        }

        abstract double getArea();
        abstract double getPerimeter();

        String areaMsg() {
            return paramsMsg() + ", area: " + getArea();
        }

        String perimeterMsg() {
            return paramsMsg() + ", perimeter: " + getPerimeter();
        }

    }

    private static class Rectangle extends Shape2D {

        Rectangle(double[] dimensions) {
            super(dimensions);
        }

        double getArea() {
            return (dimensions[0] * dimensions[1]);
        }

        double getPerimeter() {
            return ( 2 * (dimensions[0] + dimensions[1]) );
        }

        String paramsMsg() {
            return "length: " + dimensions[0] + ", width: " + dimensions[1];
        }

    }

    private static class Square extends Shape2D {

        Square(double[] dimensions) {
            super(dimensions);
        }

        double getArea() {
            return Math.pow(dimensions[0], 2);
        }

        double getPerimeter() {
            return (dimensions[0] * 4);
        }

        String paramsMsg() {
            return "length: " + dimensions[0];
        }

    }

    private abstract static class Shape3D extends Shape {

        Shape3D(double[] dimensions) {
            super(dimensions);
        }

        abstract double getVolume();
        abstract double getSurface();


        String volumeMsg() {
            return paramsMsg() + ", volume: " + getVolume();
        }

        String surfaceMsg() {
            return paramsMsg() + ", perimeter: " + getVolume();
        }

    }

    private static class Cuboid extends Shape3D {

        Cuboid(double[] dimensions) {
            super(dimensions);
        }

        double getVolume() {
            return dimensions[0] * dimensions[1] * dimensions[2];
        }
        
        double getSurface() {
            // backlog: i could just store those as fields internally
            double x = dimensions[0];
            double y = dimensions[1];
            double z = dimensions[2];
            return 2 * (x*y + x*z + y*z);
        }

        String paramsMsg() {
            return "x: " + dimensions[0] + ", y: " + dimensions[1] + ", z: " + dimensions[2];
        }

    }

    private static class Cube extends Shape3D {

        Cube(double[] dimensions) {
            super(dimensions);
        }

        double getVolume() {
            return Math.pow(dimensions[0], 3);
        }
        
        double getSurface() {
            return 6 * Math.pow(dimensions[0], 2);
        }

        String paramsMsg() {
            return "edge length: " + dimensions[0];
        }

    }

    // properly subclassing the other figures would be too complex, so I'll just set up some methods
    private static class ZoneOfASphere {
        static double volume(double x, double y, double z) {
            return (Math.PI * z * (3 * Math.pow(y, 2) + 3 * Math.pow(x, 2) + Math.pow(z, 2))) / 6;
        }
    }

    private static class SphereWithCylinder {
        static double volume(double z) {
            return Math.PI * Math.pow(z, 3) / 6;
        }
    }

    private static class Ungula {
        static double volume(double x, double z) {
            return (2.0 * Math.pow(x, 2) * z) / 3.0;
        }
    }

    private static class PrintTools {

        static void printLines(String[] lines) {
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println();
        }

        static String[] messageWithHeader(String header, String[] msgs) {
            String[] result = new String[msgs.length + 2];
            result[0] = header;
            for (int i = 0; i < msgs.length; i++) {
                result[i+1] = msgs[i];
            }
            result[result.length -1] = "-------------";
            return result;
        }

        static void printWithHeader(String header, String[] msgs) {
            printLines(messageWithHeader(header, msgs));
        }

    }

    // private abstract static class ShapeCollection {

    //     final Shape[] shapes;

    //     ShapeCollection(Shape[] shapes) {
    //         this.shapes = shapes;
    //     }

        

    // }

    private abstract static class Shape2DCollection  {

        final Shape2D[] shapes;

        Shape2DCollection(Shape2D[] shapes) {
            // super(shapes);
            this.shapes = shapes;
        }

        abstract String getShape();

        String[] getAreaMsgs() {
            String[] result = new String[shapes.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = shapes[i].areaMsg();
            }
            return result;
        }

        void printAreas() {
            PrintTools.printWithHeader((getShape() + " areas: "), getAreaMsgs());
        }

        String[] getPerimeterMsgs() {
            String[] result = new String[shapes.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = shapes[i].perimeterMsg();
            }
            return result;
        }

        void printPerimeters() {
            PrintTools.printWithHeader((getShape() + " perimeters: "), getPerimeterMsgs());
        }

        // define abstract static method buildFromRange here?

    }

    private static class RectangleCollection extends Shape2DCollection {

        RectangleCollection(Rectangle[] shapes) {
            super(shapes);
        }

        static RectangleCollection buildFromRange(int limit) {
            int count = (int) Math.pow(limit, 2);
            Rectangle[] shapes = new Rectangle[count];
            for (int i = 0; i < shapes.length; i++) {
                double[] dimensions = new double[] { (i / limit + 1), (i % limit + 1) };
                shapes[i] = new Rectangle(dimensions);
            }
            return new RectangleCollection(shapes);
        }

        String getShape() {
            return "Rectangle";
        }

    }

    private static class SquareCollection extends Shape2DCollection {

        SquareCollection(Square[] shapes) {
            super(shapes);
        }

        static SquareCollection buildFromRange(int limit) {
            Square[] shapes = new Square[limit];
            for (int i = 0; i < shapes.length; i++) {
                double[] dimensions = new double[] { (double) i + 1};
                shapes[i] = new Square(dimensions);
            }
            return new SquareCollection(shapes);
        }

        String getShape() {
            return "Square";
        }

    }

    private abstract static class Shape3DCollection {

        final Shape3D[] shapes;

        Shape3DCollection(Shape3D[] shapes) {
            this.shapes = shapes;
        }

        abstract String getShape();

        String[] getVolumeMsgs() {
            String[] result = new String[shapes.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = shapes[i].volumeMsg();
            }
            return result;
        }

        void printVolumes() {
            PrintTools.printWithHeader((getShape() + " volumes: "), getVolumeMsgs());
        }

        String[] getSurfaceMsgs() {
            String[] result = new String[shapes.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = shapes[i].volumeMsg();
            }
            return result;
        }

        void printSurfaces() {
            PrintTools.printWithHeader((getShape() + " surfaces: "), getSurfaceMsgs());
        }

    }

    private static class CuboidCollection extends Shape3DCollection {

        CuboidCollection(Cuboid[] shapes) {
            super(shapes);
        }

        static CuboidCollection buildFromRange(int limit) {
            int count = (int) Math.pow(limit, 3);
            Cuboid[] shapes = new Cuboid[count];
            for (int i = 0; i < shapes.length; i++) {
                double[] dimensions = new double[] {
                    (i / limit) / limit + 1,
                    (i / limit) % limit + 1,
                    (i % limit) + 1 // or (i % limit) % limit + 1
                };
                shapes[i] = new Cuboid(dimensions);
            }
            return new CuboidCollection(shapes);
        }

        String getShape() {
            return "Cuboid";
        }

    }

    private static class CubeCollection extends Shape3DCollection {

        CubeCollection(Cube[] shapes) {
            super(shapes);
        }

        static CubeCollection buildFromRange(int limit) {
            Cube[] shapes = new Cube[limit];
            for (int i = 0; i < shapes.length; i++) {
                double[] dimensions = new double[] { i };
                shapes[i] = new Cube(dimensions);
            }
            return new CubeCollection(shapes);
        }

        String getShape() {
            return "Cube";
        }

    }


    public static void main(String[] args) {
        
        RectangleCollection myRectangles = RectangleCollection.buildFromRange(4);
        myRectangles.printPerimeters();
        myRectangles.printAreas();

        SquareCollection mySquares = SquareCollection.buildFromRange(8);
        mySquares.printPerimeters();
        mySquares.printAreas();


        CuboidCollection myCuboids = CuboidCollection.buildFromRange(2);
        myCuboids.printSurfaces();
        myCuboids.printVolumes();


        CubeCollection myCubes = CubeCollection.buildFromRange(8);
        myCubes.printSurfaces();
        myCubes.printVolumes();

    }
    
}
