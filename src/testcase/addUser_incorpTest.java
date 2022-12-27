package testcase;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.addUser_incorpPage;
import page_locator.edit_incorpPage;
import common.baseSetup;
import excelHelpers.excelHelpers;

public class addUser_incorpTest {

    public static void main(String[] args) {
        try {
            excelHelpers excel = new excelHelpers();
            excel.setExcelSheet("AddUserinCorp");

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            edit_incorpPage get = new edit_incorpPage(driver);
            addUser_incorpPage addUser = new addUser_incorpPage(driver);

            using.login();
            get.clickNavigation();
            get.find.click();
            addUser.naviga_user();
            Thread.sleep(1200);

            if (addUser.verifyTitle()) {

                System.out.println("Testcase: " + excel.getCellData("TCID", 1));
                addUser.email.sendKeys(excel.getCellData("email", 1));
                addUser.button.click();

                String noti = using.messgaeError_tagline();

                if (noti.equals("Địa chỉ email này đã có trong tổ chức của bạn.")) {

                    addUser.print();

                    Thread.sleep(1500);

                    for (int i = 2; i < 7; i++) {
                        System.out.println("=========================");

                        System.out.println("Testcase: " + excel.getCellData("TCID", i));
                        addUser.createUser(excel.getCellData("email", i), excel.getCellData("lastname", i),
                                excel.getCellData("firstname", i));
                        Thread.sleep(1000);

                        noti = using.messgaeError_tagline();

                        switch (noti) {
                            case "Địa chỉ email không đúng định dạng!":
                                addUser.print();
                                break;

                            case "Bạn chưa nhập họ và tên cho tài khoản!":
                                addUser.print();
                                break;

                            case "Bạn chưa nhập địa chỉ email!":
                                addUser.print();
                                break;

                            default:
                                if (noti.equals("Đang gửi email thông tin tài khoản...")) {
                                    using.passed();
                                } else {
                                    using.failed();
                                }
                                break;
                        }
                        Thread.sleep(1200);
                    }
                } else {
                    using.failed();
                }
            } else {
                System.out.println("Verify title popup is invalid...");
                using.failed();
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
