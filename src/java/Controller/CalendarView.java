/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ShowcaseUniversityDAO;
import DAO.ShowcaseUniversityDAOImpl;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author IT353S843
 */
@ManagedBean
@SessionScoped
public class CalendarView {
    private Date date;
    private String Message;
    private Date currentDate = new Date();

    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * Creates a new instance of CalendarView
     */
    public CalendarView() {
    }
      
    public String onDateSelect(){
        ShowcaseUniversityDAO universityDAO = new ShowcaseUniversityDAOImpl();
        int result = universityDAO.updateDate(date);
        if(result==1){
            Message = "Successfully scheduled";
           return Message;
        }
        else{
             Message = "Date is Already taken";
              return Message;
        }        
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the Message
     */
    public String getMessage() {
        return Message;
    }

    /**
     * @param Message the Message to set
     */
    public void setMessage(String Message) {
        this.Message = Message;
    }
   
    
}
