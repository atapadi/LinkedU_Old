/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.ImageDAO;
import DAO.ImageDAOImpl;
import java.util.ArrayList;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ericz
 */
public class PostBean
{
    private int postId;
    private int imageId;
    private int videoId;
    private String textContent;
    private String username;
    private ArrayList comments;
    private int profilePictureID;
    private StreamedContent image;

    public PostBean(int postId, int imageId, int videoId, String textContent, String username)
    {
        this.postId = postId;
        this.imageId = imageId;
        this.videoId = videoId;
        this.textContent = textContent;
        this.username = username;
    }
    
     public PostBean(int imageId, int videoId, String textContent, String username)
    {
        this.imageId = imageId;
        this.videoId = videoId;
        this.textContent = textContent;
        this.username = username;
    }
     
    public PostBean(String textContent)
    {
        this.textContent = textContent;
    }
    
    public PostBean()
    {
        
    }

    /**
     * @return the pictureId
     */
    public int getImageId()
    {
        return imageId;
    }

    /**
     * @param pictureId the pictureId to set
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
     * @return the textContent
     */
    public String getTextContent()
    {
        return textContent;
    }

    /**
     * @param textContent the textContent to set
     */
    public void setTextContent(String textContent)
    {
        this.textContent = textContent;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the postId
     */
    public int getPostId()
    {
        return postId;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(int postId)
    {
        this.postId = postId;
    }

    /**
     * @return the comments
     */
    public ArrayList getComments()
    {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(ArrayList comments)
    {
        this.comments = comments;
    }

    /**
     * @return the profilePictureID
     */
    public int getProfilePictureID()
    {
        return profilePictureID;
    }

    /**
     * @param profilePictureID the profilePictureID to set
     */
    public void setProfilePictureID(int profilePictureID)
    {
        this.profilePictureID = profilePictureID;
    }

    /**
     * @return the image
     */
    public StreamedContent getImage()
    {
        System.out.println("getImage called");
        ImageDAO imageDAO = new ImageDAOImpl();
        return imageDAO.selectImageByImageId(imageId);
    }

    /**
     * @param image the image to set
     */
    public void setImage(StreamedContent image)
    {
        System.out.println("setimage");
        this.image = image;
    }
    
    
}
