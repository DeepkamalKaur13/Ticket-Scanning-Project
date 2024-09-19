
import java.util.Scanner;


/**
 * Write a description of class Preferences here.
 * This class provides a menu for testing the Table class, allowing users to display data, look up tickets,
 * search for specific tickets, change ticket values, save data, and get or set single cell values. It
 * interacts with the Table class to ensure its methods work correctly.
 *
 * @author (Deepkamal kaur)
 * @version (a version number or a date)
 */
public class TableTestProgram {
    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        /** tablename stores filename**/
        String tablename;
        /** this variable will store user choice**/
        String choice;
        /** store row enter by user**/
        int row;
        /**Store column enter by user**/
        int colNum;
        /**it is used to identify and look up a row in the table.**/
        String key;
        /** help in changing the original value to new value enter by user choice**/
        String newValue;


        System.out.print("Enter the name of the tab delimited text file you wish to manage (e.g. codes.txt) > ");
        tablename = in.nextLine();
        Table t = new Table(tablename);
        System.out.println("Successfully loaded: " + t);


        while (1 == 1) {
            System.out.println("\n\nTable Testing Menu\n");
            System.out.println("1. Display all data");
            System.out.println("2. Lookup");
            System.out.println("3. Search");
            System.out.println("4. Change");
            System.out.println("5. Save data to " + tablename);
            System.out.println("6. Get Single Cell Value");
            System.out.println("7. Save Single Cell Value");
            System.out.println("8. Quit");
            System.out.print("Select > ");
            choice = in.nextLine();


            if (choice.equals("8")) break;

            else if (choice.equals("1")) {
                t.display();
            } else if (choice.equals("2")) {
                System.out.print("Enter a key > ");
                key = in.nextLine();
                row = t.lookup(key);

                if (row == -1) {
                    System.out.println(key + " can not be found");
                } else {
                    System.out.println(key + " is found at row:" + row);
                }
            } else if (choice.equals("3")) {
                System.out.print("Enter a key > ");
                key = in.nextLine();
                String[] result = t.getMatches(key);    // find the user input value in the file
                if (result.length == 1 && result[0].equals("Ticket not found")) {
                    System.out.println(result[0]);
                } else {
                    System.out.println("Ticket details:");
                    for (String s : result) {
                        System.out.println(s);
                    }
                }
            } else if (choice.equals("4")) {
                System.out.print("Enter a key > ");
                key = in.nextLine();
                System.out.print("Enter column number to change > ");
                colNum = Integer.parseInt(in.nextLine());
                System.out.print("Enter new value > ");
                newValue = in.nextLine();
                t.change(key, colNum, newValue);    // change the value of column number enter by user into new one
            } else if (choice.equals("5")) {
                t.save();
            } else if (choice.equals("6")) {
                System.out.print("Enter row number > ");
                int rowNum = Integer.parseInt(in.nextLine());
                System.out.print("Enter column number > ");
                colNum = Integer.parseInt(in.nextLine());
                System.out.println("Value at row " + rowNum + " and col " + colNum + " is: " + t.getCellValue(rowNum, colNum));
            } else if (choice.equals("7")) {
                System.out.print("Enter row number > ");
                int rowNum = Integer.parseInt(in.nextLine());
                System.out.print("Enter column number > ");
                colNum = Integer.parseInt(in.nextLine());
                System.out.print("Enter new value > ");
                newValue = in.nextLine();
                t.setCellValue(rowNum, colNum, newValue);       //set the value as per user demand but not in original file just for short period
            }
        }
        System.out.println("Thank-you, good bye!");

    }
}



