package DAO;

import Controller.PostController;
import Model.RecruiterBean;
import Model.StudentInfoBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RecruiterDAOImpl implements RecruiterDAO
{
    private int profileId;
    private String university;
    private String username;
    private String department;
    private String phone;
    private ArrayList posts;

    private ArrayList resultList;
    private Connection DBConn = null;
    private String myDB = "jdbc:derby://10.110.10.26/atapadi_spring2018_LinkedUAppDB";
    private String driver = "org.apache.derby.jdbc.ClientDriver";
    private RecruiterBean targetRecruiter;

    public void connect2DB()
    {
        DBHelper.loadDriver(driver);
        DBConn = DBHelper.connect2DB(myDB, "itkstu","student");
    }
    
    @Override
    public int createRecruiter(RecruiterBean recruiterModel)
    {
        int rowCount = 0;
        resultList = selectRecruiterByUsername(recruiterModel.getUsername());
        
        if(resultList.isEmpty())
        {
            try 
            {
                connect2DB();
                String insertString;
                Statement stmt = DBConn.createStatement();
                insertString = "INSERT INTO itkstu.recruiter "
                + "(university, username, department, phone) "
                + "VALUES ('" + recruiterModel.getUniversity()
                + "', '" + recruiterModel.getUsername()
                + "', '" + recruiterModel.getDepartment()
                + "', '" + recruiterModel.getPhone()
                + "')";
                
                System.out.println("RECRUITERDAOIMPL: " + insertString);
                rowCount = stmt.executeUpdate(insertString);
                DBConn.close();
            } 
            catch (SQLException e) 
            {
                System.err.println(e.getMessage());
            }
        }
        else
        {
            System.err.println("RECRUITERDAOIMPL: Recruiter Profile Already Exists");
        }
        return rowCount;
    }
    
    @Override
    public int updateRecruiter(RecruiterBean recruiterModel)
    {
        int rowCount = 0;
        resultList = selectRecruiterByUsername(recruiterModel.getUsername());
        System.out.println("RECRUITERDAOIMPL: profileId - " + recruiterModel.getProfileId());
        System.out.println("RECRUITERDAOIMPL: university - " + recruiterModel.getUniversity());
        System.out.println("RECRUITERDAOIMPL: username - " + recruiterModel.getDepartment());
        System.out.println("RECRUITERDAOIMPL: phone - " + recruiterModel.getPhone());
        
        if(!resultList.isEmpty())
        {
            try 
            {
                connect2DB();
                String insertString;
                Statement stmt = DBConn.createStatement();
                insertString = "UPDATE itkstu.recruiter "
                        + "SET university = '" + recruiterModel.getUniversity()
                        + "', phone = '" + recruiterModel.getPhone()
                        + "', department = '" + recruiterModel.getDepartment()
                        +"' WHERE username = '" + recruiterModel.getUsername() + "'";
                System.out.println("RECRUITERDAOIMPL: " + insertString);
                rowCount = stmt.executeUpdate(insertString);
                
                DBConn.close();
            } 
            catch (SQLException e) 
            {
                System.err.println(e.getMessage());
            }
        }
        else
        {
            System.err.println("STUDENDAOIMPL: Recruiter does not Exist");
        }
        return rowCount;
    }

    @Override
    public ArrayList selectRecruiterByUsername(String targetUsername)
    {
        PostController postController = new PostController();
        resultList = new ArrayList();
        String selectString = "SELECT * FROM itkstu.recruiter "
            + "WHERE username = '" + targetUsername + "'";
        System.out.println("RECRUITERDAOIMPL: " + selectString);
        try
        {
            connect2DB();
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);

            while(rs.next())
            {
                profileId = rs.getInt("profileId");
                university = rs.getString("university");
                username = rs.getString("username");
                department = rs.getString("department");
                phone = rs.getString("phone");

                targetRecruiter = new RecruiterBean(profileId, university, username, department, phone);
                posts = postController.selectPostsByUsername(targetUsername);
                targetRecruiter.setPosts(posts);
                resultList.add(targetRecruiter);
            }
            DBConn.close();
        }
        catch(Exception e)
        {
            System.err.println("ERROR SELECT RECRUITER BY USERNAME FAILED");
            System.err.println("TARGET: " + targetUsername);
            e.printStackTrace(); 
        }
        return resultList;
    }

    @Override
    public ArrayList DisplayStudentInfo() {
        ArrayList listOfStudent = new ArrayList();  
        StudentInfoBean studentInfoBean;
        String selectString = "select u.firstname, u.lastname, u.email, "
                + "s.school,s.sat,s.act,s.psat from users u join student s on u.username = s.username";
        String Fname, Lname, Email, School, ACT, SAT, PSAT;
        try  {
            connect2DB();
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);

            while(rs.next())
            {
                Fname = rs.getString("firstname");
                Lname = rs.getString("lastname");
                Email = rs.getString("email");
                School = rs.getString("school");
                SAT = rs.getString("sat");
                ACT = rs.getString("act");
                PSAT = rs.getString("psat");

                studentInfoBean = new StudentInfoBean(Fname, Lname, Email, School, SAT, ACT, PSAT);
                listOfStudent.add(studentInfoBean);
            }
            DBConn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        return listOfStudent;
    }
}