package Model;

import java.util.ArrayList;

public class RecruiterBean
{
    private int profileId;
    private String university;
    private String username;
    private String department;
    private String phone;
    
    private String Fname;
    private String Lname;
    private String Email;
    private String School;
    private String SAT;
    private String ACT;
    private String PSAT;
    private ArrayList posts;

    public RecruiterBean(int profileId, String department, String phone, String university, String username)
    {
        this.profileId = profileId;
        this.department = department;
        this.phone = phone;
        this.university = university;
        this.username = username;
    }

    public RecruiterBean(String Fname, String Lname, String Email, String School, String SAT, String ACT, String PSAT)
    {
        this.Fname = Fname;
        this.Lname = Lname;
        this.Email = Email;
        this.School = School;
        this.SAT=  SAT;
        this.ACT = ACT;
        this.PSAT = PSAT;
    }
    
    public RecruiterBean()
    {

    }

    /**
     * @return the profileId
     */
    public int getProfileId()
    {
        return profileId;
    }

    /**
     * @param profileId the profileId to set
     */
    public void setProfileId(int profileId)
    {
        this.profileId = profileId;
    }

    /**
     * @return the university
     */
    public String getUniversity()
    {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(String university)
    {
        this.university = university;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the department
     */
    public String getDepartment()
    {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department)
    {
        this.department = department;
    }

    /**
     * @return the phone
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
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

    /**
     * @return the posts
     */
    public ArrayList getPosts()
    {
        return posts;
    }

    /**
     * @param posts the posts to set
     */
    public void setPosts(ArrayList posts)
    {
        this.posts = posts;
    }
    
}
