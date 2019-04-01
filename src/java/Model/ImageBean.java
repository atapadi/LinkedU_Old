package Model;

public class ImageBean
{
    private int imageId;
    private String source;

    public ImageBean(int imageId, String source)
    {
        this.imageId = imageId;
        this.source = source;
    }
    
    public ImageBean()
    {
        
    }
    /**
     * @return the imageId
     */
    public int getImageId()
    {
        return imageId;
    }

    /**
     * @param imageId the imageId to set
     */
    public void setImageId(int imageId)
    {
        this.imageId = imageId;
    }

    /**
     * @return the source
     */
    public String getSource()
    {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source)
    {
        this.source = source;
    }
}