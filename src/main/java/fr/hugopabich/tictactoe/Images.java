package fr.hugopabich.tictactoe;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

public class Images {

    private static HashMap<String, BufferedImage> images = new HashMap<>();

    /**
     * Reads all the images contained in the images folder.
     */
    public static void init(){
        try {
            InputStream in = Images.class.getResourceAsStream("/images");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String resource;

            while ((resource = br.readLine()) != null) {
                BufferedImage image = ImageIO.read(Images.class.getResourceAsStream("/images/" + resource));
                images.put(resource.replace(".png", ""), image);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Returns a loaded image or null if not found.
     *
     * @param name Name of the file without .png
     * @return The image or null if not found.
     */
    public static BufferedImage getImage(String name){
        return images.get(name);
    }

}
