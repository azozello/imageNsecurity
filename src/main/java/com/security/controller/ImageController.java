package com.security.controller;

import com.security.model.Image;
import com.security.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

@RestController
public class ImageController {

    private ImageService imageService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(value = "/allImagesNames")
    public ArrayList<String> getImage(){
        return new ArrayList<>();
    }

    @RequestMapping(value = "/image/{name}")
    public void getImageByName(@PathVariable String name, HttpServletResponse response){
        Image i = imageService.getImageByName(name);
        String url = imageService.buildURL(i);
        File f = new File(url);
        try (InputStream is = new FileInputStream(f);
             OutputStream os = response.getOutputStream()){
            response.setContentType("image/jpeg");

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes,0,read);
            }
            response.flushBuffer();
        } catch (IOException ioe    ){
            ioe.printStackTrace();
        }
    }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/photo/"+name)));
                stream.write(bytes);
                stream.close();
                Image temp = new Image(name,file.getContentType());
                imageService.addImage(temp);
                imageService.saveImageNames(imageService.getAllImages());
                return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }
}
