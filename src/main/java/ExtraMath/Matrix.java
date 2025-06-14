package ExtraMath;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    public int rows, cols;
    public double[][] info;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        info = new double[rows][cols];
    }

    public Matrix(double[][] info) {
        this.rows = info.length;
        this.cols = info[0].length;
        this.info = info;
    }

    public Matrix(Matrix matrix) {
        rows = matrix.rows;
        cols = matrix.cols;
        info = matrix.info;
    }

    /**
     * Inserts a value into a specific row and column of the Matrix
     * @param row the row to be inserted into
     * @param col the column to be inserted into
     * @param val the value to be inserted into the specified location
     */
    public void insertVal(int row, int col, double val) {
        info[row][col] = val;
    }

    /**
     * Adds this Matrix to another Matrix
     * @param matrix the Matrix for this one to be added to
     * @return the addition Matrix
     */
    public Matrix add(Matrix matrix) {
        if (matrix == null) {
            System.out.println("Addition Matrix is null");
            return null;
        }
        if (matrix.rows != this.rows || matrix.cols != this.cols) {
            System.out.println("Addition Matrix does not have the same number of rows or columns");
            return null;
        }

        Matrix newMatrix = new Matrix(matrix.rows, matrix.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                newMatrix.insertVal(i, j, this.info[i][j] + matrix.info[i][j]);
            }
        }

        return newMatrix;
    }

    /**
     * Subtracts another Matrix from this Matrix
     * @param matrix the Matrix for this one to be subtracted from
     * @return the subtraction Matrix
     */
    public Matrix subtract(Matrix matrix) {
        if (matrix == null) {
            System.out.println("Subtraction Matrix is null");
            return null;
        }
        if (matrix.rows != this.rows || matrix.cols != this.cols) {
            System.out.println("Subtraction Matrix does not have the same number of rows or columns");
            return null;
        }

        Matrix newMatrix = new Matrix(matrix.rows, matrix.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                newMatrix.insertVal(i, j, this.info[i][j] - matrix.info[i][j]);
            }
        }

        return newMatrix;
    }

    /**
     * Multiplies this Matrix by a constant
     * @param num the constant that this Matrix is to be multiplied by
     * @return the multiplied Matrix
     */
    public Matrix multiply(double num) {
        Matrix newMatrix = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.info[i][j] == 0) {
                    continue;
                }
                newMatrix.insertVal(i, j, info[i][j] * num);
            }
        }

        return newMatrix;
    }

    /**
     * Multiplies this Matrix with another Matrix
     * @param matrix the Matrix for this one to be multiplied to
     * @return the multiplied Matrix
     */
    public Matrix multiply(Matrix matrix) {
        if (matrix == null) {
            System.out.println("Multiplication Matrix is null");
            return null;
        }
        if (this.cols != matrix.rows) {
            System.out.println("Multiplication Matrix can not be multiplied by this one");
            return null;
        }

        Matrix newMatrix = new Matrix(this.rows, matrix.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                double value = 0;
                for (int k = 0; k < matrix.rows; k++) {
                    value += this.info[i][k] * matrix.info[k][j];
                }
                newMatrix.insertVal(i, j, value);
            }
        }

        return newMatrix;
    }

    /**
     * Transposes this Matrix
     * @return the transposed Matrix
     */
    public Matrix transpose() {
        Matrix newMatrix = new Matrix(this.cols, this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                newMatrix.insertVal(j, i, this.info[i][j]);
            }
        }

        return newMatrix;
    }

    /**
     * Gets the magnitude of this Matrix IFF the Matrix is a vector (of any dimension)
     * @return the magnitude of this Matrix (vector)
     */
    public double magnitude() { // VECTORS ONLY
        if (!this.isVector()) {
            System.out.println("This Matrix is not a vector; therefore magnitude cannot be calculated");
            return Double.NaN;
        }

        double sum = 0;
        for (int i = 0; i < Math.max(this.rows, this.cols); i++) {
            sum += ((this.rows >= this.cols) ? Math.pow(this.info[i][0], 2) : Math.pow(this.info[0][i], 2));
        }

        return Math.sqrt(sum);
    }

    /**
     * Projects this Matrix onto another Matrix
     * @param matrix the Matrix that this Matrix will be projected onto
     * @return the projection vector
     */
    public Matrix project(Matrix matrix) {
        if (!matrix.isVector(3) || !this.isVector(3)) {
            System.out.println("One of the two matrices is not a vector and therefore projection cannot be calculated");
            return null;
        }

        if (this.rows == matrix.rows || this.cols == matrix.cols) {
            matrix = matrix.transpose();
        }
        if (this.rows != matrix.cols) {
            System.out.println("This projection cannot be completed as the dimensions of each Matrix are different");
        }

        return matrix.multiply(matrix.multiply(this).info[0][0] / Math.pow(matrix.magnitude(), 2));
    }

    // !!! ROTATION FUNCTIONS!!!

