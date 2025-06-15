package Entities;

import ExtraMath.Matrix;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    public Entity owner;

    public static List<Entity> entities = new ArrayList<Entity>();
    public Matrix position;
    public Matrix rotations;
    public Matrix forward;

    public int debugId;
    public static int nextDebugId = 0;

    public Entity(double x, double y, double z, double dirX, double dirY, double dirZ) {
        this.debugId = nextDebugId++;

        position = new Matrix(new double[][]{{x}, {y}, {z}});
        rotations = new Matrix(new double[][]{{dirX}, {dirY}, {dirZ}});

        forward = new Matrix(new double[][]{{0}, {1}, {0}});
        forward
                .rotateX3(dirX, new Matrix(new double[][]{{0}, {0}, {0}}))
                .rotateY3(dirY, new Matrix(new double[][]{{0}, {0}, {0}}))
                .rotateZ3(dirZ, new Matrix(new double[][]{{0}, {0}, {0}}));
    }

    public Entity(Matrix position, Matrix rotations) {
        this.position = position;
        this.rotations = rotations;

        forward = new Matrix(new double[][]{{0}, {1}, {0}});
        forward
                .rotateX3(this.rotations.info[0][0], new Matrix(new double[][]{{0}, {0}, {0}}))
                .rotateY3(this.rotations.info[1][0], new Matrix(new double[][]{{0}, {0}, {0}}))
                .rotateZ3(this.rotations.info[2][0], new Matrix(new double[][]{{0}, {0}, {0}}));
    }

    public void move(double x, double y, double z) {
        Matrix center = new Matrix(new double[][]{{0}, {0}, {0}});
        Matrix rotate = new Matrix(new double[][]{{x}, {y}, {z}});

        position = position.add(rotate)
                .rotateX3(rotations.info[0][0], position)
                .rotateY3(rotations.info[1][0], position)
                .rotateZ3(rotations.info[2][0], position);
    }

    public void rotateX(double angle, Matrix origin) {
        if (!origin.isVector(3)) {
            return;
        }

        this.rotations.info[0][0] += angle;
        this.position = this.position.rotateX3(angle, origin);
        this.forward = this.forward.rotateX3(angle, origin);
    }

    public void rotateY(double angle, Matrix origin) {
        if (!origin.isVector(3)) {
            return;
        }

        this.rotations.info[1][0] += angle;
        this.position = this.position.rotateY3(angle, origin);
        this.forward = this.forward.rotateY3(angle, origin);
    }

    public void rotateZ(double angle, Matrix origin) {
        if (!origin.isVector(3)) {
            return;
        }

        this.rotations.info[2][0] += angle;
        this.position = this.position.rotateZ3(angle, origin);
        this.forward = this.forward.rotateZ3(angle, origin);
    }
}
