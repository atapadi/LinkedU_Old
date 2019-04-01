/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ShowcaseUniversityDAO;
import DAO.ShowcaseUniversityDAOImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author IT353S843
 */
@ManagedBean
@RequestScoped
public class ShowcaseUniversityController {

   private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        
        ShowcaseUniversityDAO showcaseDAO = new ShowcaseUniversityDAOImpl();
        images = showcaseDAO.GetShowCaseUniversities();
         
    }
 
    public List<String> getImages() {
        return images;
    }
  
}
