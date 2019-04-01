package DAO;

import Controller.PostController;
import Controller.UserController;
import Model.ImageBean;
import Model.UserBean;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import static org.primefaces.component.imagecropper.ImageCropper.PropertyKeys.image;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class ImageDAOImpl implements ImageDAO
{

    private ArrayList resultList;
    private Connection DBConn = null;
    private String myDB = "jdbc:derby://10.110.10.26/atapadi_spring2018_LinkedUAppDB";
    private String driver = "org.apache.derby.jdbc.ClientDriver";
    private ImageBean targetImage;
    private int imageId;
    //private String source;

    public void connect2DB()
    {
        DBHelper.loadDriver(driver);
        DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
    }

    @Override
    public StreamedContent getProfileImage(UserBean user)
    {
        StreamedContent image = null;
        try
        {
            connect2DB();
            String insert = "SELECT image FROM IMAGES WHERE IMAGEID = " + user.getProfilePictureID();
            System.out.println("IMAGEDAOIMPL: " + insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert);
//          stmt.setBinaryStream(1, file.getInputstream());
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                image = new DefaultStreamedContent(new ByteArrayInputStream(rs.getBytes(1)));
            }
            DBConn.close();
        } 
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        
        return image;
    }

    @Override
    public int createImage(UploadedFile file, String username)
    {
        int rowCount = 0;
        int imgID = -1;
        UserController userController = new UserController();
        try
        {
            connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "INSERT INTO IMAGES VALUES (default, ?, '" + username + "')";
            System.out.println("IMAGEDAOIMPL: " + insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setBinaryStream(1, file.getInputstream());
            rowCount = stmt.executeUpdate();
            if (rowCount == 1)
            {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next())
                {
                    imgID = rs.getInt(1);
                }

                userController.setProfilePictureId(imgID, username);
                //System.out.println(imgID);
            }

            DBConn.close();
        } 
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return imgID;
    }

    @Override
    public StreamedContent selectImageByImageId(int targetImageId)
    {
        StreamedContent image = null;
        String selectString = "SELECT image FROM itkstu.images "
                + "WHERE imageId = " + targetImageId;
        System.out.println("IMAGEDAOIMPL: " + selectString);
        
        try
        {
            connect2DB();
            PreparedStatement stmt = DBConn.prepareStatement(selectString);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                image = new DefaultStreamedContent(new ByteArrayInputStream(rs.getBytes(1)));
                //System.out.println("image done");
            }
            DBConn.close();
        } 
        catch (Exception e)
        {
            System.err.println("ERROR: SELECT image BY IMAGEID FAILED.");
            System.err.println("TARGET: " + targetImageId);
            e.printStackTrace();
            //image = new DefaultStreamedContent();
        }

        return image;
    }

    public List<StreamedContent> selectAllImagesByUsername(UserBean user)
    {
        List<StreamedContent> images = new ArrayList<>();
        String selectString = "SELECT image FROM itkstu.images "
                + "WHERE USERNAME = '" + user.getUsername() + "'";
        System.out.println("IMAGEDAOIMPL: " + selectString);
        try
        {
            connect2DB();
            PreparedStatement stmt = DBConn.prepareStatement(selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            while (rs.next())
            {
                images.add(new DefaultStreamedContent(new ByteArrayInputStream(rs.getBytes(1))));
            }

            DBConn.close();
        } 
        catch (Exception e)
        {
            System.err.println("ERROR: SELECT image BY IMAGEID FAILED.");
            System.err.println("TARGET: " + user.getUsername());
            e.printStackTrace();
        }
        return images;
    }

}
