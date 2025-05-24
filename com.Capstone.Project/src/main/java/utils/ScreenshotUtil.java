package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/screenshots/"));
            Files.copy(src.toPath(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
