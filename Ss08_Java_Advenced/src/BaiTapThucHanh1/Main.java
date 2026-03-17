package BaiTapThucHanh1;

public class Main {

    public static void main(String[] args) {

        EmployeeRepository repo1 = new EmployeeRepository();
        EmployeeRepository repo2 = new EmployeeRepository();

        repo1.save(new Employee(1,"An",1000));
        repo2.findAll();

        // kiểm tra cùng instance
        System.out.println("\nKiểm tra Singleton:");
        System.out.println("Repo1 connection: " + repo1.getConnection().hashCode());
        System.out.println("Repo2 connection: " + repo2.getConnection().hashCode());
    }
}