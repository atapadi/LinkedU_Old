/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import DAO.UniversityDAO;
import DAO.UniversityDAOImpl;
import DAO.UserDAO;
import DAO.UserDAOImpl;
import Model.RecruiterBean;
import Model.StudentBean;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class UpdateController implements Serializable
{
    private StudentController studentController;
    private StudentBean studentBean;
    private RecruiterBean recruiterBean;
    private String updateStatus;
    private boolean skip;
    private String EmailMessage;
    private String AdmissionMessage;
    
    public UpdateController()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        LoginController loginSession = (LoginController)session.getAttribute("loginController");
        studentBean = loginSession.getTargetUser().getTargetStudent();
        recruiterBean = loginSession.getTargetUser().getTargetRecruiter();
        studentController = new StudentController();
        System.out.println(studentBean.getUsername());
    }
    
    public String updateStudent()
    {
        String resultString = "";
        if(studentController.updateStudent(studentBean))
        {
            resultString = "profilestandard.xhtml";
        }       
        
        return resultString;
    }
    
    public String updateUsers(){
        UserDAO userDAO = new UserDAOImpl();
        int result = userDAO.updateEmailDB(studentBean);
        if(result==1)
        return EmailMessage = "Successfully UnSubscribed";
        else
            return EmailMessage = "Error in UnSubscription";
    }
    
     public String subscribeEmail(){
        UserDAO userDAO = new UserDAOImpl();
        int result = userDAO.SubscribeEmail(studentBean);
        if(result==1)
        return EmailMessage = "Successfully Subscribed";
        else
            return EmailMessage = "Error in Subscription";
    }
    
    public String applyAdmission(){
   
    UniversityDAO universitydao = new UniversityDAOImpl();
    String result = universitydao.applyForAdmission(studentBean);
    if(result.equalsIgnoreCase("successfull"))
       AdmissionMessage = "Successfully Applied! Check your email.";
    else
        AdmissionMessage = "Unsuccessfully";
    return AdmissionMessage;
    }
    
   
    /**
     * @return the studentBean
     */
    public StudentBean getStudentBean()
    {
        return studentBean;
    }

    /**
     * @param studentBean the studentBean to set
     */
    public void setStudentBean(StudentBean studentBean)
    {
        this.studentBean = studentBean;
    }

    public String getUpdateStatus()
    {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus)
    {
        this.updateStatus = updateStatus;
    }

    public String onFlowProcess(FlowEvent event)
    {
        if (isSkip())
        {
            setSkip(false);   //reset in case user goes back
            return "confirm";
        } else
        {
            return event.getNewStep();
        }
    }

    /**
     * @return the skip
     */
    public boolean isSkip()
    {
        return skip;
    }

    /**
     * @param skip the skip to set
     */
    public void setSkip(boolean skip)
    {
        this.skip = skip;
    }

    /**
     * @return the EmailMessage
     */
    public String getEmailMessage() {
        return EmailMessage;
    }

    /**
     * @param EmailMessage the EmailMessage to set
     */
    public void setEmailMessage(String EmailMessage) {
        this.EmailMessage = EmailMessage;
    }

    /**
     * @return the AdmissionMessage
     */
    public String getAdmissionMessage() {
        return AdmissionMessage;
    }

    /**
     * @param AdmissionMessage the AdmissionMessage to set
     */
    public void setAdmissionMessage(String AdmissionMessage) {
        this.AdmissionMessage = AdmissionMessage;
    }

    /**
     * @return the recruiterBean
     */
    public RecruiterBean getRecruiterBean() {
        return recruiterBean;
    }

    /**
     * @param recruiterBean the recruiterBean to set
     */
    public void setRecruiterBean(RecruiterBean recruiterBean) {
        this.recruiterBean = recruiterBean;
    }

}
