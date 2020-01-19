package Main2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Util.ConnectDatabase;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author jeet
 */
public class Sendmail {
        String facultyId, LectureNo, courseId;
        ArrayList list = new ArrayList();
        public void studentList(int idPerson){
            list.add(idPerson);
        }
        public void Id(String facultyId, String LectureNo, String courseId){
            this.LectureNo = LectureNo;
            this.facultyId = facultyId;
            this.courseId = courseId;
        }
        public void sendMail(String facultyId) throws SQLException {
//           String to;
            System.out.println("We are in sendMail method");
        try{
            String to;
           
            ConnectDatabase d= new ConnectDatabase();
             d.connect();
             String query = "SELECT * FROM tb_login WHERE id = '"+this.facultyId+"';";
             d.executeSQL(query);
             d.rs.first();
             to = d.rs.getString("Username");
             System.out.println();
             System.out.println(to);
             
//String msg = "";
            String Sub="attendance of " + LectureNo+" of "+courseId;
            String Main= list.toString() +" were present.";         
                    
         String host="smtp.gmail.com";
         String user="info@AutoattendAncesSystem.com";
         String pass="admin";
         
// String to = "mithilesh.t@ahduni.edu.in";
         String from="r.b.chocha@gmail.com";       
        boolean sessionDebug = false;
        
        Properties props=System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        
        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        
        Session mailSession=Session.getDefaultInstance(props, null);
        mailSession.setDebug(sessionDebug);
        Message msg=new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] address={new InternetAddress(to)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(Sub);
        msg.setSentDate(new Date());
        msg.setText(Main);
        
        Transport  transport =mailSession.getTransport("smtp");
        transport.connect(host, user, pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
            System.out.println(":):):):):)");                   
            System.out.println("Mail Sent!!!");
        }catch(Exception e){
            System.out.println("NO!!!!!!!");
            System.out.println("Errrrr:"+e);
            e.printStackTrace();
        }
    }
    
}
