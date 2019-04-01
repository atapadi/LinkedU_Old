/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import Model.UserBean;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
//import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Richa
 */
@Named(value = "userController")
@SessionScoped
@ManagedBean
public class UserController implements Serializable
{
    private String USER_TYPE_STUDENT = "student";
    private String USER_TYPE_RECRUITER = "recruiter";
    private String USER_TYPE_UNIVERSITY = "university";
    private String USER_TYPE_ADMIN = "admin";
    
    private UserBean userModel;
    private UserBean targetUser;
    private ArrayList resultList;
          
    public UserController()
    {
        userModel = new UserBean();
    }
    
    public boolean createUser(UserBean userModel)      
    {
        //System.out.println("USERCONTROLLER: createUser()");
        boolean userInserted = false;
        UserDAO userDAO = new UserDAOImpl();
        int rowCount = userDAO.createUser(userModel);
        
        if(rowCount == 1)
        {
            userInserted = true;
        }
        
        return userInserted;
    }
    
    public boolean updateUser(UserBean userModel)
    {
        //System.out.println("USERCONTROLLER: updateUser() - username: " + userModel.getUsername());
        boolean userUpdated = false;
        UserDAO userDAO = new UserDAOImpl();
        int rowCount = userDAO.updateUser(userModel);
        
        if(rowCount == 1)
        {
            userUpdated = true;
        }
        
        return userUpdated;
    }
    public UserBean selectUserByUsername()
    {
        UserDAO userDAO = new UserDAOImpl();
        resultList = userDAO.selectUserByUsername(userModel.getUsername());
        
        if(resultList.size() == 1)
        {
            targetUser = (UserBean)resultList.get(0);
            //System.out.println("USERCONTROLLER: Select By Username Successfull");
            //System.out.println("USERCONTROLLER: Target Username - " + targetUser.getUsername());
        }
        return targetUser;   
    }
    
    public ArrayList selectUserByUsertype()
    {
        UserDAO userDAO = new UserDAOImpl();
        resultList = userDAO.selectUserByUsertype(USER_TYPE_STUDENT);
        for(int i = 0; i < resultList.size(); i++)
        {
            targetUser = (UserBean)resultList.get(i);
            //System.out.println("SELECT BY USERTYPE SUCCESSFULL");
            //System.out.println("TARGET: " + targetUser.getUsername());
        }
        return resultList;
    }
    
    // For Display Student
    public ArrayList StudentInformation()
    {
         UserDAO userDAO = new UserDAOImpl();
         resultList = userDAO.DisplayStudentInfo();
           for(int i = 0; i < resultList.size(); i++)
        {
            targetUser = (UserBean)resultList.get(i);
        }
        return resultList;
    }
    
    public int setEmailId(UserBean targetUser)
    {
        UserDAO userDAO = new UserDAOImpl();
        int rowCount = userDAO.setEmailId(targetUser);
        
        return rowCount;
    }
    
    public int setProfilePictureId(int imageId, String username)
    {
        UserDAO userDAO = new UserDAOImpl();
        int rowCount = userDAO.setProfilePictureId(imageId, username);
        
        return rowCount;
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
     * @return the USER_TYPE_STUDENT
     */
    public String getUSER_TYPE_STUDENT()
    {
        return USER_TYPE_STUDENT;
    }

    /**
     * @param USER_TYPE_STUDENT the USER_TYPE_STUDENT to set
     */
    public void setUSER_TYPE_STUDENT(String USER_TYPE_STUDENT)
    {
        this.USER_TYPE_STUDENT = USER_TYPE_STUDENT;
    }

    /**
     * @return the USER_TYPE_RECRUITER
     */
    public String getUSER_TYPE_RECRUITER()
    {
        return USER_TYPE_RECRUITER;
    }

    /**
     * @param USER_TYPE_RECRUITER the USER_TYPE_RECRUITER to set
     */
    public void setUSER_TYPE_RECRUITER(String USER_TYPE_RECRUITER)
    {
        this.USER_TYPE_RECRUITER = USER_TYPE_RECRUITER;
    }

    /**
     * @return the USER_TYPE_UNIVERSITY
     */
    public String getUSER_TYPE_UNIVERSITY()
    {
        return USER_TYPE_UNIVERSITY;
    }

    /**
     * @param USER_TYPE_UNIVERSITY the USER_TYPE_UNIVERSITY to set
     */
    public void setUSER_TYPE_UNIVERSITY(String USER_TYPE_UNIVERSITY)
    {
        this.USER_TYPE_UNIVERSITY = USER_TYPE_UNIVERSITY;
    }

    /**
     * @return the USER_TYPE_ADMIN
     */
    public String getUSER_TYPE_ADMIN()
    {
        return USER_TYPE_ADMIN;
    }

    /**
     * @param USER_TYPE_ADMIN the USER_TYPE_ADMIN to set
     */
    public void setUSER_TYPE_ADMIN(String USER_TYPE_ADMIN)
    {
        this.USER_TYPE_ADMIN = USER_TYPE_ADMIN;
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
     * @return the resultList
     */
    public ArrayList getResultList()
    {
        return resultList;
    }

    /**
     * @param resultList the resultList to set
     */
    public void setResultList(ArrayList resultList)
    {
        this.resultList = resultList;
    }
    
}
