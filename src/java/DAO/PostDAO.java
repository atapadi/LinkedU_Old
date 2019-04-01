/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.PostBean;
import java.util.ArrayList;

/**
 *
 * @author Richa
 */
public interface PostDAO
{
    public int createPost(PostBean PostModel);
    public ArrayList selectAllPosts();
    public ArrayList selectPostsByUsername(String targetUsername);
    public void setImageId(int imageId, int postId);
}
