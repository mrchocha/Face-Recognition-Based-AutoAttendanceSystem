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
public class MaxID {
    public int max() {
        int maxy = 0;
        try {
            ConnectDatabase connect = new ConnectDatabase();
            connect.connect();
            connect.executeSQL("SELECT * FROM student ORDER BY id DESC");
            connect.rs.first();
            maxy = connect.rs.getInt("id");
            System.out.println(maxy);
//            return max;
        } catch (SQLException ex) {
            Logger.getLogger(MaxID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maxy;
    }
}
