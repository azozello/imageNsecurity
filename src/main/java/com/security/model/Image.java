package com.security.model;

import java.io.Serializable;

public class Image implements Serializable {
    private String name;
    private String type;
    private String folder;

    public Image(){}

    public Image(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Image(String name, String type, String folder) {
        this.name = name;
        this.type = type;
        this.folder = folder;
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", folder='" + folder + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
