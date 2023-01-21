package Presentation.View.Subviews.PanelFeatures;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class used for the rescaling of an image
 *
 * @author ICE 9
 */

public class ImageFeatures {

    /**
     * Constructor used when configuring a board
     */
    public ImageFeatures(){

    }

    /**
     * Used to resize an image
     * @param path a string containing the path to the image
     * @param width the desired width
     * @param height the desired height
     * @return the newly size image
     */
    public Image resizeImage(String path, int width, int height){
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(path));
            return bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        }catch(IOException e){
            return null;
        }
    }

}
