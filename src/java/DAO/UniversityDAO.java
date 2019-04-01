/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.StudentBean;
import java.util.ArrayList;

public interface UniversityDAO
{
    public ArrayList selectUniversityByUsername(String targetUsername);
    public ArrayList selectUniversityByProfileId(String targetProfileId);
    public String applyForAdmission(StudentBean studentBean);
}
