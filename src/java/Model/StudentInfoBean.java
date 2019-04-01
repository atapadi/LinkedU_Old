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
public class StudentInfoBean {
    private String Fname;
    private String Lname;
    private String Email;
    private String School;
    private String SAT;
    private String ACT;
    private String PSAT;

    
    public StudentInfoBean(){
        
    }
    
    public StudentInfoBean(String Fname, String Lname, String Email, String School, String SAT, String ACT, String PSAT){
        this.Fname = Fname;
        this.Lname = Lname;
        this.Email = Email;
        this.School = School;
        this.SAT=  SAT;
        this.ACT = ACT;
        this.PSAT = PSAT;
    }
    
    /**
     * @return the Fname
     */
    public String getFname() {
        return Fname;
    }

    /**
     * @param Fname the Fname to set
     */
    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    /**
     * @return the Lname
     */
    public String getLname() {
        return Lname;
    }

    /**
     * @param Lname the Lname to set
     */
    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the School
     */
    public String getSchool() {
        return School;
    }

    /**
     * @param School the School to set
     */
    public void setSchool(String School) {
        this.School = School;
    }

    /**
     * @return the SAT
     */
    public String getSAT() {
        return SAT;
    }

    /**
     * @param SAT the SAT to set
     */
    public void setSAT(String SAT) {
        this.SAT = SAT;
    }

    /**
     * @return the ACT
     */
    public String getACT() {
        return ACT;
    }

    /**
     * @param ACT the ACT to set
     */
    public void setACT(String ACT) {
        this.ACT = ACT;
    }

    /**
     * @return the PSAT
     */
    public String getPSAT() {
        return PSAT;
    }

    /**
     * @param PSAT the PSAT to set
     */
    public void setPSAT(String PSAT) {
        this.PSAT = PSAT;
    }
    
}
