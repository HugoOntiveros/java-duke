import edu.duke.*;
import java.io.File;

public class BatchInversion {
  public ImageResource turnNegative (ImageResource img) {
    ImageResource negativeImg = new ImageResource(img.getWidth(), img.getHeight());
    for (Pixel px : negativeImg.pixels()) {
      Pixel op = img.getPixel(px.getX(), px.getY());
      px.setRed(255-px.getRed());
      px.setGreen(255-px.getGreen());
      px.setBlue(255-px.getBlue());
    }
    return negativeImg;
  }

  public void testBatchInversion() {
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      ImageResource img = new ImageResource(f);
      String imgName = img.getFileName();
      System.out.println("Proccessing " + imgName);
      imgName = "inv-" + imgName;
      img = turnNegative(img);
      img.setFileName(imgName);
      System.out.println("Saving as " + imgName);
      img.save();
    }
  }
}