//    public Matrix rotateX3(double angle) {
//        if (!this.isVector(3)) {
//            System.out.println("This Matrix is not a vector and therefore rotate cannot be calculated");
//            return null;
//        }
//
//        angle = Math.toRadians(angle);
//        Matrix rotationMatrix = new Matrix(new double[][]{{1, 0, 0}, {0, Math.cos(angle), -Math.sin(angle)}, {0, Math.sin(angle), Math.cos(angle)}});
//
//        return (this.rows == 1) ? this.multiply(rotationMatrix) : this.transpose().multiply(rotationMatrix).transpose();
//    }

    /**
     * Rotates the x angle of this vector
     * @param angle the angle it is to be rotated by
     * @param origin the point it is to be rotated around
     * @return the rotated vector
     */
    public Matrix rotateX3(double angle, Matrix origin) {
        if (!this.isVector(3)) {
            System.out.println("This Matrix is not a vector and therefore rotate cannot be calculated");
            return null;
        }

        angle = Math.toRadians(angle);
        Matrix rotationMatrix = new Matrix(new double[][]{{1, 0, 0}, {0, Math.cos(angle), -Math.sin(angle)}, {0, Math.sin(angle), Math.cos(angle)}});

        Matrix newMatrix = this;
        if (this.rows > 1) {
            newMatrix = newMatrix.transpose();
        }
        if (origin.rows > 1) {
            origin = origin.transpose();
        }

        newMatrix = newMatrix.subtract(origin);
        newMatrix = newMatrix.multiply(rotationMatrix);
        newMatrix = newMatrix.add(origin);

        return newMatrix.transpose();
    }

//    public Matrix rotateY3(double angle) {
//        if (!this.isVector(3)) {
//            System.out.println("This Matrix is not a vector and therefore rotate cannot be calculated");
//            return null;
//        }
//
//        angle = Math.toRadians(angle);
//        Matrix rotationMatrix = new Matrix(new double[][]{{Math.cos(angle), 0, Math.sin(angle)}, {0, 1, 0}, {-Math.sin(angle), 0, Math.cos(angle)}});
//
//        return (this.rows == 1) ? this.multiply(rotationMatrix) : this.transpose().multiply(rotationMatrix).transpose();
//    }

    /**
     * Rotates the y angle of this vector
     * @param angle the angle it is to be rotated by
     * @param origin the point it is to be rotated around
     * @return the rotated vector
     */
    public Matrix rotateY3(double angle, Matrix origin) {
        if (!this.isVector(3)) {
            System.out.println("This Matrix is not a vector and therefore rotate cannot be calculated");
            return null;
        }

        angle = Math.toRadians(angle);
        Matrix rotationMatrix = new Matrix(new double[][]{{Math.cos(angle), 0, Math.sin(angle)}, {0, 1, 0}, {-Math.sin(angle), 0, Math.cos(angle)}});

        Matrix newMatrix = this;
        if (this.rows > 1) {
            newMatrix = newMatrix.transpose();
        }
        if (origin.rows > 1) {
            origin = origin.transpose();
        }

        newMatrix = newMatrix.subtract(origin);
        newMatrix = newMatrix.multiply(rotationMatrix);
        newMatrix = newMatrix.add(origin);

        return newMatrix.transpose();
    }

//    public Matrix rotateZ3(double angle) {
//        if (!this.isVector(3)) {
//            System.out.println("This Matrix is not a vector and therefore rotate cannot be calculated");
//            return null;
//        }
//
//        angle = Math.toRadians(angle);
//        Matrix rotationMatrix = new Matrix(new double[][]{{Math.cos(angle), -Math.sin(angle), 0}, {Math.sin(angle), Math.cos(angle), 0}, {0, 0, 1}});
//
//        return (this.rows == 1) ? this.multiply(rotationMatrix) : this.transpose().multiply(rotationMatrix).transpose();
//    }

    /**
     * Rotates the z angle of this vector
     * @param angle the angle it is to be rotated by
     * @param origin the point it is to be rotated around
     * @return the rotated vector
     */
    public Matrix rotateZ3(double angle, Matrix origin) {
        if (!this.isVector(3)) {
            System.out.println("This Matrix is not a vector and therefore rotate cannot be calculated");
            return null;
        }

        angle = Math.toRadians(angle);
        Matrix rotationMatrix = new Matrix(new double[][]{{Math.cos(angle), -Math.sin(angle), 0}, {Math.sin(angle), Math.cos(angle), 0}, {0, 0, 1}});

        Matrix newMatrix = this;
        if (this.rows > 1) {
            newMatrix = newMatrix.transpose();
        }
        if (origin.rows > 1) {
            origin = origin.transpose();
        }

        newMatrix = newMatrix.subtract(origin);
        newMatrix = newMatrix.multiply(rotationMatrix);
        newMatrix = newMatrix.add(origin);

        return newMatrix.transpose();
    }

    /**
     * Rounds every point of the Matrix
     * @return the rounded Matrix
     */
    public Matrix roundPoints() {
        Matrix newMatrix = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                newMatrix.info[i][j] = Math.round(this.info[i][j]);
            }
        }

        return newMatrix;
    }

    /**
     * Determines whether this Matrix is a vector or not
     * @param dimensions the dimensions that the vector should be
     * @return whether the Matrix is a vector of the specified dimensions
     */
    public boolean isVector(int dimensions) {
        return (this.rows == 1 && this.cols == dimensions) || (this.cols == 1 && this.rows == dimensions);
    }

    public boolean isVector() {
        return this.rows == 1 || this.cols == 1;
    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < this.rows; i++) {
            String subStr = "[";
            for (int j = 0; j < this.cols; j++) {
                subStr += info[i][j] + ((j == this.cols - 1) ? "]" : ", ");
            }
            str += subStr + "\n";
        }

        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return Objects.deepEquals(info, matrix.info);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(info);
    }
}
