 /** David Woloszczuk-Mrugala
 *   CPSC 24500
 *   03/04/23
 *   This is my attempt to make a Data.txt
 */
 

package a4;

import java.util.Scanner;
import java.util.stream.Stream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Assignment4 {
    public static int getNoLines(String filename) throws Exception {
        try (Stream<String> fileStream = Files.lines(Paths.get(filename))) {
            return (int) fileStream.count();
        }
    }

    public static int[][] create2DArray(String filename) throws Exception {
        int noLines = getNoLines(filename);
        int[][] arr = new int[noLines][];

        Scanner scanner = new Scanner(new File(filename));
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split("\\s+");
            arr[i] = new int[values.length];
            for (int j = 0; j < values.length; j++) {
                arr[i][j] = Integer.parseInt(values[j]);
            }
            i++;
        }
        scanner.close();
        return arr;
    }

    public static int findLongestRow(int[][] arr) {
        int longestRow = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].length > arr[longestRow].length) {
                longestRow = i;
            }
        }
        return longestRow;
    }

    public static int findMaxInRow(int[][] arr, int rowIndex) {
        int max = arr[rowIndex][0];
        for (int i = 1; i < arr[rowIndex].length; i++) {
            if (arr[rowIndex][i] > max) {
                max = arr[rowIndex][i];
            }
        }
        return max;
    }

    public static int findMax(int[][] arr) {
        int max = arr[0][0];
        for (int[] row : arr) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String filename = null;
        if (args.length < 1) {
            System.out.println("usage: Assignment4 inputFilename ");
            System.exit(0);
        }
        filename = args[0];
        int[][] arr = null;
        try {
            System.out.println("Number of lines in the file = " + getNoLines(filename));
            arr = create2DArray(filename);
            int longestRow = findLongestRow(arr);
            System.out.println("Longest row in the file is: " + (longestRow + 1) + " ,with length of: "
                    + arr[longestRow].length);
            System.out.println("Max value in first row = " + findMaxInRow(arr, 0));
            System.out.println("Max value in file = " + findMax(arr));
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
