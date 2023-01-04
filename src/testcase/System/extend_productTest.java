package testcase.System;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.edit_incorpPage;
import page_locator.extend_productPage;

public class extend_productTest {

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            edit_incorpPage get = new edit_incorpPage(driver);
            extend_productPage extend = new extend_productPage(driver);

            using.login();
            get.clickNavigation();
            get.find.click();
            extend.click_extend();
            extend.continuebtn.click();

            String noti = using.messgaeError_tagline();
            if (noti != null) {
                System.out.println("=========================");
                System.out.println("Testcase: 1");
                System.out.println(noti);
                using.passed();

                extend.usercorrect.sendKeys("1");
                Thread.sleep(1000);
                extend.continuebtn.click();
                Thread.sleep(1000);
                System.out.println("Bạn đang thanh toán: " + extend.price_amount());
                System.out.println("=========================");
                System.out.println("Testcase: 2");
                extend.confirm.click();
                if (extend.get_textdone() != null) {
                    System.out.println(extend.get_textdone());
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
