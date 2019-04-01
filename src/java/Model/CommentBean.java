package Model;

/**
 *
 * @author Richa
 */
public class CommentBean
{
    private String content;
    private int commentId;
    private int postId;
    private String username;
    
    public CommentBean(int commentId, String content, int postId)
    {
        this.commentId = commentId;
        this.content = content;
    }
    
    public CommentBean(String content, int postId)
    {
        this.content = content;
    }
    
    public CommentBean(String content)
    {
        this.content = content;
    }
    
    public CommentBean(int commentId)
    {
        this.commentId = commentId;
    }
    
    public CommentBean()
    {
        
    }

    /**
     * @return the content
     */
    public String getContent()
    {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * @return the commentId
     */
    public int getCommentId()
    {
        return commentId;
    }

    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(int commentId)
    {
        this.commentId = commentId;
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
    
}
