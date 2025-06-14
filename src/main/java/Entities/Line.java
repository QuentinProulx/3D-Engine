package Entities;

import ExtraMath.Matrix;

import java.util.ArrayList;
import java.util.List;

public class Line extends Entity {
    public static List<Line> lines = new ArrayList<Line>();

    public Point point1;
    public Point point2;

    public Line(Point point1, Point point2) {
        super(point1.position.subtract(point2.position).multiply(0.5).add(point2.position), new Matrix(new double[][]{{0}, {0}, {0}}));
        this.point1 = point1;
        this.point2 = point2;

        point1.owner = this;
        point2.owner = this;

        lines.add(this);
    }
}
