/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UserBean;
import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Richa
 */
@ManagedBean (name = "passwordResetController")
@SessionScoped
public class PasswordResetController implements Serializable
{

    private UserController userController;
    private UserBean userModel;
    private UserBean targetUser;
    private String SALT = "Project353";
    private UUID uuid;
    private String emailId;
    private String saltedEmailId;
    private String hashedEmailId;

    public PasswordResetController()
    {
        userController = new UserController();
        targetUser = new UserBean();
        userModel = new UserBean();
    }
    
    public String updateUser()
    {
        hashedEmailId = SignupController.generateHash(SALT + targetUser.getEmailId());
        targetUser.setEmailId(hashedEmailId);
        //targetUser.setPassword(SignupController.generateHash(SALT + targetUser.getPassword()));
        System.out.println(targetUser.getPassword());
        userController.updateUser(targetUser); 
        
        return "logIn.xhtml";
    }

    //Returns a UserBean. Saves into targetUser
    public String selectUserByUsername()
    {
        String returnString = "";
        userController.setUserModel(userModel);
        targetUser = userController.selectUserByUsername();

        if (targetUser.getUsername().equalsIgnoreCase(userModel.getUsername()))
        {
            returnString = sendResetEmail();
        } 
        else
        {
            //User not found logic.
            //Give user feedback that username is not registered.
            //After so many attempts to recover password, maybe give user option of signup. 
        }
        
        return returnString;
    }

    public String sendResetEmail()
    {   
        String email = targetUser.getEmail(); 
        String to = email;
        uuid = UUID.randomUUID();
        emailId = uuid.toString();
        saltedEmailId = SALT + emailId;
        hashedEmailId = SignupController.generateHash(saltedEmailId);
        targetUser.setEmailId(hashedEmailId);
        userController.setEmailId(targetUser);

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
        Session session = Session.getInstance(properties, new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(from, password);
            }
        });

        try
        {
            session.setDebug(true);
            Transport transport = session.getTransport();
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            message.setSubject("Reset Password");
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

           
            String htmlText = "<center><H1>Click the link to reset your password"
                    + "<br/> http://gfish3.it.ilstu.edu:8080/atapadi_Spring2018_LinkedU/resetPassword.xhtml?id=" + emailId + "</H1></center>";
           
            // String str= aProfile.getFirst_name();
                    // Send the actual HTML message, as big as you like
            message.setContent(htmlText, "text/html");

          

            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.close();

            System.out.println("Sent message successfully....");
        } 
        
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
        
        return "linkEmailed.xhtml"; // navigate to "echo.xhtml"
    }

    /**
     * @return the userController
     */
    public UserController getUserController()
    {
        return userController;
    }

    /**
     * @param userController the userController to set
     */
    public void setUserController(UserController userController)
    {
        this.userController = userController;
    }

    /**
     * @return the userModel
     */
    public UserBean getUserModel()
    {
        return userModel;
    }

    /**
     * @param userModel the userModel to set
     */
    public void setUserModel(UserBean userModel)
    {
        this.userModel = userModel;
    }

    /**
     * @return the targetUser
     */
    public UserBean getTargetUser()
    {
        return targetUser;
    }

    /**
     * @param targetUser the targetUser to set
     */
    public void setTargetUser(UserBean targetUser)
    {
        this.targetUser = targetUser;
    }

    /**
     * @return the SALT
     */
    public String getSALT()
    {
        return SALT;
    }

    /**
     * @param SALT the SALT to set
     */
    public void setSALT(String SALT)
    {
        this.SALT = SALT;
    }

    /**
     * @return the uuid
     */
    public UUID getUuid()
    {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }

    /**
     * @return the emailId
     */
    public String getEmailId()
    {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
    }

}
