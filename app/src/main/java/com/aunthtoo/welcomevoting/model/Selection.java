package com.aunthtoo.welcomevoting.model;

/**
 * Created by Aunt Htoo on 12/24/2016.
 */

public class Selection {

    int id;
    String name;
    String birthday;
    int choose;
    String img;



    public Selection() {

    }

    public Selection(int id, String name, String birthday, int choose, String img) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.choose = choose;
        this.img=img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setChoose(int choo) {
        this.choose = choo;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getChoose() {
        return choose;
    }

    public String getImg() {
        return img;
    }
}
