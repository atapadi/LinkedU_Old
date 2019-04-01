package Controller;

import DAO.ShowcaseUniversityDAO;
import DAO.ShowcaseUniversityDAOImpl;
import Model.UniversityNameBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author IT353S843
 */
@ManagedBean
@RequestScoped
public class AdminController {
    private String response ="";    
    public UniversityNameBean universityBean;
    private String responseEmail;
    
    public AdminController(){
        universityBean = new UniversityNameBean();
    }
    
    
    public String showcaseUniversities(){
        int result = 0;
      ShowcaseUniversityDAO auniversityDAO = new ShowcaseUniversityDAOImpl();    // Creating a new object each time.
            for(int i=0; i < universityBean.universityName.length; i++){
                result =  auniversityDAO.updateUniversityShowcase(universityBean.universityName[i]);
                result++ ;
            }    
           if(result > 0)
           {
               response = "University Updated.";
           }
           else
               response = "Please Select atleast one checkbox.";
         return response;    
    }
   
     public String unShowcaseUniversities(){
        int result = 0;
        ShowcaseUniversityDAO auniversityDAO = new ShowcaseUniversityDAOImpl();    // Creating a new object each time.
            for(int i=0; i < universityBean.universityName.length; i++){
                result =  auniversityDAO.updateUniversityUnShowcase(universityBean.universityName[i]);
                result++;
            }    
           if(result > 0)
           {
               response = "University Unshowcased.";
           }
           else
               response = "Please Select atleast one checkbox.";
         return response;    
    }
    
    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * @return the universityBean
     */
    public UniversityNameBean getUniversityBean() {
        return universityBean;
    }

    /**
     * @param universityBean the universityBean to set
     */
    public void setUniversityBean(UniversityNameBean universityBean) {
        this.universityBean = universityBean;
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }
    
     public String emailSubscription(){
        int result = 0;
        ShowcaseUniversityDAO auniversityDAO = new ShowcaseUniversityDAOImpl();    // Creating a new object each time.
        result = auniversityDAO.adminEmail(universityBean.getAdminMessage());
               
           if(result > 0)
           {
               responseEmail = "Email Successfully sent.";
           }
           else
               responseEmail = "Error.";
         return responseEmail;    
    }

    /**
     * @return the responseEmail
     */
    public String getResponseEmail() {
        return responseEmail;
    }

    /**
     * @param responseEmail the responseEmail to set
     */
    public void setResponseEmail(String responseEmail) {
        this.responseEmail = responseEmail;
    }

}
