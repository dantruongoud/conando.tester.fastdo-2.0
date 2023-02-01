package testcase.System;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.incorpPage;
import page_locator.rechargePage;
import common.baseSetup;

public class rechargeTest {

    int testcase;
    String amuount;

    public rechargeTest(int testcase, String amuount) {
        this.testcase = testcase;
        this.amuount = amuount;
    }

    public static void main(String[] args) {
        try {

            rechargeTest[] list_dataTest = {
                    new rechargeTest(1, "0"),
                    new rechargeTest(2, "5000000")
            };

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage index = new SignInPage(driver);
            rechargePage rechargepage = new rechargePage(driver);
            incorpPage incorp = new incorpPage(driver);

            index.login();

            rechargepage.recharge.click();

            if (rechargepage.verify_title_modal()) {
                for (int i = 0; i < list_dataTest.length; i++) {
                    System.out.println("=========================");

                    System.out.println("Testcase: " + list_dataTest[i].testcase);
                    rechargepage.recharge(list_dataTest[i].amuount);
                    Thread.sleep(1000);

                    String noti = incorp.messgaeError();
                    switch (noti) {
                        case "Chưa nhập số tiền cần nạp":
                            System.out.println(noti);
                            rechargepage.back.click();
                            index.passed();
                            break;
                        case "Đã gửi yêu cầu nạp tiền, số dư sẽ được cộng sau khi yêu cầu được phê duyệt":
                            System.out.println(noti);
                            index.passed();
                            break;
                        default:
                            index.failed();
                            break;
                    }
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
