//public class Main {
//
//    public static void main(String[] args) {
//
//        BaiTap3 user = new BaiTap3();
//
//        try {
//            user.setAge(-5);
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("Chương trình vẫn tiếp tục chạy...");
//    }
//}
public class Main {

    public static void main(String[] args) {

        BaiTap5 user = new BaiTap5();

        try {

            user.setAge(-10);

        } catch (InvalidAgeException e) {

            System.out.println("Lỗi nghiệp vụ: " + e.getMessage());

            e.printStackTrace();
        }

        System.out.println("Chương trình vẫn tiếp tục chạy...");
    }
}