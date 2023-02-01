package testcase.inCorp;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.edit_incorpPage;
import common.baseSetup;
import excelHelpers.excelHelpers;

public class edit_incorpTest {

    public static void main(String[] args) {
        try {
            excelHelpers excel = new excelHelpers();
            excel.setExcelSheet("Create inCorp");

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage index = new SignInPage(driver);
            edit_incorpPage edit_incorp = new edit_incorpPage(driver);

            index.login();
            edit_incorp.clickNavigation();
            edit_incorp.find.click();
            edit_incorp.edit.click();

            if (edit_incorp.verify_popup()) {

                for (int i = 1; i < 6; i++) {
                    System.out.println("=========================");
                    System.out.println("Testcase: " + excel.getCellData("TCID", i));
                    edit_incorp.clearDataTest();

                    edit_incorp.create_incorp(excel.getCellData("name", i), excel.getCellData("address", i),
                            excel.getCellData("phone", i), excel.getCellData("mail", i));
                    Thread.sleep(1200);

                    Boolean passed = false;
                    String noti = index.messgaeError_tagline();
                    for (int j = 0; j < edit_incorp.tagline.length; j++) {
                        if (noti.equals(edit_incorp.tagline[j])) {
                            passed = true;
                            index.passed();
                            break;
                        }
                    }
                    if (!passed)
                        index.failed();
                    // switch (noti) {
                    // case "Bạn chưa nhập tên tổ chức.":
                    // edit_incorp.print();
                    // break;
                    // case "Bạn chưa nhập địa chỉ tổ chức.":
                    // edit_incorp.print();
                    // break;
                    // case "Bạn chưa nhập Số điện thoại người đại diện.":
                    // edit_incorp.print();
                    // break;
                    // case "Bạn chưa nhập email tổ chức.":
                    // edit_incorp.print();
                    // break;
                    // default:
                    // if (noti.equals("Đã cập nhật thông tin tổ chức!")) {
                    // index.passed();
                    // } else {
                    // index.failed();
                    // }
                    // break;
                    // }
                    // Thread.sleep(1000);

                }
            } else {
                index.failed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
