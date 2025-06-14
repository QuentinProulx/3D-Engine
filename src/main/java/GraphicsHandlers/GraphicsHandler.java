package GraphicsHandlers;

import Entities.Line;
import Entities.Point;
import ExtraMath.Matrix;
import org.example.Game;

import java.awt.*;
import java.util.Iterator;

public class GraphicsHandler {
    // TODO: Make it so that this displays 3D objects in 2D

    private static final double FOV = 1;

    public static void renderPoints(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);

        Iterator<Point> iterator = Point.points.iterator();
        while (iterator.hasNext()) {
            Point point = iterator.next();
            if (point.hashCode() == Game.camera.hashCode() || point.owner != null) {
                continue;
            }

            double[] pos;
            pos = renderPoint(point);

            if (pos == null) {
                continue;
            }

            g2d.drawLine((int) pos[0], (int) pos[1], (int) pos[0], (int) pos[1]);
        }
    }

    public static void renderLines(Graphics2D g2d) {
        g2d.setColor(Color.RED);

        Iterator<Line> iterator = Line.lines.iterator();
        while (iterator.hasNext()) {
            Line line = iterator.next();

            double[] pos1 = renderPoint(line.point1);
            double[] pos2 = renderPoint(line.point2);

            if (pos1 == null || pos2 == null) {
                continue;
            }

            g2d.drawLine((int) pos1[0], (int) pos1[1], (int) pos2[0], (int) pos2[1]);
        }
    }

    public static double[] renderPoint(Point point) {
        Point camera = Game.camera;

        int posX; // Where to display the point on the screen (x-value)
        int posY; // Where to display the point on the screen (y-value)

        Matrix worldPosition = point.position;
        Matrix relative = worldPosition.subtract(camera.position);

        // Rotate relative to camera orientation
        Matrix rotated = relative
                .rotateX3(-camera.rotations.info[0][0], new Matrix(new double[][]{{0}, {0}, {0}}))
                .rotateY3(-camera.rotations.info[1][0], new Matrix(new double[][]{{0}, {0}, {0}}))
                .rotateZ3(-camera.rotations.info[2][0], new Matrix(new double[][]{{0}, {0}, {0}}));

        if (rotated.info[1][0] < 0) {
            return null;
        }

        // Perspective projection
        double scale = 250; // adjust this to zoom in/out
        posX = (int) ((rotated.info[0][0] / rotated.info[1][0]) * scale + 250);
        posY = (int) ((rotated.info[2][0] / rotated.info[1][0]) * scale + 250);

        return new double[]{posX, posY};
    }
}
