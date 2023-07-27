package me.justin.eckenweber;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

public class ImageCompressor {

    public static void compress(File inputFile, boolean keepFileType, float quality, boolean resize, boolean customSize, int customWidth, int customHeight) {
        try {
            System.out.println("Compressing " + inputFile.getName() + "...");
            final String[] splittedInfo = inputFile.getName().split("\\.");
            final String rawType = splittedInfo[splittedInfo.length - 1];//inputFile.getName().split("\\.")[1];
            final String fileType = keepFileType ? rawType : "jpg";
            final String name = inputFile.getName().substring(0, inputFile.getName().length() - (rawType.length() + 1));//inputFile.getName().split("\\.")[0];
            final String location = inputFile.getAbsolutePath().replace(inputFile.getName(), File.separator + "compressed" + File.separator + name + (keepFileType ? "." + rawType : ".jpg")).replace(" ", "_");
            final File folder = new File(inputFile.getAbsolutePath().replace(inputFile.getName(), File.separator + "compressed" + File.separator));
            if (!folder.exists()) folder.mkdir();

            // Load the original image
            BufferedImage originalImage = ImageIO.read(inputFile);

            // Resize the image to half of its original size
            int newWidth = resize ? originalImage.getWidth() / 2 : originalImage.getWidth();
            int newHeight = resize ? originalImage.getHeight() / 2 : originalImage.getHeight();
            int imageType = keepFileType ? originalImage.getType() : BufferedImage.TYPE_INT_RGB;

            if (customSize) {
                newWidth = customWidth;
                newHeight = customHeight;
            }

            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Create a new BufferedImage and draw the resized image onto it
            BufferedImage outputImage = new BufferedImage(newWidth, newHeight, imageType);
            Graphics2D graphics = outputImage.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics.drawImage(resizedImage, 0, 0, null);
            graphics.dispose();

            // Save the resized and compressed image as JPEG
            File outputFile = new File(location);
            ImageOutputStream outputStream = ImageIO.createImageOutputStream(outputFile);
            ImageWriter writer = ImageIO.getImageWritersByFormatName(fileType).next();
            writer.setOutput(outputStream);

            ImageWriteParam imageWriteParam = writer.getDefaultWriteParam();
            imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            imageWriteParam.setCompressionQuality(quality); // You can adjust the compression quality here (0.0f - 1.0f)

            writer.write(null, new javax.imageio.IIOImage(outputImage, null, null), imageWriteParam);

            writer.dispose();
            outputStream.close();

            System.out.println("Compressed!\nOutput File: ./compressed/" + name + "." + fileType);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}