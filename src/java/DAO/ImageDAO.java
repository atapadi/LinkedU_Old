package DAO;

import Model.ImageBean;
import Model.UserBean;
import java.util.ArrayList;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public interface ImageDAO
{
    public int createImage(UploadedFile file, String username);
    public StreamedContent getProfileImage(UserBean user);
    public StreamedContent selectImageByImageId(int targetImageId);
}
