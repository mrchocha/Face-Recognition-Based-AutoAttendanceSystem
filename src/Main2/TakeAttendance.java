/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main2;

import Util.ConnectDatabase;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rahul
 */
public class TakeAttendance {

    ConnectDatabase connect = new ConnectDatabase();
//    int n = new MaxID().max();
//    int[] count = new int[n+1];
    public void take(String faculty_id, int idPerson, String lectureNo, String courseId) {
        connect.connect();
              if (lectureNo.equals("Lecture1")) {
            int count = 0;
            System.out.println("In take");
            System.out.println(faculty_id + "          " + courseId + "       " + idPerson + "     " + lectureNo);
            String query = "INSERT INTO student_attendance(faculty_id, course_id, id, " + lectureNo + ") VALUES('" + faculty_id + "', '" + courseId + "', " + idPerson + ", 1);";
//            count[idPerson]++;
//            if(count[idPerson] == 1){
                connect.execute(query);
//            }
        } else {
            try {
                int count = 0;
                System.out.println("In take");
                System.out.println(faculty_id + "          " + courseId + "       " + idPerson + "     " + lectureNo);
                ConnectDatabase connect1 = new ConnectDatabase();
                connect1.connect();
                connect1.executeSQL("SELECT * FROM student_attendance");
                connect1.rs.first();
                if(connect1.rs.getInt("id") == idPerson){
                    String query = "UPDATE `student_attendance` SET `faculty_id` = '" + faculty_id + "', `course_id` = '" + courseId + "', `" + lectureNo + "` = 1 WHERE `id` = "+idPerson+";";
                    connect.execute(query);
                    count++;
                }
                while(connect1.rs.next()){
                if(connect1.rs.getInt("id") == idPerson){
                    String query = "UPDATE `student_attendance` SET `faculty_id` = '" + faculty_id + "', `course_id` = '" + courseId + "', `" + lectureNo + "` = 1 WHERE `id` = "+idPerson+";";
                    connect.execute(query);
                    count++;
                   }
                }
                if(count == 0){
                    String query = "INSERT INTO student_attendance(faculty_id, course_id, id, " + lectureNo + ") VALUES('" + faculty_id + "', '" + courseId + "', " + idPerson + ", 1);";
                    connect.execute(query);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(TakeAttendance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        connect.disconnect();
    }

}
