package Model;

import java.util.ArrayList;

public class UniversityBean
{
    private String profileId;
    private String universityEmail;
    private String username;
    private ArrayList recruiters;
    
    public UniversityBean(String profileId, String universityEmail, String username)
    {
        this.profileId = profileId;
        this.universityEmail = universityEmail;
        this.username = username;
    }

    public UniversityBean()
    {

    }
    
    /**
     * @return the profileId
     */
    public String getProfileId()
    {
        return profileId;
    }

    /**
     * @param profileId the profileId to set
     */
    public void setProfileId(String profileId)
    {
        this.profileId = profileId;
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
     * @return the recruiters
     */
    public ArrayList getRecruiters()
    {
        return recruiters;
    }

    /**
     * @param recruiters the recruiters to set
     */
    public void setRecruiters(ArrayList recruiters)
    {
        this.recruiters = recruiters;
    }

    /**
     * @return the universityEmail
     */
    public String getUniversityEmail()
    {
        return universityEmail;
    }

    /**
     * @param universityEmail the universityEmail to set
     */
    public void setUniversityEmail(String universityEmail)
    {
        this.universityEmail = universityEmail;
    }
}
