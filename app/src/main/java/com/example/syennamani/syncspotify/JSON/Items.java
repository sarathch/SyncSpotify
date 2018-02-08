package com.example.syennamani.syncspotify.JSON;

/**
 * Created by syennamani on 2/8/2018.
 */

public class Items
{
    private String id;

    private String name;

    private Images[] images;

    private String type;

    private String album_type;

    private String uri;

    private String href;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Images[] getImages ()
    {
        return images;
    }

    public void setImages (Images[] images)
    {
        this.images = images;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getAlbum_type ()
    {
        return album_type;
    }

    public void setAlbum_type (String album_type)
    {
        this.album_type = album_type;
    }

    public String getUri ()
    {
        return uri;
    }

    public void setUri (String uri)
    {
        this.uri = uri;
    }

    public String getHref ()
    {
        return href;
    }

    public void setHref (String href)
    {
        this.href = href;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", name = "+name+", images = "+images+", type = "+type+", album_type = "+album_type+", uri = "+uri+", href = "+href+"]";
    }
}
