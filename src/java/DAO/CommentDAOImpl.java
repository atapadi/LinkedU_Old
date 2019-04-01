/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.CommentBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Richa
 */
public class CommentDAOImpl implements CommentDAO
{

    ArrayList resultList;
    private Connection DBConn = null;
    private String myDB = "jdbc:derby://10.110.10.26/atapadi_spring2018_LinkedUAppDB";
    private String driver = "org.apache.derby.jdbc.ClientDriver";
    
    int commentId;
    String content;
    String username;
    CommentBean targetComment;
    
    public void connect2DB()
    {
        DBHelper.loadDriver(driver);
        DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
    }

    @Override
    public int createComment(CommentBean commentModel)
    {
        int rowCount = 0;
        String insertString = "INSERT INTO comments (content, postid, username) "
                + "VALUES ('" + commentModel.getContent() + "', " + commentModel.getPostId() +  ", '" + commentModel.getUsername() + "')";
        try
        {
            connect2DB();
            PreparedStatement stmt = DBConn.prepareStatement(insertString, Statement.RETURN_GENERATED_KEYS);
            rowCount = stmt.executeUpdate();
            
            if(rowCount == 1)
            {
                ResultSet rs = stmt.getGeneratedKeys();
                
                if(rs.next())
                {
                    commentId = rs.getInt(1);
                    commentModel.setCommentId(commentId);
                    System.out.println(commentId);
                }   
            }
        }
        catch(Exception e)
        {
           System.err.println(e.getMessage());
        }
        
        return rowCount;
    }

    @Override
    public ArrayList selectCommentsByPostId(int postId)
    {
        resultList = new ArrayList();
        String selectString = "SELECT content, username FROM itkstu.comments "
                + "WHERE postId = " + postId;       
        
        try
        {
            connect2DB();
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);
            
            while(rs.next())
            {
                content = rs.getString("content");
                //System.out.println(content);
                username = rs.getString("username");
                targetComment = new CommentBean(content);
                targetComment.setUsername(username);
                resultList.add(targetComment);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return resultList;
    }
}
