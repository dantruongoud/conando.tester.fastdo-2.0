package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import excelHelpers.excelHelpers;
import page_locator.SignInPage;
import page_locator.admin.createAdminPage;
import page_locator.admin.create_banner;
import page_locator.admin.editAdminPage;

public class create_bannerTest {

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            createAdminPage account_admin = new createAdminPage(driver);
            editAdminPage get = new editAdminPage(driver);
            create_banner create_Banner = new create_banner(driver);
            excelHelpers excel = new excelHelpers();
            excel.setExcelSheet("admin - createBanner");

            using.login_admin();
            account_admin.setting.click();
            using.waitForPageLoaded();

            create_Banner.naviga.click();
            using.waitForPageLoaded();

            if (get.verify_title("Banner quảng cáo")) {
                create_Banner.create_btn.click();
                Thread.sleep(1000);

                for (int i = 1; i < 5; i++) {
                    System.out.println("=========================");
                    System.out.println("Testcase: " + excel.getCellData("TCID", i));
                    create_Banner.title_input.sendKeys(excel.getCellData("title", i));
                    using.button.click();
                    Thread.sleep(1200);

                    String noti = using.messgaeError_tagline();

                    switch (noti) {
                        case "Bạn chưa nhập tiêu đề banner":
                            create_Banner.print();
                            break;
                        case "Bạn chưa tải ảnh lên":
                            create_Banner.print();
                            create_Banner.uploadImage("//span[contains(text(),'Tải ảnh lên')]", "xpath");
                            break;
                        case "Bạn chưa chọn trang hiển thị":
                            create_Banner.print();
                            create_Banner.select();
                            break;
                        default:
                            if (noti.equals("Đã cập nhật banner thành công!")) {
                                using.passed();
                            } else {
                                using.failed();
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
