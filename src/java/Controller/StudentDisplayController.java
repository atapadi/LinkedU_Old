/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RecruiterDAO;
import DAO.RecruiterDAOImpl;
import Model.StudentInfoBean;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author IT353S843
 */
@ManagedBean
@SessionScoped
public class StudentDisplayController {
    private StudentInfoBean studentModel;
    private StudentInfoBean targetBean;
    private List listOfStudents;
    /**
     * Creates a new instance of StudentDisplay
     */
    public StudentDisplayController() {
        studentModel = new StudentInfoBean();
    }

    /**
     * @return the listOfStudents
     */
    public List getListOfStudents() {
        RecruiterDAO recruiterDAO = new RecruiterDAOImpl();
        listOfStudents = recruiterDAO.DisplayStudentInfo();  
         for(int i = 0; i < listOfStudents.size(); i++)
        {
            targetBean = (StudentInfoBean)listOfStudents.get(i);
            System.out.println("SELECT BY USERTYPE SUCCESSFULL");
        }
        
        return listOfStudents;    
    }

    /**
     * @param listOfStudents the listOfStudents to set
     */
    public void setListOfStudents(List listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    /**
     * @return the studentModel
     */
    public StudentInfoBean getStudentModel() {
        return studentModel;
    }

    /**
     * @param studentModel the studentModel to set
     */
    public void setStudentModel(StudentInfoBean studentModel) {
        this.studentModel = studentModel;
    }

    /**
     * @return the targetBean
     */
    public StudentInfoBean getTargetBean() {
        return targetBean;
    }

    /**
     * @param targetBean the targetBean to set
     */
    public void setTargetBean(StudentInfoBean targetBean) {
        this.targetBean = targetBean;
    }
    
}
