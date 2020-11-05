package com.system.fms;

import java.sql.*;
import java.util.*;
import javax.mail.internet.*;
/*
 * @author Soumyajit
 */
class Payment {
    public static void process(Connection con) throws Exception{
        String id ,name = null, branch = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Enrollment no.: ");
        id = sc.nextLine();
        if(id.length() == 12) {
            ResultSet set = null;
            String query = "select firstname,lastname,branch,crrsem from student where enroll_id = "+id;
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            set = stmt.executeQuery(query);
            while(set.next()) {
                name = set.getString(2) + " " + set.getString(1);
                branch = set.getString(3);
                System.out.println("Full Name: "+name);
                System.out.println("Branch: "+branch+" Current Sem.: "+set.getInt(4));
            }
            set.first();
            System.out.print("Set Fee Status as Paid for Sem: "+set.getInt(4)+" ? (Y-yes, N-no): ");
            char status = sc.nextLine().charAt(0);
            
            int crrsem = 0;
            if(Character.toLowerCase(status) == 'y') {
                set.first();
                crrsem = set.getInt(4);
                query = "update feepay set sem"+crrsem+" = 'paid' where enroll_id = "+id;
                stmt.executeUpdate(query);
            }
            
            query = "select email from student where enroll_id = "+id;
            set = stmt.executeQuery(query);
            InternetAddress[] address = new InternetAddress[1];
            while(set.next()) { address[0] = new InternetAddress(set.getString(1)+"@gmail.com"); }

            PdfHandler.makePdf(name, branch, id);
            Mail mail = new Mail();
            mail.send(address, 0);
            System.out.println("Fees of id = "+id+" for sem : "+crrsem+" is paid Successfully!");
        }
        else System.out.println("Error: Syntax of your entered Enrollment no. is wrong");
    }
}
