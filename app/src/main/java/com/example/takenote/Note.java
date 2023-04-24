package com.example.takenote;

import java.io.Serializable;

public class Note implements Serializable {
    private String title;
    private String type;
    private String history;
    private String decscrption;
    private  int Id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Note(String title, String type, String history, String decscrption) {
        this.title = title;
        this.type = type;
        this.history = history;
        this.decscrption = decscrption;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", history='" + history + '\'' +
                ", decscrption='" + decscrption + '\'' +
                ", id=" + Id +
                '}';
    }

    public Note(String title, String Type, String decscrption) {
        this.title = title;
        this.type = Type;
        this.decscrption = decscrption;
    }

    public Note() {
    }

    public Note(String title, String type, String history, String decscrption, int id) {
        this.title = title;
        this.type = type;
        this.history = history;
        this.decscrption = decscrption;
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public Note(String title, String history, String decscrption, int imgRes) {
        this.title = title;
        this.history = history;
        this.decscrption = decscrption;
        this.Id = imgRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getDecscrption() {
        return decscrption;
    }

    public void setDecscrption(String decscrption) {
        this.decscrption = decscrption;
    }


}
