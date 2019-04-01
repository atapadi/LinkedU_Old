/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.UserBean;
import java.util.List;

/**
 *
 * @author ericz
 */
public interface SearchDAO {
    public List<UserBean> SearchByName(String name);
    
}
