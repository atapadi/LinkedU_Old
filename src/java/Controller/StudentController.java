/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.StudentDAO;
import DAO.StudentDAOImpl;
import Model.StudentBean;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FlowEvent;

@ManagedBean
@SessionScoped
public class StudentController implements Serializable
{
    private ArrayList resultList;
    private StudentBean studentModel;
    private StudentBean targetStudent; 
    private boolean skip;

    public boolean createStudent(StudentBean studentModel)
    {
        //System.out.println("STUDENTCONTROLLER: createStudent");
        boolean studentInserted = false;
        StudentDAO studentDAO = new StudentDAOImpl();
        int rowCount = studentDAO.createStudent(studentModel);
        
        if(rowCount == 1)
        {
            studentInserted = true;
        }
        
        return studentInserted;
    }
    
    public boolean updateStudent(StudentBean studentModel)
    {
        //System.out.println("STUDENTCONTROLLER: updateStudent");
        boolean studentUpdated = false;
        StudentDAO studentDAO = new StudentDAOImpl();
        int rowCount = studentDAO.updateStudent(studentModel);
        
        if(rowCount == 1)
        {
            studentUpdated = true;
        }
        
        return studentUpdated;
    }
    
    public ArrayList selectStudentByUsername(String targetUsername)
    {
        StudentDAO studentDAO = new StudentDAOImpl();
        resultList = studentDAO.selectStudentByUsername(targetUsername);
        
        if(resultList.size() == 1)
        {
            targetStudent = (StudentBean)resultList.get(0);
            //System.out.println("STUDENTCONTROLLER: Select By Username Successfull");
            //System.out.println("STUDENTCONTROLLER: Target Username - " + targetStudent.getUsername());
        }
        
        return resultList;
    }
    
    public String onFlowProcess(FlowEvent event)
    {
        //System.out.println("ONFLOWPROCESS: " + event);
        //System.out.println("ONFLOWPROCESS: " + skip);
        if (isSkip())
        {
            setSkip(false);   //reset in case user goes back
            return "confirm";
        } 
        else
        {
            return event.getNewStep();
        }
    }
    
    /**
     * Creates a new instance of StudentController
     */
    public StudentController()
    {
        studentModel = new StudentBean();
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
     * @return the studentModel
     */
    public StudentBean getStudentModel()
    {
        return studentModel;
    }

    /**
     * @param studentModel the studentModel to set
     */
    public void setStudentModel(StudentBean studentModel)
    {
        this.studentModel = studentModel;
    }

    /**
     * @return the targetStudent
     */
    public StudentBean getTargetStudent()
    {
        return targetStudent;
    }

    /**
     * @param targetStudent the targetStudent to set
     */
    public void setTargetStudent(StudentBean targetStudent)
    {
        this.targetStudent = targetStudent;
    }

    /**
     * @return the skip
     */
    public boolean isSkip()
    {
        return skip;
    }

    /**
     * @param skip the skip to set
     */
    public void setSkip(boolean skip)
    {
        this.skip = skip;
    }
    
}
