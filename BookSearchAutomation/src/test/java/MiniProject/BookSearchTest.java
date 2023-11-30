package MiniProject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookSearchTest {
	private static WebDriver driver;
	public static String baseUrl="https://openlibrary.org/";
	public static List<String> category=new ArrayList<String>();
	public static List<String> count=new ArrayList<String>();
	
	//getting WebDriver
	public void getDriver() {
		driver=DriverSetup.setDriver();
	}
	
	//Opening the URL
	public void getUrl() throws IOException{
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		screenshot("screenshot1");
	}
	
	//Close PopUp
	public void closePopup() throws IOException {
		int count = driver.findElements(By.tagName("iframe")).size();
		if(count>1) {
			driver.switchTo().frame(0);
			driver.findElement(By.xpath("//*[@id='banner-close-image-white']")).click();
			screenshot("screenshot2");
			driver.switchTo().defaultContent();
		}
	}
	
	//Getting category and its count in list
	public void getCategory() throws InterruptedException, IOException{
		List<WebElement> categoryEle=driver.findElements(By.cssSelector("p.category-title"));
		List<WebElement> countEle=driver.findElements(By.cssSelector("p.category-count"));
		for(int i=0;i<countEle.size();i++) {
			if(categoryEle.get(i).getText().equals("")) {
				driver.findElement(By.xpath("//*[@id='categories_carousel']/button[2]")).click();
				screenshot("screenshot3");
				Thread.sleep(1000);
			}
			category.add(categoryEle.get(i).getText());
			count.add(countEle.get(i).getText());
		}
	}
	
	
	//Printing Category and count
	public void printCategoryAndCount() {
		for(int j=0;j<count.size();j++) {
			System.out.println(category.get(j)+" ----> "+count.get(j));
		}
	}
	
	//Selecting subjects menu from browsers Drop-down
	public void selectSubjects() throws Exception {
		WebElement browser=driver.findElement(By.className("down-arrow"));
		browser.click();
		screenshot("screenshot4");
		Thread.sleep(3000);
		WebElement subjects= driver.findElement(By.xpath("//*[@id='header-bar']/ul[1]/li[2]/div/details/div/ul/li/a"));
		subjects.click();
		screenshot("screenshot5");
	}
	
	//Navigating to other page by clicking see more link
	public void seeMoreLink() throws IOException {
		driver.findElement(By.xpath("//*[@id='subjectsPage']/ul[13]/li[9]/a")).click();
		
		screenshot("screenshot6");
	}
	
	//validating Tamil books Count
	public void validateTamilBookCount() {
		String count=driver.findElement(By.xpath("//*[@id='contentBody']/ul/li[38]/span/b")).getText();
		String[] counttxt= count.split(" ");
		String[] bookcount=counttxt[0].split(",");
		int tamilbookcount=Integer.parseInt(bookcount[0]+bookcount[1]);
		if(tamilbookcount>1000) {
			System.out.println("Tamil books are greater than 1000");
		}
		else {
			System.out.println("Tamil books are less than 1000");
		}
	}
	
	//Capturing screenshot
	public void screenshot(String filename) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File(System.getProperty("user.dir")+"\\Screenshot\\"+filename+".png");
		FileUtils.copyFile(src, trg);
	}
	
	//Close Browser
	public void closeBrowser() {
		driver.quit();
	}
	
	//Main Function
	public static void main(String[] args) throws Exception{
		
		BookSearchTest bs=new BookSearchTest();
		bs.getDriver();
		bs.getUrl();
		bs.closePopup();
		bs.getCategory();
		bs.printCategoryAndCount();
		bs.selectSubjects();
		bs.seeMoreLink();
		bs.validateTamilBookCount();
		ExcelUtils.excel(category, count);
		bs.closeBrowser();
	}
}
