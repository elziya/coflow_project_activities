package com.technokratos.util.email;

import org.xhtmlrenderer.swing.Java2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;
 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.technokratos.consts.CoflowCommonConstants.IMAGE_ATTACHMENT_QUALITY;

public class HtmlToImageUtil {

    public static File convertXhtmlToImage(String html, String inputFilename, String outputFilename, int widthImage, int heightImage) throws IOException {
        try (FileWriter writer = new FileWriter(inputFilename)) {
            writer.write(html);
            writer.flush();
        }

        final File f = new File(inputFilename);
        final Java2DRenderer renderer = new Java2DRenderer(f, widthImage, heightImage);
        final BufferedImage img = renderer.getImage();
        final FSImageWriter imageWriter = new FSImageWriter();

        imageWriter.setWriteCompressionQuality(IMAGE_ATTACHMENT_QUALITY);
        imageWriter.write(img, outputFilename);

        return new File(outputFilename);
    }
}

