package DAO;

import Model.StudentBean;
import Model.UniversityBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UniversityDAOImpl implements UniversityDAO
{
    private ArrayList resultList;
    private Connection DBConn = null;
    private String myDB = "jdbc:derby://10.110.10.26/atapadi_spring2018_LinkedUAppDB";
    private String driver = "org.apache.derby.jdbc.ClientDriver";
    private UniversityBean targetUniversity;
    private String profileId;
    private String universityEmail;
    private String username;
    private ArrayList recruiters;

    public void connect2DB()
    {
        DBHelper.loadDriver(driver);
        DBConn = DBHelper.connect2DB(myDB, "itkstu","student");
    }  

    @Override
    public ArrayList selectUniversityByUsername(String targetUsername)
    {
        resultList = new ArrayList();
        String selectString = "SELECT * FROM itkstu.university "
            + "WHERE username = '" + targetUsername + "'";
        System.out.println("UNIVERSITYDAOIMPL: " + selectString);
        try 
        {
            connect2DB();
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);
            
            while(rs.next())
            {
                profileId = rs.getString("profileId");
                universityEmail = rs.getString("universityEmail");
                username = rs.getString("username");

                targetUniversity = new UniversityBean(profileId, universityEmail, username);
                resultList.add(targetUniversity);
            }
            DBConn.close();
        } 
        catch (Exception e) 
        {
            System.err.println("ERROR: SELECT UNIVERSITY BY USERNAME FAILED.");
            System.err.println("TARGET: " + targetUsername);
            e.printStackTrace();
        }
        return resultList;
    }
    
    @Override
    public ArrayList selectUniversityByProfileId(String targetProfileId)
    {
        resultList = new ArrayList();
        String selectString = "SELECT * FROM itkstu.university "
            + "WHERE profileId = '" + targetProfileId + "'";
        System.out.println("UNIVERSITYDAOIMPL: " + selectString);

        try 
        {
            connect2DB();
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);
            
            while(rs.next())
            {
                profileId = rs.getString("profileId");
                universityEmail = rs.getString("universityEmail");
                username = rs.getString("username");

                targetUniversity = new UniversityBean(profileId, universityEmail, username);
                resultList.add(targetUniversity);
            }
            DBConn.close();
        } 
        catch (Exception e) 
        {
            System.err.println("ERROR: SELECT UNIVERSITY BY PROFILEID FAILED.");
            System.err.println("TARGET: " + targetProfileId);
            e.printStackTrace();
        }
        return resultList;
    }
    
    @Override
    public String applyForAdmission(StudentBean studentBean){
         int rowCount = 0;
         String email = "", fname = "", school = "", act = "", psat = "", sat = "";
        String query = "SELECT FIRSTNAME, EMAIL FROM ITKSTU.USERS WHERE USERNAME = '"
                + studentBean.getUsername() + "'";
        
        String query1 = "SELECT SCHOOL, ACT, SAT, PSAT FROM ITKSTU.STUDENT WHERE USERNAME = '"
                + studentBean.getUsername() + "'";
        try {
            connect2DB();
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                fname = rs.getString("FIRSTNAME");
                email = rs.getString("EMAIL");
            }
            ResultSet rs1 = stmt.executeQuery(query1);
            while(rs1.next()){
                school = rs1.getString("SCHOOL");
                act = rs1.getString("ACT");
                sat = rs1.getString("SAT");
                psat = rs1.getString("PSAT");
            }
            String to = email;

                // Sender's email ID needs to be mentioned
                String from = "atapadi@ilstu.edu";
                String password = "Akki_aman+3363-";

                // Assuming you are sending email from this host
                String host = "outlook.office365.com";

                // Get system properties
                Properties properties = System.getProperties();

                // Setup mail server
                properties.setProperty("mail.smtp.host", host);
                properties.setProperty("mail.smtp.starttls.enable", "true");
                properties.setProperty("mail.smtp.auth", "true");
                properties.setProperty("mail.smtp.port", "587");

                // Get the default Session object.
                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

                try {
                    session.setDebug(true);
                    Transport transport = session.getTransport();
                    // Create a default MimeMessage object.
                    MimeMessage message = new MimeMessage(session);
                    message.setSubject("LinkedU");
                    // Set From: header field of the header.
                    message.setFrom(new InternetAddress(from));

                    // Set To: header field of the header.
                    message.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(to));

                    // Set Subject: header field
                    message.setSubject("Successfully applied for admission");

                    // String str= aProfile.getFirst_name();
                    // Send the actual HTML message, as big as you like
                    message.setContent(
                     "<h3>Hello " + fname  +  "!</h3><br/>"
                             + "<p>You have successfully applied for admission with following details:<br/>"
                             + "ACT SCORE: " + act
                             + "<br/>SAT SCORE: " + sat
                             + "<br/>PSAT SCORE: " + psat
                             + "<br/>HIGH SCHOOL: " + school
                             + "</p>", "text/html");

                    transport.connect();
                    transport.sendMessage(message,
                            message.getRecipients(Message.RecipientType.TO));
                    transport.close();

                    System.out.println("Sent message successfully....");
                    rowCount = rowCount + 1;
                } catch (MessagingException mex) {
                    mex.printStackTrace();
                    System.out.println(mex);
                }
            DBConn.close();
        }
         catch (Exception ex) {
            ex.printStackTrace();
        }
    return "successfull";
    }
    
}
