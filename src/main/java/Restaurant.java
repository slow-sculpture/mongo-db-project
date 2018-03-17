import java.util.List;

public class Restaurant {
    private String borough;
    private String cuisine;
    private String name;
    private List<Grades> grades;

    public Restaurant() {
    }

    public Restaurant(String borough, String cuisine, String name, List<Grades> grades) {
        this.borough = borough;
        this.cuisine = cuisine;
        this.name = name;
        this.grades = grades;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }
}
