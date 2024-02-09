package codereader.codereader;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadingQRCode {
	public static void main(String[] args) throws IOException, NotFoundException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://testsigma.com/blog/qr-code-testing/");
		String qrcodeUrl =  driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[1]/div[2]/div[1]/div[4]/div/div/div/div[2]/figure/img")).getAttribute("src");
		URL url = new URL(qrcodeUrl);
		// read url in image format
		BufferedImage bufferedImage =  ImageIO.read(url);
		// to get exact image from bufferedimage
		LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
		// to extract BinaryBitmap
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
		// decode value from binaryBitmap
		Result result =   new MultiFormatReader().decode(binaryBitmap);
		
		System.out.println(result.getText());
		
	}

}
