package geo;

public class Geometrics {
    // issue: I tried doing stuff like make Rectangle.area(double[] dimensions)  a static method, 
    // but static abstract methods are impossible.

    /**
     * The basic class stores a Shape identified by its dimensions.
     */
    private abstract static class Shape {

        final double[] dimensions;

        Shape(double[] dimensions) {
            this.dimensions = dimensions;
        }

        /**
         * formats the dimensions as a String
         * @return the dimensons as a String
         */
        abstract String dimensionsMsg();

    }

    /**
     * A two-dimensional shape has a perimeter and an area.
     */
    private abstract static class Shape2D extends Shape {

        Shape2D(double[] dimensions) {
            super(dimensions);
        }

        /**
         * Calculates the area of a two-dimensional shape.
         * @return the area of the shape
         */
        abstract double getArea();

        /**
         * Calculates the perimeter of a two-dimensional shape.
         * @return the perimeter of the shape
         */
        abstract double getPerimeter();

        /**
         * formats the area as a String. Includes the dimensions.
         * @return the dimensions and area as a String
         */
        String areaMsg() {
            return dimensionsMsg() + ", area: " + getArea();
        }

        /**
         * formats the perimeter as a String. Includes the dimensions.
         * @return the dimensions and perimeter as a String
         */
        String perimeterMsg() {
            return dimensionsMsg() + ", perimeter: " + getPerimeter();
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

        String dimensionsMsg() {
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

        String dimensionsMsg() {
            return "length: " + dimensions[0];
        }

    }

    /**
     * A three-dimensional shape has a surface and a volume.
     */
    private abstract static class Shape3D extends Shape {

        Shape3D(double[] dimensions) {
            super(dimensions);
        }

        /**
         * Calculates the volume of a three-dimensional shape.
         * @return the volume of the shape
         */
        abstract double getVolume();

        /**
         * Calculates the surface of a three-dimensional shape.
         * @return the surface of the shape
         */
        abstract double getSurface();

        /**
         * Formats the volume as a String. Includes the dimensions.
         * @return the dimensions and volume as a String
         */
        String volumeMsg() {
            return dimensionsMsg() + ", volume: " + getVolume();
        }

        /**
         * Formats the surface as a String. Includes the dimensions.
         * @return the dimensions and surface as a String
         */
        String surfaceMsg() {
            return dimensionsMsg() + ", surface: " + getSurface();
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
            // backlog: maybe i could just store stuff like that as fields internally
            double x = dimensions[0];
            double y = dimensions[1];
            double z = dimensions[2];
            return 2 * (x*y + x*z + y*z);
        }

        String dimensionsMsg() {
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

        String dimensionsMsg() {
            return "edge length: " + dimensions[0];
        }

    }

    /**
     * Utilities for batch-printing dimension calculations
     */
    private static class PrintTools {
        /**
         * Takes a header line and a String array. Prints first the header line, then the array line by line and finally a row of dashes.
         * @param header
         * @param msgs
         */
        static void printWithHeader(String header, String[] msgs) {
            System.out.println(header);
            for (String msg : msgs) {
                System.out.println(msg);
            }
            System.out.println("-------------");
        }
    }

    /**
     * Manages a collection of two-dimensional shapes. Provides methods to calculate and print perimeters and areas in batch.
     * 
     * In theory, this and Shape3DCollection would be subclassed of ShapeCollection, but those two don't share too much logic.
     * 
     * Each subclass of ShapeCollection provides a method called buildFromRange() 
     * that generates shapes with dimensions in a range from 1.0 to n.0 (inclusive).
     * 
     * eg. RectangleCollection.buildFromRange(2) generates rectangles with 1.0 * 1.0, 1.0 * 2.0, 2.0 * 1.0 and 2.0 * 2.0.
     */
    private abstract static class Shape2DCollection  {

        final Shape2D[] shapes;

        Shape2DCollection(Shape2D[] shapes) {
            this.shapes = shapes;
        }

        /**
         * Returns the name of the shape.
         * @return the name of the shape
         */
        abstract String getShape();

        String[] getAreaMsgs() {
            String[] result = new String[shapes.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = shapes[i].areaMsg();
            }
            return result;
        }

        /**
         * Prints the areas of the shapes, with a header.
         */
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

        /**
         * prints the perimeters of the shapes, with a header.
         */
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
                double[] dimensions = { (i / limit + 1), (i % limit + 1) };
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
                double[] dimensions = { (double) i + 1};
                shapes[i] = new Square(dimensions);
            }
            return new SquareCollection(shapes);
        }

        String getShape() {
            return "Square";
        }

    }

    /**
     * Manages a collection of three-dimensional shapes. Provides methods to calculate and print surfaces and volumes in batch.
     * 
     * Subclasses provide a buildFromRange() method. For details, see the javadoc for Shape2DCollection.
     */
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
                result[i] = shapes[i].surfaceMsg();
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
                double[] dimensions = {
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
                double[] dimensions = { i + 1 };
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
