
/**
 * Class Model to get Name , Date , Image for each new
 */

package com.example.a7medassem.raye7task.Model;

public class newsModel {

    private String Name , Date , Image;

    public newsModel() {
    }

    public newsModel(String name, String date, String image) {
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
