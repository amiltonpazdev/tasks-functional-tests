package br.com.tasks.functional.prod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {

	@Test
	public void healthCheck() throws MalformedURLException {
		URL url = URI.create("http://192.168.192.15:4444/wd/hub").toURL();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(url,cap);
		
		try {			
			driver.navigate().to("http://192.168.192.15:9999/tasks");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			String versao = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(versao.startsWith("build"));
		}finally {
			driver.quit();
		}		
		
	}
}
