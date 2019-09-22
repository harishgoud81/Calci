package mobile;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;

public class Dialer {

	public static void main(String[] args) throws Exception
	{
		//Get a mobile number
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter a mobile number");
				String mbno=sc.nextLine();
				//Start appium server and form url for it
				Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
				URL u=new URL("http://127.0.0.1:4723/wd/hub");
				//Maintain details base for app and ARD
				DesiredCapabilities dc=new DesiredCapabilities();
				dc.setCapability(CapabilityType.BROWSER_NAME,"");
				dc.setCapability("deviceName","PNXID18120902127");
				dc.setCapability("platformName","Android");
				dc.setCapability("platformVersion","9");
				dc.setCapability("appPackage","com.google.android.dialer");
				dc.setCapability("appActivity","com.google.android.dialer.extensions.GoogleDialtactsActivity");
				
				//Launch app in ARD through appium server
				AndroidDriver driver;
				while(2>1)
				{
					try
					{
					    driver=new AndroidDriver(u,dc);
					    break;
					}
					catch(Exception ex)
					{
						
					}
				}
				//App automation
				try
				{
					Thread.sleep(5000);
					TouchAction ta=new TouchAction(driver);
					ta.tap(ElementOption.point(938, 1811)).perform();
					WebDriverWait wait=new WebDriverWait(driver,20);
					Thread.sleep(5000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='CONTACTS']")));
					for(int i=0;i<mbno.length();i++)
					{
						char d=mbno.charAt(i);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='"+d+"']")));
						driver.findElement(By.xpath("//*[@text='"+d+"'][@index='0']")).click();
					}
					ta.tap(ElementOption.point(535, 1992)).perform();
					Thread.sleep(10000);
					driver.findElement(By.xpath("(//*[@instance='4'])[1]")).click();
					Thread.sleep(9000);
					driver.findElement(By.xpath("//*[content-desc='End call']")).click();
					
			
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			//stop appium server
		    Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		    Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");

				

				
				
				
					 

	}

}
