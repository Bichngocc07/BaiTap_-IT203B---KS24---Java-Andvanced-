import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordStrengthTest {

    // Lớp đánh giá mật khẩu
    static class PasswordService {

        public String evaluatePasswordStrength(String password) {

            if (password == null || password.length() < 8) {
                return "Yếu";
            }

            boolean hasUpper = password.matches(".*[A-Z].*");
            boolean hasLower = password.matches(".*[a-z].*");
            boolean hasDigit = password.matches(".*[0-9].*");
            boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");

            int criteria = 0;
            if (hasUpper) criteria++;
            if (hasLower) criteria++;
            if (hasDigit) criteria++;
            if (hasSpecial) criteria++;

            if (criteria == 4) {
                return "Mạnh";
            } else if (criteria >= 2) {
                return "Trung bình";
            } else {
                return "Yếu";
            }
        }
    }

    @Test
    void testStrongPassword() {

        PasswordService service = new PasswordService();

        String result = service.evaluatePasswordStrength("Abc123!@");

        assertEquals("Mạnh", result);
    }

    @Test
    void testMediumPasswords() {

        PasswordService service = new PasswordService();

        assertAll(
                () -> assertEquals("Trung bình", service.evaluatePasswordStrength("abc123!@")), // thiếu chữ hoa
                () -> assertEquals("Trung bình", service.evaluatePasswordStrength("ABC123!@")), // thiếu chữ thường
                () -> assertEquals("Trung bình", service.evaluatePasswordStrength("Abcdef!@")), // thiếu số
                () -> assertEquals("Trung bình", service.evaluatePasswordStrength("Abc12345"))  // thiếu ký tự đặc biệt
        );
    }

    @Test
    void testWeakPasswords() {

        PasswordService service = new PasswordService();

        assertAll(
                () -> assertEquals("Yếu", service.evaluatePasswordStrength("Ab1!")),      // quá ngắn
                () -> assertEquals("Yếu", service.evaluatePasswordStrength("password")),  // chỉ chữ thường
                () -> assertEquals("Yếu", service.evaluatePasswordStrength("ABC12345"))   // chữ hoa + số
        );
    }
}