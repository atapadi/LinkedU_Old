/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RecruiterDAO;
import DAO.RecruiterDAOImpl;
import Model.RecruiterBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author IT353S843
 */
@ManagedBean
@SessionScoped
public class RecruiterController
{
    private ArrayList resultList;
    private RecruiterBean recruiterModel;
    private RecruiterBean targetRecruiter;
    private List listOfStudents;
    
    public boolean createRecruiter(RecruiterBean recruiterModel)
    {
        boolean recruiterInserted = false;  
        RecruiterDAO recruiterDAO = new RecruiterDAOImpl();
        int rowCount = recruiterDAO.createRecruiter(recruiterModel);
        
        if(rowCount == 1)
        {
            recruiterInserted = true;
        } 
        
        return recruiterInserted;
    }
    
    public ArrayList selectRecruiterByUsername(String targetUsername)
    {
        RecruiterDAO recruiterDAO = new RecruiterDAOImpl();
        setResultList(recruiterDAO.selectRecruiterByUsername(targetUsername));
        
        if(resultList.size() == 1)
        {
            targetRecruiter = (RecruiterBean)resultList.get(0);
        }
        
        return resultList;
    }

    /**
     * Creates a new instance of RecruiterController
     */
    public RecruiterController()
    {
        recruiterModel = new RecruiterBean();
    }

    /**
     * @return the recruiterBean
     */
    public RecruiterBean getRecruiterModel()
    {
        return recruiterModel;
    }

    /**
     * @param recruiterBean the recruiterBean to set
     */
    public void setRecruiterBean(RecruiterBean recruiterModel)
    {
        this.setRecruiterModel(recruiterModel);
    }

    /**
     * @return the resultList
     */
    public ArrayList getResultList()
    {
        return resultList;
    }

    /**
     * @param resultList the resultList to set
     */
    public void setResultList(ArrayList resultList)
    {
        this.resultList = resultList;
    }

    /**
     * @param recruiterModel the recruiterModel to set
     */
    public void setRecruiterModel(RecruiterBean recruiterModel)
    {
        this.recruiterModel = recruiterModel;
    }

    /**
     * @return the targetRecruiter
     */
    public RecruiterBean getTargetRecruiter()
    {
        return targetRecruiter;
    }

    /**
     * @param targetRecruiter the targetRecruiter to set
     */
    public void setTargetRecruiter(RecruiterBean targetRecruiter)
    {
        this.targetRecruiter = targetRecruiter;
    }

    /**
     * @return the listOfStudents
     */
    public List getListOfStudents() {
        RecruiterDAO recruiterDAO = new RecruiterDAOImpl();
        listOfStudents = recruiterDAO.DisplayStudentInfo();  
        
        return listOfStudents;
    }

    /**
     * @param listOfStudents the listOfStudents to set
     */
    public void setListOfStudents(List listOfStudents) {
        this.listOfStudents = listOfStudents;
    }
}
