package com.security.service;

import com.security.model.pojo.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Test
    public void saveAndLoadTest(){
        ArrayList<Image> before = imageService.loadImageNames();
        imageService.saveImageNames(before);
        ArrayList<Image> after = imageService.loadImageNames();
        for (int i=0;i<before.size();i++){
            assertEquals(before.get(i).getFolder(),after.get(i).getFolder());
            assertEquals(before.get(i).getName(),after.get(i).getName());
            assertEquals(before.get(i).getType(),after.get(i).getType());
        }
    }

    @Test
    public void imageURLBuildTest(){
        Image testImage = new Image("image","jpg","user");
        String URLExpected = "src/main/resources/photo/user/image.jpg";
        assertEquals(URLExpected,imageService.buildURL(testImage));
    }

    @Test
    public void getImagesByFolderTest(){
        String folder = "user";
        ArrayList<Image> testList = imageService.getImagesByFolder(folder);
        for (Image i : testList){
            assertEquals(i.getFolder(), folder);
        }
    }

    @Test
    public void addImage(){
        Image image = new Image("test","test","test");
        imageService.addImage(image);
        assertTrue(imageService.getAllImages().contains(image));
    }

    @Test
    public void deleteImage(){
        ArrayList<Image> save = imageService.loadImageNames();
        Image image = save.get(0);
        imageService.deleteImage(image);
        assertTrue(imageService.loadImageNames().contains(image));
        imageService.saveImageNames(save);
    }

    @Test
    public void getImageByName(){
        Image had = imageService.getAllImages().get(0);
        Image loaded = imageService.getImageByName(had.getName());
        assertEquals(had.getName(),loaded.getName());
        assertEquals(had.getType(),loaded.getType());
        assertEquals(had.getFolder(),loaded.getFolder());
    }
}
