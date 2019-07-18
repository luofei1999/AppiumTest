import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

public class TestUtil {
    private AppiumDriver driver;
    //先执行的方法
    @BeforeClass
    public void setup() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        //指定浏览器名称
        cap.setCapability(CapabilityType.BROWSER_NAME, "");
        //指定测试平台
        cap.setCapability("platformName", "Android");
        //指定测试机的ID,通过adb命令`adb devices`获取
        cap.setCapability("deviceName", "a0b021697d13");
        //测试机的Android 版本
        cap.setCapability("platformVersion", "8.1.0");
        //将获取到的包名和Activity名设置为值
        cap.setCapability("appPackage", "com.android.calculator2");
        cap.setCapability("appActivity", "com.android.calculator2.Calculator");

        //A new session could not be created的解决方法
        cap.setCapability("appWaitActivity", "com.android.calculator2.Calculator");
        //每次启动时覆盖session，否则第二次后运行会报错不能新建session
        cap.setCapability("sessionOverride", true);
        //和测试机进行连接
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }
    //测试时执行的方法
    @Test
    public void plus() {
        //准备好需要测试的控件ID
        String[] act = {"com.google.android.calculator:id/digit_1","com.google.android.calculator:id/op_add","com.google.android.calculator:id/digit_8","com.google.android.calculator:id/eq"};
        //传入封装好的方法中进行自动化点击测试
        runM(act);

    }

    private void runM(String[] act) {

        try {
            //如果不等待一段时间，可能会因为APP初始化的时间关系，点击事件被提前消费了
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //遍历所有的需要自动化测试控制，并执行点击操作
        for (String actiong :
                act) {
            driver.findElementById(actiong).click();
        }

    }

}
