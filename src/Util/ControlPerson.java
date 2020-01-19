
package Util;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ControlPerson {
    ConnectDatabase connect  = new ConnectDatabase();
    public void insert(ModelPerson mod) {
        try {
            connect.connect();
            PreparedStatement pst = connect.con.prepareStatement("INSERT INTO student(id, first_name, last_name, dob, office, ContactNum, Email, Year, Branch)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setInt(1, mod.getId());
            pst.setString(2, mod.getFirst_name());
            pst.setString(3, mod.getLast_name());
            pst.setString(4, mod.getDob());
            pst.setString(5, mod.getOffice());
            pst.setLong(6, mod.getContactNum());
            pst.setString(7, mod.getEmail()+"");
            pst.setInt(8, mod.getYear());
            pst.setString(9, mod.getBranch()+"");
            pst.executeUpdate();
            System.out.println(mod.getFirst_name() + " has Data Registered.");
            connect.disconnect();
        } catch (SQLException e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
        }
    }
}
