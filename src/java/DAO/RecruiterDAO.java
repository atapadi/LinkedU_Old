package DAO;

import Model.RecruiterBean;
import java.util.ArrayList;

public interface RecruiterDAO
{
    public int createRecruiter(RecruiterBean recruiterModel);
    public int updateRecruiter(RecruiterBean recruiterModel);
    public ArrayList selectRecruiterByUsername(String targetUsername);
    public ArrayList DisplayStudentInfo();
}