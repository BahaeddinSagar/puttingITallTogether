package ly.bsagar.gsonpicasso;

public class MovieClass {
    public String ImageURL;
    public String name;
    public String URL;
    public String description;
    public long year;

    public MovieClass(String imageURL, String name, String URL, String description, long year) {
        this.ImageURL = imageURL;
        this.name = name;
        this.URL = URL;
        this.description = description;
        this.year = year;
    }

    public MovieClass() {

    }

    @Override
    public String toString() {
        return "MovieClass{" +
                "Imageurl='" + ImageURL + '\'' +
                ", name='" + name + '\'' +
                ", URL='" + URL + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                '}';
    }
}
