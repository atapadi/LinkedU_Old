/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

public class StudentBean
{

    private int profileId;
    private String dateOfBirth;
    private int height;
    private int weight;
    private String street;
    private String city;
    private String country;
    private String zipcode;
    private String phone;
    private String school;
    private int endYear;
    private int sat;
    private int act;
    private int psat;
    private String certification;
    private String essay;
    private String hobbies;
    private String username;
    private ArrayList post = new ArrayList();
    private boolean subscription;

    public StudentBean()
    {
        
    }

    public StudentBean(int profileId, String dateOfBirth, int height, int weight, String street, String city, String country,
            String zipcode, String phone, String school, int endYear, int sat, int act, int psat, String certification,
            String essay, String hobbies, String username)
    {
        this.profileId = profileId;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
        this.phone = phone;
        this.school = school;
        this.endYear = endYear;
        this.sat = sat;
        this.act = act;
        this.psat = psat;
        this.certification = certification;
        this.essay = essay;
        this.hobbies = hobbies;
        this.username = username;
    }

    public StudentBean(int profileId, String dateOfBirth, int height, int weight, String street, String city, String country,
            String zipcode, String phone, String school, int endYear, int sat, int act, int psat, String certification,
            String essay, String hobbies)
    {
        this.profileId = profileId;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
        this.phone = phone;
        this.school = school;
        this.endYear = endYear;
        this.sat = sat;
        this.act = act;
        this.psat = psat;
        this.certification = certification;
        this.essay = essay;
        this.hobbies = hobbies;
    }

    public StudentBean(String dateOfBirth, int height, int weight, String street, String city, String country,
            String zipcode, String phone, String school, int endYear, int sat, int act, int psat, String certification,
            String essay, String hobbies)
    {
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
        this.phone = phone;
        this.school = school;
        this.endYear = endYear;
        this.sat = sat;
        this.act = act;
        this.psat = psat;
        this.certification = certification;
        this.essay = essay;
        this.hobbies = hobbies;

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
     * @return the dateOfBirth
     */
    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * @return the weight
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    /**
     * @return the street
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street)
    {
        this.street = street;
    }

    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country)
    {
        this.country = country;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode()
    {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
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
     * @return the school
     */
    public String getSchool()
    {
        return school;
    }

    /**
     * @param school the school to set
     */
    public void setSchool(String school)
    {
        this.school = school;
    }

    /**
     * @return the endYear
     */
    public int getEndYear()
    {
        return endYear;
    }

    /**
     * @param endYear the endYear to set
     */
    public void setEndYear(int endYear)
    {
        this.endYear = endYear;
    }

    /**
     * @return the sat
     */
    public int getSat()
    {
        return sat;
    }

    /**
     * @param sat the sat to set
     */
    public void setSat(int sat)
    {
        this.sat = sat;
    }

    /**
     * @return the act
     */
    public int getAct()
    {
        return act;
    }

    /**
     * @param act the act to set
     */
    public void setAct(int act)
    {
        this.act = act;
    }

    /**
     * @return the psat
     */
    public int getPsat()
    {
        return psat;
    }

    /**
     * @param psat the psat to set
     */
    public void setPsat(int psat)
    {
        this.psat = psat;
    }

    /**
     * @return the certification
     */
    public String getCertification()
    {
        return certification;
    }

    /**
     * @param certification the certification to set
     */
    public void setCertification(String certification)
    {
        this.certification = certification;
    }

    /**
     * @return the essay
     */
    public String getEssay()
    {
        return essay;
    }

    /**
     * @param essay the essay to set
     */
    public void setEssay(String essay)
    {
        this.essay = essay;
    }

    /**
     * @return the hobbies
     */
    public String getHobbies()
    {
        return hobbies;
    }

    /**
     * @param hobbies the hobbies to set
     */
    public void setHobbies(String hobbies)
    {
        this.hobbies = hobbies;
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
     * @return the post
     */
    public ArrayList getPost()
    {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(ArrayList post)
    {
        this.post = post;
    }

    
     
    public boolean isSubscription() {
        return subscription;
    }

    /**
     * @param subscription the subscription to set
     */
    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

}
