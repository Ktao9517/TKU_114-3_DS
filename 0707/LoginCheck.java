public class LoginCheck {
    public static void main(String[] args) {
        String username = "admin";
        String password = "1234";

        String inputUsername = "admin";
        String inputPassword = "1234";

        boolean result1 = username.equals(inputUsername)&&password.equals(inputPassword);

        System.out.println("帳號密碼: " + result1);
    }
}
