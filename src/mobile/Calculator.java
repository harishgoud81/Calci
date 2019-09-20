package mobile;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.management.DescriptorRead;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class Calculator
{
	public static void main(String[] args) throws Exception
	{
		
	
Scanner sc=new Scanner(System.in);
System.out.println("enter a number");
String x=sc.nextLine();
System.out.println("Enter a number");
String y=sc.nextLine();
DesiredCapabilities dc =new DesiredCapabilities();
dc.setCapability(CapabilityType.BROWSER_NAME, "");
dc.setCapability("deviceName","emulator-5554");
dc.setCapability("platformName", "Android");
dc.setCapability("platformVersion", "4.2.2");
dc.setCapability("appPackage", "com.android.calculator2");
dc.setCapability("appActivity", "com.android.calculator2.Calculator");
//start appiumserver
Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
URL u=new URL("http://0.0.0.0:4723/wd/hub");
AndroidDriver driver;
while(2>1)
{
	try
	{
		driver=new AndroidDriver<>(u,dc);
		break;
	}
	catch(Exception ex)
	{
		}


}
//appautomation
try
{
	WebDriverWait w=new WebDriverWait(driver, 20);
	for(int i=0;i<x.length();i++)
	{
		char d=x.charAt(i);
		if(d=='-')
		{
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='minus]")));
			driver.findElement(By.xpath("//*[@content-desc='minus]"));
		}
		else
		{
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text=' "+d+" ']")));
			driver.findElement(By.xpath("//*[@text=' \"+d+\" ']")).click();
		}
	}


	for(int i=0;i<x.length();i++)
	{
		char d=y.charAt(i);
		if(d=='-')
		{
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='minus]")));
			driver.findElement(By.xpath("//*[@content-desc='minus]"));
		}
		else
		{
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text=' "+d+" ']")));
			driver.findElement(By.xpath("//*[@text=' \"+d+\" ']")).click();
		}
	}
	//click=
w.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='equals']")));
driver.findElement(By.xpath("//*[@content-desc='equals']")).click();
//getoutput
String z=driver.findElement(By.xpath("//*[@class='android.widget.Button']")).getAttribute("text");
if(z.contains("minus"))
	
{
	z=z.replace("minus","-");
}
int i1=Integer.parseInt(x);
int i2=Integer.parseInt(y);
int o=Integer.parseInt(z);
if(o==i1+i2)
{
	System.out.println("test passed");
}
else
{
	System.out.println("test failed");
}
driver.close();
}
catch(Exception ex)
{
	System.out.println(ex.getMessage());
}
//stopappium server
Runtime.getRuntime().exec("taskkill /F /IM node.exe");
Runtime.getRuntime().exec("taskkill /F ?IM cmd.exe");
}
}