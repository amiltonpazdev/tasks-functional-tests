package br.com.tasks.functional;

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


public class TasksTest {

	
	public WebDriver acessarAplicacao() throws MalformedURLException {

		URL url = URI.create("http://192.168.192.15:4444/wd/hub").toURL();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		
		WebDriver driver = new RemoteWebDriver(url,cap);
		driver.navigate().to("http://192.168.192.15:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			// clicar no botão
						driver.findElement(By.id("addTodo")).click();
					//escrever a descrição
						driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
					//escrever a data
						driver.findElement(By.id("dueDate")).sendKeys("14/12/2024");
					//clicar em salvar
						driver.findElement(By.id("saveButton")).click();
					//validar mensagem de sucesso
						String mensagem = driver.findElement(By.id("message")).getText();
						Assert.assertEquals("Success!", mensagem);
		} finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			// clicar no botão
						driver.findElement(By.id("addTodo")).click();
					
					//escrever a data
						driver.findElement(By.id("dueDate")).sendKeys("14/12/2024");
					//clicar em salvar
						driver.findElement(By.id("saveButton")).click();
					//validar mensagem de sucesso
						String mensagem = driver.findElement(By.id("message")).getText();
						Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefacomDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			// clicar no botão
						driver.findElement(By.id("addTodo")).click();
					
					//escrever a descrição
					driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
					
					//escrever a data
						driver.findElement(By.id("dueDate")).sendKeys("14/12/2023");
					//clicar em salvar
						driver.findElement(By.id("saveButton")).click();
					//validar mensagem de sucesso
						String mensagem = driver.findElement(By.id("message")).getText();
						Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			// clicar no botão
						driver.findElement(By.id("addTodo")).click();
					
					//escrever a descrição
					driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
					
					//clicar em salvar
						driver.findElement(By.id("saveButton")).click();
					//validar mensagem de sucesso
						String mensagem = driver.findElement(By.id("message")).getText();
						Assert.assertEquals("Fill the due date", mensagem);
		} finally {
			//fechar o browser
			driver.quit();
		}
		
	}
}
