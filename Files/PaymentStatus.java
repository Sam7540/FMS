package com.system.fms;

import java.util.*;
import java.sql.*;
/*
 * @author Soumyajit
 */
class FetchData {
    public static void fetcher(String query, Statement stmt) throws Exception {
        ResultSet set = stmt.executeQuery(query);
        CommandLineTable table = new CommandLineTable();
        table.setShowVerticalLines(true);
        table.setHeaders("Enrollment no.", "Sem-1", "Sem-2", "Sem-3", "Sem-4", "Sem-5", "Sem-6", "Sem-7", "Sem-8");
        while(set.next()) {
            table.addRow(set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5),
            set.getString(6), set.getString(7), set.getString(8), set.getString(9));
        }
        table.print();
    }
}
public class PaymentStatus {
    public static void getStatus(Connection con) throws Exception {
        String query;
        Scanner sc = new Scanner(System.in);
        while(true) {
            Tool.clearScreen();
            System.out.println("1. Search by Enrollment no.");
            System.out.println("2. Search by Branch");
            System.out.println("3. Search All");
            System.out.print("4. Go back to the main menu\nEnter your choice: ");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            switch(sc.nextInt()) {
                case 1:
                    System.out.print("Enter Enrollment no. of Student: ");
                    sc.nextLine();
                    String id = sc.nextLine();
                    query = "select * from feepay where enroll_id = "+id;
                    FetchData.fetcher(query, stmt);
                    System.out.print("\nPress any key to continue....");
                    sc.nextLine();
                    break;
                case 2:
                    System.out.print("Enter Name of branch:");
                    sc.nextLine();
                    String branch = sc.nextLine();
                    query = "select feepay.* from feepay inner join student using(enroll_id) where student.branch = '"+branch+"'";
                    FetchData.fetcher(query, stmt);
                    System.out.print("\nPress any key to continue....");
                    sc.nextLine();
                    break;
                case 3:
                    query = "select feepay.* from feepay inner join student using(enroll_id)";
                    FetchData.fetcher(query, stmt);
                    System.out.print("\nPress any key to continue....");
                    Tool.pause(sc);
                    break;
                case 4:
                    String[] args = null;
                    MainWindow.main(args);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
