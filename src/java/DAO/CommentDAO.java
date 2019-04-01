/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.CommentBean;
import java.util.ArrayList;

/**
 *
 * @author Richa
 */
public interface CommentDAO
{
    public int createComment(CommentBean commentModel);
    public ArrayList selectCommentsByPostId(int commentId);
}
