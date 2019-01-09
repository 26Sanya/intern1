package Model;

/**
 * Created by James Sarkar.
 */

public class Exercise {

    private String url;

    private String name;

    public Exercise(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getImageId() {
        return url;
    }


    public void setImageId(String url) {
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
