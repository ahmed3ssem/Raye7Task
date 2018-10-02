
/**
 * Class Model to get Name , Date , Image for each new
 */

package com.example.a7medassem.raye7task.Model;

public class favoriteModel {

    private String Name,Date ,Image;

    public favoriteModel() {
    }

    public favoriteModel(String name, String date, String image) {
        Name = name;
        Date = date;
        Image = image;
    }

    public favoriteModel(String name, String date, String image, String URL) {
        Name = name;
        Date = date;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
