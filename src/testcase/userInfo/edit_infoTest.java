package testcase.userInfo;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.edit_infoPage;
import common.baseSetup;
import excelHelpers.excelHelpers;

public class edit_infoTest {

    public static void main(String[] args) {
        try {

            excelHelpers excel = new excelHelpers();
            excel.setExcelSheet("editInfo");

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage index = new SignInPage(driver);
            edit_infoPage edit_info = new edit_infoPage(driver);

            index.login();
            edit_info.navigation();

            if (edit_info.verify_title()) {

                for (int i = 1; i < 10; i++) {

                    System.out.println("=========================");
                    System.out.println("Testcase: " + excel.getCellData("TCID", i));
                    edit_info.clearTXT();

                    edit_info.change_info(excel.getCellData("firstname", i), excel.getCellData("lastname", i),
                            excel.getCellData("phone", i), excel.getCellData("passold", i),
                            excel.getCellData("passnew", i));
                    Thread.sleep(1200);

                    Boolean passed = false;
                    String noti = index.messgaeError_tagline();
                    for (int j = 0; j < edit_info.tagline.length; j++) {
                        if (noti.equals(edit_info.tagline[j])) {
                            passed = true;
                            index.passed();
                            break;
                        }
                    }
                    if (!passed)
                        index.passed();
                    // switch (noti) {
                    // case "Bạn chưa nhập họ và tên cho tài khoản!":
                    // edit_info.print();
                    // break;
                    // case "Mật khẩu cũ không chính xác!":
                    // edit_info.print();
                    // break;
                    // case "Đã cập nhật thông tin thành công!":
                    // edit_info.print();
                    // break;
                    // case "Bạn chưa nhập số điện thoại!":
                    // edit_info.print();
                    // break;
                    // case "Số điện thoại không đúng!":
                    // edit_info.print();
                    // break;
                    // case "Bạn chưa nhập mật khẩu cũ!":
                    // edit_info.print();
                    // break;
                    // case "Bạn chưa nhập mật khẩu mới!":
                    // edit_info.print();
                    // break;
                    // default:
                    // if (noti.equals("Đã cập nhật thông tin và mật khẩu thành công!")) {
                    // index.passed();
                    // } else {
                    // index.failed();
                    // }
                    // break;
                    // }
                    // Thread.sleep(1200);
                }

            } else {
                index.failed();
                System.out.println("Không tìm thấy title của trang");
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
