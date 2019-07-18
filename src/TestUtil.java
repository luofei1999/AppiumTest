import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

public class TestUtil {
    private AppiumDriver driver;

    @BeforeClass
    public void setup() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(CapabilityType.BROWSER_NAME, "");
        cap.setCapability("platformName", "Android"); //指定测试平台
        cap.setCapability("deviceName", "a0b021697d13"); //指定测试机的ID,通过adb命令`adb devices`获取
        cap.setCapability("platformVersion", "8.1.0");

        //将上面获取到的包名和Activity名设置为值
        cap.setCapability("appPackage", "com.android.calculator2");
        cap.setCapability("appActivity", "com.android.calculator2.Calculator");

        //A new session could not be created的解决方法
        cap.setCapability("appWaitActivity", "com.android.calculator2.Calculator");
        //每次启动时覆盖session，否则第二次后运行会报错不能新建session
        cap.setCapability("sessionOverride", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }

    @Test
    public void plus() {

        String[] act = {"com.google.android.calculator:id/digit_1","com.google.android.calculator:id/op_add","com.google.android.calculator:id/digit_8","com.google.android.calculator:id/eq"};

        runM(act);

    }

    private void runM(String[] act) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (String actiong :
                act) {
            driver.findElementById(actiong).click();
        }

    }

}
