package pracadom.driving_licence_test.model;

public class User {
    private Integer numberOfPoints;


    public User(){
        numberOfPoints = 0;
    }
    public void addPoint(){
        numberOfPoints++;
    }
    public Integer getNumberOfPoints(){
        return numberOfPoints;
    }
}
