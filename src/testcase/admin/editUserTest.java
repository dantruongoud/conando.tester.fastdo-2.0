package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import excelHelpers.excelHelpers;
import page_locator.SignInPage;
import page_locator.admin.admin_addUserPage;
import page_locator.admin.deleteUserPage;
import page_locator.admin.editUserPage;

public class editUserTest {

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            admin_addUserPage admin_createUser = new admin_addUserPage(driver);
            deleteUserPage del = new deleteUserPage(driver);
            editUserPage edit_user = new editUserPage(driver);
            excelHelpers excel = new excelHelpers();
            excel.setExcelSheet("admin - editUser");

            using.login_admin();
            admin_createUser.crud_user.click();
            using.waitForPageLoaded();
            if (admin_createUser.verifyTitle()) {
                del.search_result("truong@gmail.com");
                using.waitForPageLoaded();
                edit_user.edit_btn.click();
                Thread.sleep(1000);
                if (edit_user.verify_title()) {

                    edit_user.cleartxt();

                    for (int i = 1; i < 7; i++) {
                        System.out.println("=========================");

                        System.out.println("Testcase: " + excel.getCellData("TCID", i));
                        edit_user.edit(excel.getCellData("phone", i), excel.getCellData("firstname", i),
                                excel.getCellData("lastname", i));
                        Thread.sleep(1200);

                        String noti = using.messgaeError_tagline();
                        switch (noti) {
                            case "Bạn chưa nhập họ và tên cho tài khoản!":
                                edit_user.print();
                                break;
                            case "Bạn chưa nhật mật khẩu cho tài khoản!":
                                edit_user.print();
                                break;
                            case "Bạn chưa nhập số điện thoại!":
                                edit_user.print();
                                break;
                            case "Số điện thoại không đúng định dạng!":
                                edit_user.print();
                                break;
                            case "Email này đã được sử dụng, vui lòng sử dụng email khác!":
                                edit_user.print();
                                break;
                            default:
                                if (noti.equals("Đã cập nhật thông tin tài khoản!")) {
                                    using.passed();
                                } else {
                                    using.failed();
                                }
                                break;
                        }
                        Thread.sleep(1200);
                    }
                } else {
                    System.out.println("Không tìm thấy popup chỉnh sửa...");
                }
            } else {
                System.out.println("Hiển thị sai tiêu đề trang...");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
