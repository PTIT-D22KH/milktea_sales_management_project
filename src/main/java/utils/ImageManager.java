/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.imgscalr.Scalr;

/**
 *
 * @author P51
 */
public class ImageManager {
    private static String resourcesPath;
    private static String imagesPath;
    
    public ImageManager() {
        resourcesPath = "/";
        imagesPath = resourcesPath + "images/";
    }
    
    
    public ImageIcon getImage(String name) {
        try {
            URL pathImage = getClass().getResource(imagesPath + name);
            return new ImageIcon(pathImage);
        } catch (Exception e) {
            return new ImageIcon(getClass().getResource(imagesPath + "tra_sua_default.png"));
        }
        
    }
    
    public String saveImage(BufferedImage bi, String name ) throws IOException {
        String pathImages = getClass().getResource(imagesPath).getPath();
        String fileName = getUniqueNameFile(name);
        File outFile = new File(pathImages + fileName);
        BufferedImage resizedImage = resizeImage(bi, 200);
        ImageIO.write(resizedImage, "png", outFile);
        return outFile.getName();
    }

    private String getUniqueNameFile(String name) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Timestamp(System.currentTimeMillis()));
        String fileName = String.format("%s-%s.%s", name.length() > 35 ? name.substring(0, 35) : name, timeStamp, "png");
        return fileName;
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private BufferedImage resizeImage(BufferedImage source, int targetWidth) {
        try {
            return Scalr.resize(source, targetWidth);
        } catch (Exception e) {
            return source;
        }
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
