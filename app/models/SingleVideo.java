package models;

/**
 * Created by zjh on 15-1-1.
 */
public class SingleVideo {
    private Integer id;
    private String title;
    private String authorname;
    private String video_url;
    private String image_url;

    public SingleVideo(Integer id, String title, String authorname, String video_url, String image_url) {
        this.id = id;
        this.title = title;
        this.authorname = authorname;
        this.video_url = video_url;
        this.image_url = image_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
