/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.RecruiterBean;
import Model.StudentBean;
import Model.UserBean;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable
{

    private RecruiterBean recruiterModel;
    private StudentBean studentModel;
    private UserBean userModel;
    private UserBean targetUser;
    private RecruiterController recruiterController;
    private StudentController studentController;
    private UserController userController;
    private Boolean isLoggedIn = false;

    private String loginStatus;
    private final String SALT = "Project353";
    private int loginAttempt = 0;

    /**
     * Creates a new instance of LoginController
     */
    
    public LoginController()
    {
        recruiterController = new RecruiterController();
        studentController = new StudentController();
        userController = new UserController();
        userModel = new UserBean();
        targetUser = null;
        loginAttempt = 0;
    }

    /*method for login authentication*/
    public String login()
    {
        String returnString = "";
        String saltedPassword = SALT + userModel.getPassword();
        String hashedPassword = SignupController.generateHash(saltedPassword);

        if (loginAttempt < 3)
        {
            loginAttempt++;
            if(userModel.getUsername().equalsIgnoreCase("admin"))
            {
                    isLoggedIn = true;
                    loginAttempt = 0;
                    returnString = "Admin.xhtml?faces-redirect=ture";

            }
            else
            {
                userController.setUserModel(userModel);
                targetUser = userController.selectUserByUsername();
                if (targetUser != null)
                {
                    System.out.println("LOGINCONTROLLER: " + hashedPassword);

                    if (hashedPassword.equalsIgnoreCase(targetUser.getPassword()))
                    {
                        isLoggedIn = true;
                        loginAttempt = 0;
                        if(targetUser.getUserType().equalsIgnoreCase("recruiter"))
                        {
                            returnString = "recruiter.xhtml?faces-redirect=true";
                        }
                        else
                        {
                           returnString = "profilestandard.xhtml?faces-redirect=true";
                        }
                    } else
                    {
                        setLoginStatus("Invalid Credentials");
                        returnString = "logIn.xhtml?faces-redirect=true";
                    }
                } else
                {
                    setLoginStatus("Username Does Not Exist");
                    return ("logIn.xhtml?faces-redirect=true");
                }
            }          
        } 
        else
        {
            setLoginStatus("Exceed max number of trials! Try after some time");
        }
        return returnString;
    }

    /**
     * @return the loginStatus
     */
    public String getLoginStatus()
    {
        return loginStatus;
    }

    /**
     * @param loginStatus the loginStatus to set
     */
    public void setLoginStatus(String loginStatus)
    {
        this.loginStatus = loginStatus;
    }

    /**
     * @return the loginAttempt
     */
    public int getLoginAttempt()
    {
        return loginAttempt;
    }

    /**
     * @param loginAttempt the loginAttempt to set
     */
    public void setLoginAttempt(int loginAttempt)
    {
        this.loginAttempt = loginAttempt;
    }

    /**
     * @return the recruiterController
     */
    public RecruiterController getRecruiterController()
    {
        return recruiterController;
    }

    /**
     * @param recruiterController the recruiterController to set
     */
    public void setRecruiterController(RecruiterController recruiterController)
    {
        this.recruiterController = recruiterController;
    }

    /**
     * @return the studentController
     */
    public StudentController getStudentController()
    {
        return studentController;
    }

    /**
     * @param studentController the studentController to set
     */
    public void setStudentController(StudentController studentController)
    {
        this.studentController = studentController;
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
     * @return the recruiterModel
     */
    public RecruiterBean getRecruiterModel()
    {
        return recruiterModel;
    }

    /**
     * @param recruiterModel the recruiterModel to set
     */
    public void setRecruiterModel(RecruiterBean recruiterModel)
    {
        this.recruiterModel = recruiterModel;
    }

    /**
     * @return the studentModel
     */
    public StudentBean getStudentModel()
    {
        return studentModel;
    }

    /**
     * @param studentModel the studentModel to set
     */
    public void setStudentModel(StudentBean studentModel)
    {
        this.studentModel = studentModel;
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
     * @return the isLoggedIn
     */
    public Boolean getIsLoggedIn()
    {
        return isLoggedIn;
    }

    /**
     * @param isLoggedIn the isLoggedIn to set
     */
    public void setIsLoggedIn(Boolean isLoggedIn)
    {
        this.isLoggedIn = isLoggedIn;
    }
}
