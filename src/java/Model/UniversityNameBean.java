/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author IT353S843
 */
public class UniversityNameBean {
    
    public String[] universityName;
    public String adminMessage;
    
    public UniversityNameBean()
    {      
    }

    /**
     * @return the universityName
     */
    public String[] getUniversityName() {
        return universityName;
    }

    /**
     * @param universityName the universityName to set
     */
    public void setUniversityName(String[] universityName) {
        this.universityName = universityName;
    }

    /**
     * @return the adminMessage
     */
    public String getAdminMessage() {
        return adminMessage;
    }

    /**
     * @param adminMessage the adminMessage to set
     */
    public void setAdminMessage(String adminMessage) {
        this.adminMessage = adminMessage;
    }


    
}
