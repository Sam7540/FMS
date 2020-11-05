package com.system.fms;

import java.sql.*;
import java.util.*;
/*
 * @author Soumyajit
 */
class FetchDetails {
    public static void student(Connection con) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Enrollment no. of Student: ");
        String id = sc.nextLine();
        Statement stmt = con.createStatement();
        String query = "select * from student where enroll_id = "+id;
        ResultSet set = stmt.executeQuery(query);
        while(set.next()) {
            System.out.println("Enrollment no.: "+id);
            System.out.println("Full Name: "+set.getString(3)+" "+set.getString(2));
            System.out.println("E-mail: "+set.getString(4)+"@gmail.com    Branch: "+set.getString(5)+"-"+set.getString(6));
            System.out.println("Current Semester: "+set.getInt(7)+"\nMobile no.: "+set.getString(8));
        }
    }
}
