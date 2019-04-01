/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostDAO;
import DAO.PostDAOImpl;
import Model.PostBean;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Richa
 */
@Named(value = "postController")
@SessionScoped
@ManagedBean
public class PostController implements Serializable
{
    private PostBean postModel;
    
    public void createPost()
    {
        PostDAO postDAO = new PostDAOImpl();
        postDAO.createPost(postModel);
    }

    public ArrayList selectAllPosts()
    {
        PostDAO postDAO = new PostDAOImpl();
        return postDAO.selectAllPosts();
    }
    
    public ArrayList selectPostsByUsername(String targetUsername)
    {
        PostDAO postDAO = new PostDAOImpl();
        return postDAO.selectPostsByUsername(targetUsername);
    }
    
    public PostController()
    {
        postModel = new PostBean();
    }

    /**
     * @return the postModel
     */
    public PostBean getPostModel()
    {
        return postModel;
    }

    /**
     * @param postModel the postModel to set
     */
    public void setPostModel(PostBean postModel)
    {
        this.postModel = postModel;
    }
    
}
