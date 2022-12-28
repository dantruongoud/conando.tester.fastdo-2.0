package testcase;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.edit_incorpPage;
import page_locator.upgrade_productPage;

public class upgrade_productTest {
    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            edit_incorpPage get = new edit_incorpPage(driver);
            upgrade_productPage upgrade = new upgrade_productPage(driver);

            using.login();
            get.clickNavigation();
            get.find.click();
            upgrade.click_upgrade();
            upgrade.button.click();

            String noti = using.messgaeError_tagline();
            if (noti != null) {
                System.out.println("=========================");
                System.out.println("Testcase: 1");
                System.out.println(noti);
                using.passed();
                upgrade.usercorrectInput();
                Thread.sleep(1000);

                upgrade.button.click();
                Thread.sleep(1000);

                System.out.println("Bạn đang thanh toán: " + upgrade.price_amount());
                upgrade.button.click();
                
                System.out.println("=========================");
                System.out.println("Testcase: 2");

                Thread.sleep(1000);
                if (upgrade.get_textdone() != null) {
                    System.out.println(upgrade.get_textdone());
                    using.passed();
                } else {
                    using.failed();
                }
            } else {
                using.failed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
