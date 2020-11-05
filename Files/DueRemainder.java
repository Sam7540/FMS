package com.system.fms;

import java.util.*;
import java.sql.*;
import javax.mail.internet.*;
/*
 * @author Soumyajit
 */
class DueRemainder {
    public static void send(Connection con) throws Exception {
        Scanner sc = new Scanner(System.in);
        int no;
        String temp;
        String[] string = null;
        InternetAddress[] recipients = null;
        ArrayList<String> list = new ArrayList<>();
        while(true) {
            System.out.println("1. Automatically send due fees remainder");
            System.out.println("2. Manually send fees remainder to selected recipients");
            System.out.print("3. Go back to the main menu\nEnter your choice: ");
            switch(sc.nextInt()) {
                case 1:
                    
                    break;
                case 2:
                    Tool.clearScreen();
                    System.out.println("Enter all the Recipients: ");
                    sc.nextLine();
                    while(true) {
                        temp = sc.nextLine();
                        if(temp.isEmpty()) break;
                        list.add(temp);
                    }
                    string = new String[list.size()];
                    list.toArray(string);
                    recipients = new InternetAddress[string.length];
                    for(int i=0; i<string.length; i++) recipients[i] = new InternetAddress(string[i]);
                    Mail mail = new Mail();
                    mail.send(recipients, 1);
                    
                    System.out.println("Mail has been selected to selected Recipients\n\nPress enter to continue....");
                    sc.nextLine();
                    break;
                case 3:
                    MainWindow.main();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            
        }
    }
}
