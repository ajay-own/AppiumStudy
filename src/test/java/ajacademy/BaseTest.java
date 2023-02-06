package ajacademy;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public AndroidDriver driver;
	AppiumDriverLocalService service;
	
	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException {
		int portNumber =4723;
		String IPAddress= "127.0.0.1";
		
		//code to start server
		File mainJavascriptfile = new File ("C:\\Users\\Ajay\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
		service =new AppiumServiceBuilder().withAppiumJS(mainJavascriptfile).withIPAddress(IPAddress)
				.usingPort(portNumber).build();
		
		//xpath, id, accessibilityId, classname, androidUIAutomator->supported by android
		service.start();
		String address ="http://"+IPAddress+":"+String.valueOf(portNumber);
		System.out.println(address);
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("AjayPhone");
		options.setApp("E:\\eclipse-workspace-Appium1\\AppFirst\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		driver = new AndroidDriver(new URL(address),options);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}

