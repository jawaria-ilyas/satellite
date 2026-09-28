package satellite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Satellite {


    static int[][] oldImage;
    static int[][] newImage;
    static int noOfRows, noOfCols;


    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("C:/Users/jawar/Downloads/text3.txt"));
        readSize(file);
        oldImage = readImage(file);
        newImage = readImage(file);
        file.close();
        printResult();
    }


    static void readSize(Scanner file) {
        noOfRows = file.nextInt();
        noOfCols = file.nextInt();
    }


    static int[][] readImage(Scanner file) {
        int[][] image = new int[noOfRows][noOfCols];
        for (int row = 0; row < noOfRows; row++)
            for (int col = 0; col < noOfCols; col++)
                image[row][col] = file.nextInt();
        return image;
    }


    static boolean equalRows(int row) {
        for (int col = 0; col < noOfCols; col++)
            if (oldImage[row][col] != newImage[row][col]) return false;
        return true;
    }

    static boolean equalCols(int col) {
        for (int row = 0; row < noOfRows; row++)
            if (oldImage[row][col] != newImage[row][col]) return false;
        return true;
    }


    static int lineCount(boolean lookAtRows) {
        if (lookAtRows) return noOfRows;
        return noOfCols;
    }

    static boolean sameLine(int i, boolean lookAtRows) {
        if (lookAtRows) return equalRows(i);
        return equalCols(i);
    }


    static int findEdge(int start, int step, boolean lookAtRows) {
        int i = start;
        while (i >= 0 && i < lineCount(lookAtRows) && sameLine(i, lookAtRows))
            i += step;
        return i;
    }


    static void printResult() {
        int x1 = findEdge(0, 1, true);
        int x2 = findEdge(noOfRows - 1, -1, true);
        int y1 = findEdge(0, 1, false);
        int y2 = findEdge(noOfCols - 1, -1, false);
        printRectangle(x1, y1, x2, y2);
    }

    static void printRectangle(int x1, int y1, int x2, int y2) {
        if (x1 > x2 || y1 > y2)
            System.out.println("The two images are the same");
        else
            System.out.println((x1 + 1) + " " + (y1 + 1) + " " + (x2 + 1) + " " + (y2 + 1));
    }
}


