package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.admin.createAdminPage;
import page_locator.admin.create_banner;
import page_locator.admin.editAdminPage;

public class create_bannerTest {
    int testcase;
    String title;

    public create_bannerTest(int testcase, String title) {
        this.testcase = testcase;
        this.title = title;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            using.login_admin();
            createAdminPage account_admin = new createAdminPage(driver);
            account_admin.setting.click();
            using.waitForPageLoaded();
            editAdminPage get = new editAdminPage(driver);
            create_banner create_Banner = new create_banner(driver);
            create_Banner.naviga.click();
            using.waitForPageLoaded();
            if (get.verify_title("Banner quảng cáo")) {
                create_Banner.create_btn.click();
                Thread.sleep(1000);
                create_bannerTest[] data = {
                        new create_bannerTest(1, ""),
                        new create_bannerTest(2, "title"),
                        new create_bannerTest(3, "Giảm giá test"),
                        new create_bannerTest(4, "Giảm giá test"),
                };
                for (int i = 0; i < data.length; i++) {
                    System.out.println("=========================");
                    System.out.println("Testcase: " + data[i].testcase);
                    create_Banner.title_input.sendKeys(data[i].title);
                    using.button.click();
                    Thread.sleep(1200);
                    String noti = using.messgaeError_tagline();
                    switch (noti) {
                        case "Bạn chưa nhập tiêu đề banner":
                            System.out.println(noti);
                            create_Banner.print();
                            break;
                        case "Bạn chưa tải ảnh lên":
                            System.out.println(noti);
                            create_Banner.print();
                            create_Banner.uploadImage("//span[contains(text(),'Tải ảnh lên')]", "xpath");
                            break;
                        case "Bạn chưa chọn trang hiển thị":
                            System.out.println(noti);
                            create_Banner.print();
                            create_Banner.select();
                            break;
                        default:
                            noti = using.messgaeError_tagline();
                            if (noti.equals("Đã cập nhật banner thành công!")) {
                                System.out.println(noti);
                                System.out.println("PASSED");
                                System.out.println("=========================");
                            } else {
                                System.out.println("FAILED");
                                System.out.println("=========================");
                            }
                            break;
                    }
                    Thread.sleep(1200);
                }
            } else {
                System.out.println("Page title is wrong...");
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
