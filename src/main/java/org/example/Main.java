package org.example;

import Entities.Block;
import Entities.Line;
import Entities.Point;
import ExtraMath.Matrix;

public class Main {
    static double distance = 1;

    public static Point point1 = new Point(-distance, -0.5, 0.5, 0, 0, 0);
    public static Point point2 = new Point(-distance, -0.5, -0.5, 0, 0, 0);
    public static Line line1 = new Line(point1, point2);

    public static Point point3 = new Point(distance, -0.5, -0.5, 0, 0, 0);
    public static Point point4 = new Point(distance, -0.5, 0.5, 0, 0, 0);
    public static Line line2 = new Line(point3, point4);

    public static Point point5 = new Point(-distance, 0.5, 0.5, 0, 0, 0);
    public static Point point6 = new Point(-distance, 0.5, -0.5, 0, 0, 0);
    public static Line line3 = new Line(point5, point6);

    public static Point point7 = new Point(distance, 0.5, -0.5, 0, 0, 0);
    public static Point point8 = new Point(distance, 0.5, 0.5, 0, 0, 0);
    public static Line line4 = new Line(point7, point8);

    public static Line line5 = new Line(point2, point3);
    public static Line line6 = new Line(point4, point8);
    public static Line line7 = new Line(point1, point4);
    public static Line line8 = new Line(point1, point5);
    public static Line line9 = new Line(point3, point7);
    public static Line line10 = new Line(point5, point8);
    public static Line line11 = new Line(point6, point7);
    public static Line line12 = new Line(point6, point2);

    public static void main(String[] args) {
        Game.start();
    }
}