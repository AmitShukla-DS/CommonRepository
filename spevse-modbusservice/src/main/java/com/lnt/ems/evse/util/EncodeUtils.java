package com.lnt.ems.evse.util;

//import sun.misc.BASE64Encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EncodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(EncodeUtils.class);

    public EncodeUtils() {
        throw new UnsupportedOperationException("Utility class can`t be instantiating");
    }

    /**
     * Encode image to Base64 string
     *
     * @param image The image to Base64 encode
     * @param type  jpeg, bmp, ...
     * @return Base64 encoded string
     */
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            //BASE64Encoder encoder = new BASE64Encoder();
            imageString = "";//encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return imageString;
    }
}
