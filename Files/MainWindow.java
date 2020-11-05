package com.system.fms;

import java.sql.*;
import java.util.Scanner;
/*
 * @author Soumyajit
 */
class Tool {
    public static void pause(Scanner sc) {
        sc.nextLine();
        sc.nextLine();
    }
    public static void clearScreen() throws Exception { 
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
        pb.inheritIO().start().waitFor();
    }
}
public class MainWindow {
    public static void main(String... args) {
        Connection con = null;
        Scanner sc = new Scanner(System.in);
        try {
            while(true) {
                Tool.clearScreen();
                String s = "str";
                s.concat("str");
                System.out.println("*****FEE MANAGEMENT SYSTEM*****");
                System.out.println("1. View Student Details");
                System.out.println("2. View Student's Payment Status");
                System.out.println("3. Accept Payment");
                System.out.println("4. Generate Receipt");
                System.out.println("5. Send Due Fees Remainder");
                System.out.println("6. About this System");
                System.out.print("7. Help\n8. Exit\nEnter your choice: ");
            
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url = "jdbc:mysql://localhost:3306/fms";
                String username = "root";
                String password = "admin56";

                con = DriverManager.getConnection(url, username, password);
                switch(sc.nextInt()) {
                    case 1: 
                        Tool.clearScreen();
                        FetchDetails.student(con);
                        System.out.print("Press any key to go back to the main menu....");
                        Tool.pause(sc);
                        break;
                    case 2:
                        PaymentStatus.getStatus(con);
                        break;
                    case 3:
                        Tool.clearScreen();
                        Payment.process(con);
                        System.out.print("Press any key to go back to the main menu....");
                        Tool.pause(sc);
                        break;
                    case 4:
                        Tool.clearScreen();
                        System.out.print("Enter Enrollment no. of Student: ");
                        sc.nextLine();
                        String id = sc.nextLine();
                        Statement stmt = con.createStatement();
                        String query = "select firstname,lastname,branch from student where enroll_id = "+id;
                        ResultSet set = stmt.executeQuery(query);
                        while(set.next()) { PdfHandler.makePdf(set.getString(2)+set.getString(1), set.getString(3), id); }
                        System.out.println("Receipt has been generated at specified location");
                        System.out.print("\nPress any key to go back to the main menu....");
                        sc.nextLine();
                        break;
                    case 5:
                        DueRemainder.send(con);
                        Tool.pause(sc);
                        break;
                    case 6:
                        Tool.clearScreen();
                        System.out.println("FEE MANAGEMENT SYSTEM\n\nDeveloped and Programmed by Soumyajit Dutta\nThis System is under Development");
                        System.out.print("\nPress any key to go back to the main menu....");
                        Tool.pause(sc);
                        break;
                    case 7:
                        Tool.clearScreen();
                        System.out.println("If you are facing any issues or want to give any suggestions. Kindly contact the Developer.\nEmail at get-help@fms.dev.in");
                        System.out.print("\nPress any key to go back to the main menu....");
                        Tool.pause(sc);
                        break;
                    case 8:
                        Tool.clearScreen();
                        System.out.println("Thankyou for using this System");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }   
            }
        }
        catch(Exception e) { e.printStackTrace(); }
    }
}
