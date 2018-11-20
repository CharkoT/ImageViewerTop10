package charko.tester01.com.imageviewertop10.Data;

/**
 * Created by Charko on 2018. 3. 15..
 */

public class Image {
    private int Id;
    private String FilePath;
    private String date;
    private double Latitude;
    private double Longitude;
    private int Orientation;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public int getOrientation() {
        return Orientation;
    }

    public void setOrientation(int orientation) {
        Orientation = orientation;
    }
}
