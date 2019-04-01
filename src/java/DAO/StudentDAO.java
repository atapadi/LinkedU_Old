package DAO;

import Model.StudentBean;
import java.util.ArrayList;

public interface StudentDAO
{
    public int createStudent(StudentBean studentProfile);
    public int updateStudent(StudentBean studentProfile);
    public ArrayList selectStudentByUsername(String targetUsername);
}