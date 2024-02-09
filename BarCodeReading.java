package codereader.codereader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BarCodeReading {
public static void main(String[] args) throws IOException, NotFoundException {
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.get("https://barcode.tec-it.com/en/?data=ABC-abc-12345");
	String barcodeUrl = driver.findElement(By.xpath("//*[@id=\"infoTarget\"]/div[1]/img")).getAttribute("src");
	System.out.println(barcodeUrl);
	URL url = new URL(barcodeUrl);
	BufferedImage bufferedImage =  ImageIO.read(url);
	LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
	BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
	// decode value from binaryBitmap
	Result result =   new MultiFormatReader().decode(binaryBitmap);
	System.out.println(result.getText());
}
}
