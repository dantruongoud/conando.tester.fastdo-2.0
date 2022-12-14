package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import excelHelpers.excelHelpers;
import page_locator.SignInPage;
import page_locator.admin.editAdminPage;

public class editAdminTest {

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            editAdminPage edit = new editAdminPage(driver);
            excelHelpers excel = new excelHelpers();
            excel.setExcelSheet("admin - editAdmin");

            using.login_admin();
            edit.naviga.click();
            using.waitForPageLoaded();

            if (edit.verify_title("Thông tin tài khoản")) {
                edit.cleartxt();
                for (int i = 1; i < 10; i++) {

                    System.out.println("=========================");

                    System.out.println("Testcase: " + excel.getCellData("TCID", i));
                    edit.editAdmin(excel.getCellData("firstname", i), excel.getCellData("lastname", i),
                            excel.getCellData("phone", i), excel.getCellData("passold", i),
                            excel.getCellData("passnew", i));

                    using.button.click();
                    Thread.sleep(1200);

                    String noti = using.messgaeError_tagline();
                    switch (noti) {
                        case "Bạn chưa nhập họ và tên cho tài khoản!":
                            edit.print();
                            break;
                        case "Bạn chưa nhập số điện thoại!":
                            edit.print();
                            break; 
                        case "Số điện thoại không đúng!":
                            edit.print();
                            break;
                        case "Bạn chưa nhập mật khẩu cũ!":
                            edit.print();
                            break;
                        case "Bạn chưa nhập mật khẩu mới!":
                            edit.print();
                            break;
                        case "Mật khẩu cũ không chính xác!":
                            edit.print();
                            break;
                        case "Đã cập nhật thông tin thành công!":
                            edit.print();
                            break;
                        default:
                            if (noti.equals("Đã cập nhật thông tin và mật khẩu thành công!")) {
                                using.passed();
                            } else {
                                using.failed();
                            }
                            break;
                    }
                    Thread.sleep(1200);
                }
            } else {
                System.out.println("Title is wrong...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
