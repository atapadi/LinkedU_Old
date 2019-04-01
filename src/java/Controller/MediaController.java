/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ImageDAO;
import DAO.ImageDAOImpl;
import DAO.UserDAO;
import DAO.UserDAOImpl;
import Model.UserBean;
import java.io.Serializable;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.context.SessionScoped;
//import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
//import static org.primefaces.component.focus.Focus.PropertyKeys.context;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ericz
 */
@Named(value = "mediaController")
@SessionScoped
@ManagedBean
public class MediaController implements Serializable
{

    private UploadedFile file;
    private UserBean currentUser;
    private UserBean targetUser;
    private StreamedContent image;
    private int imageId;
    private FacesContext facesContext;
    private HttpSession session;
    private LoginController loginSession;
    private UserBean user;

    public UploadedFile getFile()
    {
        return file;
    }

    public void setFile(UploadedFile file)
    {
        this.file = file;
    }
    
    public MediaController()
    {
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        loginSession = (LoginController) session.getAttribute("loginController");
        user = loginSession.getTargetUser();
        //System.out.println("MediaController created");
    }
    
    public String upload()
    {
        //System.out.println(user.getUsername());
        if (file != null)
        {
            ImageDAO dao = new ImageDAOImpl();
            //System.out.println(user.getUsername());
            imageId = dao.createImage(file, user.getUsername());
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            image = dao.selectImageByImageId(imageId);
        }
        return "profilestandard.xhtml";
    }

    public StreamedContent getProfileImage()
    {
        UserDAO userDAO = new UserDAOImpl();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        LoginController loginSession = (LoginController) session.getAttribute("loginController");
        UserBean user = (UserBean)userDAO.selectUserByUsername(loginSession.getTargetUser().getUsername()).get(0);
        //System.out.println(user.getUsername());
        System.out.println("MEDIACONTROLLER: getProfileImage() - profilePictureId = " + user.getProfilePictureID());
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
        {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else
        {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            // String studentId = context.getExternalContext().getRequestParameterMap().get("studentId");
            ImageDAO dao = new ImageDAOImpl();

            return dao.getProfileImage(user);
        }
    }
    
    public StreamedContent selectImageByUsername(String username)
    {
        //System.out.println(username);
        StreamedContent targetImage;
        UserBean targetUser;       
        UserDAO userDAO = new UserDAOImpl();
        targetUser = (UserBean)userDAO.selectUserByUsername(username).get(0);
        ImageDAO imageDAO = new ImageDAOImpl();
        targetImage = imageDAO.selectImageByImageId(targetUser.getProfilePictureID());
        //System.out.println("image returned so media controller");
        return targetImage;
    }
    
    public StreamedContent selectImageByImageId(int imageId)
    {
        StreamedContent targetImage;
        ImageDAO imageDAO = new ImageDAOImpl();
        targetImage = imageDAO.selectImageByImageId(imageId);
        return targetImage;
    }
    
    public UserBean getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(UserBean currentUser)
    {
        this.currentUser = currentUser;
    }

    public UserBean getTargetUser()
    {
        return targetUser;
    }

    public void setTargetUser(UserBean targetUser)
    {
        this.targetUser = targetUser;
    }

    /**
     * @return the image
     */
    public StreamedContent getImage()
    {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(StreamedContent image)
    {
        this.image = image;
    }

    /**
     * @return the imageId
     */
    public int getImageId()
    {
        return imageId;
    }

    /**
     * @param imageId the imageId to set
     */
    public void setImageId(int imageId)
    {
        this.imageId = imageId;
    }

}
