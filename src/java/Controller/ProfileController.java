package Controller;

import Model.UserBean;
import Model.StudentBean;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "profileController")
@SessionScoped
@ManagedBean
public class ProfileController implements Serializable
{

    private UserBean userModel;
    private UserController userController;
    private PostController postController;
    private CommentController commentController;
    //private MediaController mediaController;
    private int imageId = 1;
    private int videoId = 1;

    public ProfileController()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        LoginController loginSession = (LoginController) session.getAttribute("loginController");
        userModel = loginSession.getTargetUser();
        postController = new PostController();
        commentController = new CommentController();
        userController = new UserController();

    }

    public void createPost(int imageId)
    {
      //FacesContext facesContext = FacesContext.getCurrentInstance();
      //HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
      //MediaController mediaController = (MediaController)session.getAttribute("mediaController");
        postController.getPostModel().setUsername(userModel.getUsername());
        System.out.println("setUsername()");
        
        //System.out.println(mediaController.getImageId());
        postController.getPostModel().setImageId(imageId);
        if(imageId == 0)         
        System.out.println("getImageId");
        postController.getPostModel().setVideoId(videoId);
        postController.createPost();
        System.out.println("Create Post()");
        postController.getPostModel().setTextContent("");
        StudentBean studentModel = (StudentBean) userModel.getProfile();
        studentModel.setPost(getPosts());
        userModel.setTargetStudent(studentModel);
    }

    public void createComment(int postId)
    {
        System.out.println(postId);
        commentController.getCommentModel().setPostId(postId);
        commentController.createComment();
        commentController.getCommentModel().setContent("");
        userController.setUserModel(userModel);
        userModel = userController.selectUserByUsername();

    }

    public ArrayList getPosts()
    {
        return postController.selectPostsByUsername(userModel.getUsername());
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
     * @return the postController
     */
    public PostController getPostController()
    {
        return postController;
    }

    /**
     * @param postController the postController to set
     */
    public void setPostController(PostController postController)
    {
        this.postController = postController;
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

    /**
     * @return the videoId
     */
    public int getVideoId()
    {
        return videoId;
    }

    /**
     * @param videoId the videoId to set
     */
    public void setVideoId(int videoId)
    {
        this.videoId = videoId;
    }

    /**
     * @return the commentController
     */
    public CommentController getCommentController()
    {
        return commentController;
    }

    /**
     * @param commentController the commentController to set
     */
    public void setCommentController(CommentController commentController)
    {
        this.commentController = commentController;
    }

    public int getHeight(){
        return userModel.getTargetStudent().getHeight();
    }
    public int getWeight(){
        return userModel.getTargetStudent().getWeight();
    }
    public String getCountry(){
        return userModel.getTargetStudent().getCountry();
    }
    public String getDOB(){
        int length=userModel.getTargetStudent().getDateOfBirth().length();
        return userModel.getTargetStudent().getDateOfBirth().substring(0,length-17);
    }
    public String getSchool(){
        return userModel.getTargetStudent().getSchool();
    }
    public String getUserName(){
        return userModel.getTargetStudent().getUsername();
    }
    public int getACT(){
        return userModel.getTargetStudent().getAct();
    }
    public int getSAT(){
        return userModel.getTargetStudent().getSat();
    }
    public int getPSAT(){
        return userModel.getTargetStudent().getPsat();
    }
    public int getEndYear(){
        return userModel.getTargetStudent().getEndYear();
    }
    public String getCertifications(){
        return userModel.getTargetStudent().getCertification();
    }
    public String getHobbies(){
        return userModel.getTargetStudent().getHobbies();
    }
    //recruiter info
    public String getDepartment(){
        return userModel.getTargetRecruiter().getDepartment();
    }
    public String getUniversity(){
        return userModel.getTargetRecruiter().getUniversity();
    }
    public String getPhone(){
        return userModel.getTargetRecruiter().getPhone();
    }
    public String profilePage()
    {

        return "profilestandard.xhtml";
    }

}
