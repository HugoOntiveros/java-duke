import edu.duke.*;
import java.io.File;

public class BatchGrayscale{
  public ImageResource turnGrayscale (ImageResource img) {
    ImageResource grayImg = new ImageResource(img.getWidth(), img.getHeight());
    for (Pixel px : grayImg.pixels()) {
      Pixel op = img.getPixel(px.getX(), px.getY());
      int average = (op.getRed() + op.getGreen() + op.getBlue()) / 3;
      px.setRed(average);
      px.setGreen(average);
      px.setBlue(average);
    }
    return grayImg;
  }

  public void testBatchGrayscale() {
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      ImageResource img = new ImageResource(f);
      String imgName = img.getFileName();
      System.out.println("Proccessing " + imgName);
      imgName = "gray-" + imgName;
      img = turnGrayscale(img);
      img.setFileName(imgName);
      System.out.println("Saving as " + imgName);
      img.save();
    }
  }
}