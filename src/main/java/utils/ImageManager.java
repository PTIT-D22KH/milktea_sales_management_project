package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.imgscalr.Scalr;

public class ImageManager {
    private static String resourcesPath;
    private static String imagesPath;
    
    public ImageManager(){
        resourcesPath = "/";
        imagesPath = resourcesPath + "images/";
    }
    
    public ImageIcon getImage(String name) {
        try (InputStream is = getClass().getResourceAsStream(imagesPath + name)) {
            if (is != null) {
                BufferedImage bi = ImageIO.read(is);
                return new ImageIcon(bi);
            } else {
                try {
                    URL pathImage = getClass().getResource(imagesPath + name);
                    return new ImageIcon(pathImage);
                } catch (Exception ex) {
                    return new ImageIcon(getClass().getResource(imagesPath + "tra_sua_default.png"));
                }
//                return new ImageIcon(getClass().getResource(imagesPath + "tra_sua_default.png"));
            }
        } catch (Exception e) {
            System.out.println(getClass().getResource(imagesPath + "tra_sua_default.png"));
            return new ImageIcon(getClass().getResource(imagesPath + "tra_sua_default.png"));
        }
    }   
    
    public Icon resizeIcon(ImageIcon source, int width, int height) {
        Image img = source.getImage();
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    public String saveImage(BufferedImage bi, String name) throws IOException {
        // Use a directory outside the JAR for saving images
        String userDir = System.getProperty("user.dir");
        String pathImages = userDir + "/images/";
        String fileName = getUniqueNameFile(name);
        File out = new File(pathImages + fileName);
        String resourceImages = getClass().getResource(imagesPath).getPath();
        File out2 = new File(resourceImages + fileName);
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        BufferedImage resizedImage = resizeImage(bi, 200);
        ImageIO.write(resizedImage, "png", out);
        try {
            ImageIO.write(resizedImage, "png", out2);
        } catch (IOException e) {
            
        }
        
        
        return out.getName();
    }
    
    private BufferedImage resizeImage(BufferedImage source, int targetWidth) {
        try {
            return Scalr.resize(source, targetWidth);
        } catch (Exception e) {
            return source;
        }
    }

    private String getUniqueNameFile(String name) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Timestamp(System.currentTimeMillis()));
        String fileName = String.format("%s-%s.%s", name.length() > 35 ? name.substring(0, 35) : name, timeStamp, "png");
        return fileName;
    }
}