/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controller.CommentController;
import Model.PostBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Richa
 */
public class PostDAOImpl implements PostDAO
{
    private ArrayList resultList;
    private Connection DBConn = null;
    private String myDB = "jdbc:derby://10.110.10.26/atapadi_spring2018_LinkedUAppDB";
    private String driver = "org.apache.derby.jdbc.ClientDriver";
    private PostBean targetPost = new PostBean();
    private int imageId;
    private int videoId;
    private String textContent;
    private String username;
    private int postId;
    private ArrayList comments;
    private int profilePictureID;
    private StreamedContent image;
     
    public void connect2DB()
    {
        DBHelper.loadDriver(driver);
        DBConn = DBHelper.connect2DB(myDB, "itkstu","student");
    }
    
    @Override
    public ArrayList selectPostsByUsername(String targetUsername)
    {
        CommentController commentController = new CommentController();
        ImageDAO imageDAO = new ImageDAOImpl();
        resultList = new ArrayList();
        String selectString = "SELECT * FROM itkstu.posts "
                + "WHERE username = '" + targetUsername + "' "
                + "ORDER BY postId DESC ";
        System.out.println("POSTDAOIMPL: " + selectString);
        try
        {
            connect2DB();
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);
            
            while(rs.next())
            {
                textContent = rs.getString("textcontent");
                postId = rs.getInt("postId");
                username = rs.getString("username");
                imageId = rs.getInt("imageid");
                targetPost = new PostBean(textContent);
                targetPost.setPostId(postId);
                targetPost.setUsername(username);
                
                if(imageId != 0)
                {
                    targetPost.setImageId(imageId);
                    System.out.println("TargetPost imageId set: " + targetPost.getImageId());
                    image = imageDAO.selectImageByImageId(imageId);
                    targetPost.setImage(image);
                    System.out.println("TargetPost image set");
                }
                comments = commentController.selectCommentsByPostId(postId);
                targetPost.setComments(comments);
                resultList.add(targetPost);
            }
            DBConn.close();
        }
        catch(Exception e)
        {
            System.err.println("ERROR");
            System.err.println("TARGET: " + targetPost);
            e.printStackTrace();
        }
        return resultList;
    }
    
    @Override 
    public int createPost(PostBean postModel)
    {
        int rowCount = 0;
        
        try
        {
            connect2DB();
            String insert = "INSERT INTO itkstu.posts (username, imageId, videoId, textcontent) "
                    + "VALUES ('" + postModel.getUsername()
                    + "', " + postModel.getImageId()
                    + ", " + postModel.getVideoId()
                    + ", ?)";
            if(postModel.getImageId() == 0)
            {
                insert = "INSERT INTO itkstu.posts (username, videoId, textcontent) "
                        + "VALUES ('" + postModel.getUsername() 
                        + "', " + postModel.getVideoId() 
                        + ", ?)";
            }
            
            System.out.println("POSTDAOIMPL: " + insert);
            PreparedStatement pstmt = DBConn.prepareStatement(insert);
            pstmt.setString(1,postModel.getTextContent());
            rowCount = pstmt.executeUpdate();
            DBConn.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return rowCount;
    }
    
    @Override
    public ArrayList selectAllPosts()
    {
        resultList = new ArrayList();
        String selectString = "SELECT textcontent FROM itkstu.posts"
                + " ORDER BY postId DESC";
        System.out.println("POSTDAOIMPL: " + selectString);
        try
        {
            connect2DB();
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);
            
            while(rs.next())
            {
                textContent = rs.getString("textcontent");
                targetPost = new PostBean(textContent);
                resultList.add(targetPost);
            }
            DBConn.close();
        }
        catch(Exception e)
        {
            System.err.println("ERROR");
            System.err.println("TARGET: " + targetPost);
            e.printStackTrace();
        }
        return resultList;
    }
    
    @Override
    public void setImageId(int imageId, int postId)
    {
        try
        {
            connect2DB();
            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "UPDATE itkstu.posts "
                    + "SET imageid = " + imageId + " "
                    + "WHERE postId = " + postId;
            
            System.out.println("POSTDAOIMPL: " + insertString);
            stmt.executeUpdate(insertString);
            DBConn.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
