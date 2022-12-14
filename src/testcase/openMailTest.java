package testcase;

import page_locator.openMailPage;

public class openMailTest {
    public static void main(String[] args) {
        try {
            openMailPage open = new openMailPage();
            open.setup();
            open.loginMail("ndtruong.conando@gmail.com", "Dantruong2410");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
