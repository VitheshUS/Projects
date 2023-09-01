package DOB_class;

import java.util.Map;

public class pg_dob {
    String address;
    String available;
    String description;
    String distance;
    String latitude;
    String longitude;
    String rent;
    String profile;
    Map<String,String>gallary;

    public pg_dob(String address, String available, String description, String distance, String latitude, String longitude, String rent, Map<String, String> gallary) {
        this.address = address;
        this.available = available;
        this.description = description;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rent = rent;
        this.gallary = gallary;
    }
    public pg_dob(String address, String available, String description, String distance, String latitude, String longitude, String rent) {
        this.address = address;
        this.available = available;
        this.description = description;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rent = rent;
        this.gallary = gallary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public Map<String, String> getGallary() {
        return gallary;
    }

    public void setGallary(Map<String, String> gallary) {
        this.gallary = gallary;
    }
}
