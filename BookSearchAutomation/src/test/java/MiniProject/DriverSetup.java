package MiniProject;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
private static WebDriver driver;
	
	//ChromeDriver
	public static WebDriver chromeDriver() {
		driver=new ChromeDriver();
		return driver;
	}
	
	//EdgeDriver
	public static WebDriver edgeDriver() {
		driver=new EdgeDriver();
		return driver;
	}
	
	//FirefoxDriver
	public static WebDriver firefoxDriver() {
		driver=new FirefoxDriver();
		return driver;
	}

	//setting the driver
	public static WebDriver setDriver() {
		Scanner sc=new Scanner(System.in);
		System.out.println("1.ChromeDriver  2.EdgeDriver  3.FirefoxDriver");
		int input=sc.nextInt();
		if(input==1) {
			return chromeDriver();
		}
		else if(input==2) {
			return edgeDriver();
		}
		else if(input==3) {
			return firefoxDriver();
		}
		else {
			System.out.println("Select the appropriate Driver");
			return driver;
		}
	}
	
}
