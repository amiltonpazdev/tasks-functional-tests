package br.com.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;
public class TasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
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
	public void naoDeveSalvarTarefaSemDescricao() {
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
	public void naoDeveSalvarTarefacomDataPassada() {
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
	public void naoDeveSalvarTarefaSemData() {
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
