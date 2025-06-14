package Entities;

import java.util.ArrayList;
import java.util.List;

public class Point extends Entity {
    public static List<Point> points = new ArrayList<Point>();

    public Point(int x, int y, int z) {
        super(x, y, z, 0, 0, 0);
    }

    public Point(double x, double y, double z, double dirX, double dirY, double dirZ) {
        super(x, y, z, dirX, dirY, dirZ);
        points.add(this);
    }
}
