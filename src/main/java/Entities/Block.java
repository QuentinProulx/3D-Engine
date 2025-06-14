package Entities;

import ExtraMath.Matrix;

import java.util.ArrayList;
import java.util.List;

public class Block extends Entity {
    public static List<Block> blocks = new ArrayList<Block>();
    public Matrix size = new Matrix(3, 1);

    public Block(double x, double y, double z, double xDir, double yDir, double zDir, double width, double height, double length) {
        super(x, y, z, xDir, yDir, zDir);
        size.insertVal(0, 0, width);
        size.insertVal(1, 0, height);
        size.insertVal(2, 0, height);
    }
}
