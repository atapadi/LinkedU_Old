/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CommentDAO;
import DAO.CommentDAOImpl;
import Model.CommentBean;
import Model.UserBean;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Richa
 */
@Named(value = "commentController")
@SessionScoped
@ManagedBean
public class CommentController implements Serializable
{
    ArrayList resultList;
    private CommentBean commentModel;
    private CommentBean targetComment;
    private FacesContext facesContext;
    private HttpSession session;
    private LoginController loginSession;
    private UserBean user;
    /**
     * Creates a new instance of CommentController
     */
    
    public boolean createComment()
    {
        commentModel.setUsername(user.getUsername());
        boolean commentCreated = false;
        CommentDAO commentDAO = new CommentDAOImpl();
        int rowCount = commentDAO.createComment(commentModel);
        
        if(rowCount == 1)
        {
            commentCreated = true;
        }
        
        return commentCreated;
    }
    
    public ArrayList selectCommentsByPostId(int postId)
    {
        CommentDAO commentDAO = new CommentDAOImpl();
        resultList = commentDAO.selectCommentsByPostId(postId);
        
        return resultList;
    }
    
    public CommentController()
    {
        commentModel = new CommentBean();
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        loginSession = (LoginController) session.getAttribute("loginController");
        user = loginSession.getTargetUser();
    }

    /**
     * @return the commentModel
     */
    public CommentBean getCommentModel()
    {
        return commentModel;
    }

    /**
     * @param commentModel the commentModel to set
     */
    public void setCommentModel(CommentBean commentModel)
    {
        this.commentModel = commentModel;
    }

    /**
     * @return the targetComment
     */
    public CommentBean getTargetComment()
    {
        return targetComment;
    }

    /**
     * @param targetComment the targetComment to set
     */
    public void setTargetComment(CommentBean targetComment)
    {
        this.targetComment = targetComment;
    }
}
