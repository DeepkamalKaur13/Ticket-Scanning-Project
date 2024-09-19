import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class manages anu tab delimited text file, e.g.  codes.txt
 *
 * @author Dave Slemon
 * @version v100
 */
public class Table {

    //instance variables

    //this variable store file name enter by user
    private String tablename;
    //this variable stores all the rows that are present in file
    private int numRows;
    //this variable stores all the columns that are present in file
    private int numCols;
    //grid variable is 2-d array that will store file with rows and columns
    public String[][] grid;


    /**
     * Constructor
     * Initialize the class with the name of the tab delimited text file you wish to manage.
     *
     * @param filename the name of tab delimited text file.
     */
    public Table(String filename) { //table
        tablename = filename;
        numRows = 0;
        numCols = 0;
        String s;
        int r;
        String[] item;


        //Pass1:  Go through the text file in order to ascertain the
        //        numRows and numCols
        try {

            Scanner theFile = new Scanner(new FileInputStream(new File(tablename)));
            while (theFile.hasNextLine()) {
                s = theFile.nextLine();
                item = s.split("\t", 0);


                if (item.length > numCols)
                    numCols = item.length;

                numRows++;

            }
            theFile.close();
        }
        // if try does not work then catch will and which outputs that the file is not found
        catch (FileNotFoundException e) {
            System.out.println("Table class Error 1: file not found.");
        }


        grid = new String[numRows][numCols];


        //Pass2:  populate the grid array
        try {

            Scanner theFile = new Scanner(new FileInputStream(new File(tablename)));
            r = 0;
            while (theFile.hasNextLine()) {
                s = theFile.nextLine();
                item = s.split("\t", 0);

                for (int c = 0; c < numCols; c++) {


                    if (item[c].length() == 0)
                        grid[r][c] = "";
                    else
                        grid[r][c] = item[c];


                }
                r++;


            }
            theFile.close();
        } catch (Exception e) {
            System.out.println("Table class error 2: file not found.");
        }


    } //table

    /**
     * save method saves all the changes in the file
     */
    public void save() {
        try {
            FileWriter writer = new FileWriter(tablename);
            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    writer.write(grid[r][c] + (c < numCols - 1 ? "\t" : ""));
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    /**
     * @return number of rows
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * @return number of columns
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * @param rowNum
     * @param colNum
     * @param newValue this method change the value at a specific cell
     */
    public void change(int rowNum, int colNum, String newValue) {
        if (rowNum >= 0 && rowNum < numRows && colNum >= 0 && colNum < numCols) {
            grid[rowNum][colNum] = newValue;
        }
    }

    /**
     * @param target
     * @param colNum
     * @param newValue It searches for the target value in the specified column, and if found, changes the value at that row and column to the new value.
     */
    public void change(String target, int colNum, String newValue) {
        for (int r = 0; r < numRows; r++) {
            if (grid[r][0].equals(target)) {
                change(r, colNum, newValue);
            }
        }
    }

    /**
     * @throws FileNotFoundException display all the data of the file
     */
    public void display() throws FileNotFoundException {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (c > 0) {
                    System.out.print("\t");
                }
                System.out.print(grid[r][c]);
            }
            System.out.println();
        }
    }

    /**
     * will check that the value enter by user is available in file if yes then display whole row otherwise not
     *
     * @param target
     * @return all column along with its value
     */
    public String[] getMatches(String target) {
        int rowIndex = lookup(target);
        if (rowIndex == -1) {
            return new String[]{"Ticket not found"};
        } else {
            String[] result = new String[numCols];
            for (int c = 0; c < numCols; c++) {
                result[c] = "column " + c + "\t" + "[" + grid[rowIndex][c] + "]";
            }
            return result;
        }
    }

    /**
     * @param target
     * @return integer value of, at which row the value enter by user is present
     */
    public int lookup(String target) {
        for (int r = 0; r < numRows; r++) {
            if (grid[r][0].equals(target)) {
                return r;
            }
        }
        return -1;
    }

    /**
     * @param rowNum
     * @param colNum
     * @return the value of row and col enter by user
     */
    public String getCellValue(int rowNum, int colNum) {
        if (rowNum >= 0 && rowNum < numRows && colNum >= 0 && colNum < numCols) {
            return grid[rowNum][colNum];
        }
        return "Invalid cell choosen";
    }

    /**
     * @param rowNum
     * @param colNum
     * @param newValue Save the value in the column as enter by user but not in the original file while temporary
     */
    public void setCellValue(int rowNum, int colNum, String newValue) {
        if (rowNum >= 0 && rowNum < numRows && colNum >= 0 && colNum < numCols) {
            grid[rowNum][colNum] = newValue;
        }
    }

    /**
     * @return string that represent Table object
     */
    public String toString() {
        return ("Table: " + tablename + "  rows = " + numRows + "  cols = " + numCols);
    }

} //class
    
    
