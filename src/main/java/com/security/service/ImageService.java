package com.security.service;

import com.security.model.Image;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

@Service
public class ImageService {

    public static ArrayList<Image> allImages = new ArrayList<>();
    private final String URL = "src/main/resources/photo/";

    /**
     * Serializes image names into 'names' file in the root of photo directory
     */
    public ArrayList<Image> loadImageNames(){
        ArrayList<Image> result = new ArrayList<>();
        File imageList = new File(URL+"names");
        try (FileInputStream fis = new FileInputStream(imageList);
             ObjectInputStream ois = new ObjectInputStream(fis)){
            result = (ArrayList) ois.readObject();
        } catch (FileNotFoundException fnfe){
            System.out.println("Something wrong with file");
            fnfe.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            System.out.println("Something wrong with class cast");
            cnfe.printStackTrace();
        }
        return result;
    }

    /**
     * Loads image names from 'names' file in the root of photo directory
     */
    public void saveImageNames(ArrayList<Image> names){
        File imageList = new File(URL+"names");
        try (FileOutputStream fis = new FileOutputStream(imageList);
             ObjectOutputStream ois = new ObjectOutputStream(fis)){
            ois.writeObject(names);
        } catch (FileNotFoundException fnfe){
            System.out.println("Something wrong with file");
            fnfe.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    /**
     * Builds from Image class`s object URL to use on frontend
     * @param image
     * @return URL
     */
    public String buildURL(Image image){
        return URL+image.getFolder()+"/"+image.getName()+"."+image.getType();
    }

    /**
     *Returns all photos from folder => from some user
     */
    public ArrayList<Image> getImagesByFolder(String folder){
        ArrayList<Image> result = new ArrayList<>();
        for (Image i : allImages){
            if (i.getFolder().equals(folder)) result.add(i);
        }
        return result;
    }

    public void addImage(Image image){
        allImages.add(image);
    }

    public ArrayList<Image> getAllImages() {
        return allImages;
    }
}
