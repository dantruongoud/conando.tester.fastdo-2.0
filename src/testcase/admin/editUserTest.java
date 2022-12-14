package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.admin.admin_addUserPage;
import page_locator.admin.deleteUserPage;
import page_locator.admin.editUserPage;

public class editUserTest {

    int testcase;
    String phone;
    String firstname;
    String lastname;

    public editUserTest(int testcase, String phone, String firstname, String lastname) {
        this.testcase = testcase;
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            using.login_admin();
            admin_addUserPage admin_createUser = new admin_addUserPage(driver);
            admin_createUser.crud_user.click();
            if (admin_createUser.verifyTitle()) {
                deleteUserPage del = new deleteUserPage(driver);
                del.search_result("ndtruong@gmail.com");
                using.waitForPageLoaded();
                editUserPage edit_user = new editUserPage(driver);
                edit_user.edit_btn.click();
                if (edit_user.verify_title()) {
                    edit_user.cleartxt();
                    editUserTest[] list_data_test = {
                            new editUserTest(1, "", "firstname", "lastname"),
                            new editUserTest(2, "phone", "firstname", "lastname"),
                            new editUserTest(3, "0328341", "firstname", "lastname"),
                            new editUserTest(4, "0328341092", "", "lastname"),
                            new editUserTest(5, "0328341092", "NGUYEN", ""),
                            new editUserTest(6, "0328341092", "NGUYEN", "TRUONG")
                    };
                    for (int i = 0; i < list_data_test.length; i++) {
                        System.out.println("=========================");
                        System.out.println("Testcase: " + list_data_test[i].testcase);
                        edit_user.edit(list_data_test[i].phone, list_data_test[i].firstname,
                                list_data_test[i].lastname);
                        Thread.sleep(1200);
                        String noti = using.messgaeError_tagline();
                        switch (noti) {
                            case "Bạn chưa nhập họ và tên cho tài khoản!":
                                System.out.println(noti);
                                edit_user.print();
                                break;
                            case "Bạn chưa nhật mật khẩu cho tài khoản!":
                                System.out.println(noti);
                                edit_user.print();
                                break;
                            case "Bạn chưa nhập số điện thoại!":
                                System.out.println(noti);
                                edit_user.print();
                                break;
                            case "Số điện thoại không đúng định dạng!":
                                System.out.println(noti);
                                edit_user.print();
                                break;
                            case "Email này đã được sử dụng, vui lòng sử dụng email khác!":
                                System.out.println(noti);
                                edit_user.print();
                                break;
                            default:
                                noti = using.messgaeError_tagline();
                                if (noti != null) {
                                    System.out.println(noti);
                                    System.out.println("Hoàn tất Chỉnh sửa người dùng");
                                    System.out.println("=========================");
                                } else {
                                    System.out.println("Failed");
                                }
                                break;
                        }
                        Thread.sleep(1200);
                    }
                } else {
                    System.out.println("Không tìm thấy popup chỉnh sửa...");
                    driver.close();
                }
            } else {
                System.out.println("Hiển thị sai tiêu đề trang...");
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
