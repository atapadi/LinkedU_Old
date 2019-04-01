/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controller.RecruiterController;
import Controller.StudentController;
import Model.RecruiterBean;
import Model.StudentBean;
import Model.UserBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO
{
    private ArrayList resultList;
    private Connection DBConn = null;
    private String myDB = "jdbc:derby://10.110.10.26/atapadi_spring2018_LinkedUAppDB";
    private String driver = "org.apache.derby.jdbc.ClientDriver";
    private UserBean targetUser;
    private StudentBean targetStudent;
    private RecruiterBean targetRecruiter;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String secQuestion;
    private String secAnswer;
    private String userType;
    private int profilepictureid;
    
    public void connect2DB()
    {
        DBHelper.loadDriver(driver);
        DBConn = DBHelper.connect2DB(myDB, "itkstu","student");
    }
    
    @Override
    public int createUser(UserBean userModel)
    {
        int rowCount = 0;
        resultList = selectUserByUsername(userModel.getUsername());
        
        if(resultList.isEmpty())
        {
            try
            {
                connect2DB();
                String insertString;
                //String insertStudentTbl;
                Statement stmt = DBConn.createStatement();
                insertString = "INSERT INTO itkstu.users "
                    + "(username, password, firstname, lastname, email, securityquestion, securityanswer, usertype, profilepictureid, emailsubscription) "
                    + "VALUES ('" + userModel.getUsername()
                    + "', '" + userModel.getPassword()
                    + "', '" + userModel.getFirstName()
                    + "', '" + userModel.getLastName()
                    + "', '" + userModel.getEmail()
                    + "', '" + userModel.getSecurityQuestion()
                    + "', '" + userModel.getSecurityAnswer()
                    + "', '" + userModel.getUserType()
                    + "', " + userModel.getProfilePictureID()
                    + ", '1')";
                System.out.println("USERDAOIMPL: " + insertString);
                rowCount = stmt.executeUpdate(insertString); 
                DBConn.close();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
        else
        {
            //ArrayList returned a user therefore User Already Exist
            //Returning rowCount = 0 to the controlle
            //Handle conflict there.
            System.err.println("USERDAOIMPL: User Already Exists");
        }
        
        return rowCount;
    }
    
    @Override
    public int updateEmailDB(StudentBean studentBean)
    {
        int rowCount = 0;
     try {
                connect2DB();
                String insertString;
                Statement stmt = DBConn.createStatement();
                insertString = "UPDATE ITKSTU.USERS SET EMAILSUBSCRIPTION = '0'"
                        + " WHERE username = '" + studentBean.getUsername() + "'";
                rowCount = stmt.executeUpdate(insertString);
                DBConn.close();
            } 
            catch (SQLException e) 
            {
                System.err.println(e.getMessage());
            }
            return rowCount;
        }
         
    @Override
    public int updateUser(UserBean userModel)
    {
        int rowCount = 0;
        resultList = selectUserByUsername(userModel.getUsername());
        System.out.println("USERDAOIMPL: Update Username - " + userModel.getUsername());
        
        if(!resultList.isEmpty())
        {
            try
            {
                connect2DB();
                String insertString;
                Statement stmt = DBConn.createStatement();
                insertString = "UPDATE itkstu.users "
                    + "SET username = '" + userModel.getUsername()
                    + "', password = '" + userModel.getPassword()
                    + "', firstname = '" + userModel.getFirstName()
                    + "', lastname = '" + userModel.getLastName()
                    + "', email = '" + userModel.getEmail()
                    + "', securityquestion = '" + userModel.getSecurityQuestion()
                    + "', securityanswer = '" + userModel.getSecurityAnswer()
                    + "', usertype = '" + userModel.getUserType()
                    + "', profilepictureid = " + userModel.getProfilePictureID()
                    + " WHERE username = '" + userModel.getUsername() + "'";
                
                rowCount = stmt.executeUpdate(insertString);              
                DBConn.close();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
        else
        {
            //ArrayList returned a user therefore User Already Exist
            //Returning rowCount = 0 to the controlle
            //Handle conflict there.
            System.err.println("USERDAOIMPL: User Doesnt Exist");
        }
        
        return rowCount;
    }
    
    public int setProfilePictureId(int imageId, String username)
    {
        int rowCount = 0;
        
        try
        {
            connect2DB();
            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "UPDATE itkstu.users "
                + "SET profilepictureid = " + imageId
                + " WHERE username = '" + username + "'";

            rowCount = stmt.executeUpdate(insertString);              
            DBConn.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
               
        return rowCount;
    }
    public int setEmailId(UserBean userModel)
    {
        int rowCount = 0;
        resultList = selectUserByUsername(userModel.getUsername());
        System.out.println("USERDAOIMPL: Update Username - " + userModel.getUsername());
        
        if(!resultList.isEmpty())
        {
            try
            {
                connect2DB();
                String insertString;
                Statement stmt = DBConn.createStatement();
                insertString = "UPDATE itkstu.users "
                    + "SET emailId = '" + userModel.getEmailId()
                    + "' WHERE username = '" + userModel.getUsername() + "'";
                
                rowCount = stmt.executeUpdate(insertString);              
                DBConn.close();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
        else
        {
            //ArrayList returned a user therefore User Already Exist
            //Returning rowCount = 0 to the controlle
            //Handle conflict there.
            System.err.println("USERDAOIMPL: User Doesnt Exist");
        }
        
        return rowCount;
    }
    
    @Override
    public ArrayList selectUserByUsername(String targetUsername)
    {   
        StudentController studentController = new StudentController();
        RecruiterController recruiterController = new RecruiterController();
        
        resultList = new ArrayList();
        String selectString = "SELECT * FROM itkstu.users "
                + "WHERE username = '" + targetUsername + "'";
        System.out.println("USERDAOIMPL: " + selectString);
        
        try
        {
            connect2DB();
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);
            
            while(rs.next())
            {
                username = rs.getString("username");
                password = rs.getString("password");
                firstName = rs.getString("firstname");
                lastName = rs.getString("lastname");
                email = rs.getString("email");
                secQuestion = rs.getString("securityquestion");
                secAnswer = rs.getString("securityanswer");
                userType = rs.getString("userType");
                profilepictureid= rs.getInt("profilepictureid");
                
                targetUser = new UserBean(username, password, firstName, lastName, email, secQuestion, secAnswer, userType, profilepictureid);
                
                if(userType.equalsIgnoreCase("student"))
                {
                    targetStudent = (StudentBean)studentController.selectStudentByUsername(targetUsername).get(0);
                    targetUser.setTargetStudent(targetStudent);
                }
                
                if(userType.equalsIgnoreCase("recruiter"))
                {
                    targetRecruiter = (RecruiterBean)recruiterController.selectRecruiterByUsername(targetUsername).get(0);
                    targetUser.setTargetRecruiter(targetRecruiter);
                }
                
                resultList.add(targetUser);
            }
            DBConn.close();
        }
        catch(Exception e)
        {
            System.err.println("ERROR: SELECT USER BY USERNAME FAILED.");
            System.err.println("TARGET: " + targetUsername);
            e.printStackTrace();
        }
        return resultList;
    }
    
    @Override
    public ArrayList selectUserByUsertype(String targetUserType)
    {   
        resultList = new ArrayList();
        String selectString = "SELECT * FROM itkstu.users "
                + "WHERE usertype = '" + targetUserType + "'";
        System.out.println("USERDAOIMPL: " + selectString);
        
        try
        {
            connect2DB();
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);
            
            while(rs.next())
            {
                username = rs.getString("username");
                password = rs.getString("password");
                firstName = rs.getString("firstname");
                lastName = rs.getString("lastname");
                email = rs.getString("email");
                secQuestion = rs.getString("securityquestion");
                secAnswer = rs.getString("securityanswer");
                userType = rs.getString("userType");
                targetUser = new UserBean(username, password, firstName, lastName, email, secQuestion, secAnswer, userType);
                resultList.add(targetUser);
            }
            DBConn.close();
        }
        catch(Exception e)
        {
            System.err.println("ERROR: SELECT USER BY USERTYPE FAILED.");
            System.err.println("TARGET:" + targetUserType);
            e.printStackTrace();
        }
        return resultList;
    }   
    
      @Override
       public ArrayList DisplayStudentInfo() {
        ArrayList listOfStudent = new ArrayList();    
        String selectString = "select * from itkstu.users where usertype = 'Student' ";
        try  {
            connect2DB();
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);

            while(rs.next())
            {
                username = rs.getString("username");
                firstName = rs.getString("firstname");
                lastName = rs.getString("lastname");
                email = rs.getString("email");
                
                targetUser = new UserBean(username, firstName, lastName, email);
                listOfStudent.add(targetUser);
            }
            DBConn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        return listOfStudent;
    }

    @Override
    public int SubscribeEmail(StudentBean studentBean) {
         int rowCount = 0;
        try {
                connect2DB();
                String insertString;
                Statement stmt = DBConn.createStatement();
                insertString = "UPDATE ITKSTU.USERS SET EMAILSUBSCRIPTION = '1'"
                        + " WHERE username = '" + studentBean.getUsername() + "'";
                rowCount = stmt.executeUpdate(insertString);
                DBConn.close();
            } 
            catch (SQLException e) 
            {
                System.err.println(e.getMessage());
            }
            return rowCount;
    }
}
