package DAO;

import Model.StudentBean;
import Model.UserBean;
import java.util.ArrayList;

public interface UserDAO 
{
    public int createUser(UserBean userModel);
    public int updateUser(UserBean userModel);
    public ArrayList selectUserByUsername(String targetUsername);
    public ArrayList selectUserByUsertype(String targetUsertype); 
    public int setEmailId(UserBean userModel);
    public int setProfilePictureId(int imageId, String username);
    int updateEmailDB(StudentBean studentBean);
    public ArrayList DisplayStudentInfo();
    int SubscribeEmail(StudentBean studentBean);
}
