import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserProcessorTest {

    private UserProcessor processor;

    // Lớp xử lý email
    static class UserProcessor {

        public String processEmail(String email) {

            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Invalid email format");
            }

            String[] parts = email.split("@");

            if (parts.length != 2 || parts[1].isEmpty()) {
                throw new IllegalArgumentException("Invalid email domain");
            }

            return email.toLowerCase();
        }
    }

    // Khởi tạo object trước mỗi test
    @BeforeEach
    void setUp() {
        processor = new UserProcessor();
    }

    @Test
    void shouldReturnSameEmailWhenEmailIsValid() {

        // Arrange
        String email = "user@gmail.com";

        // Act
        String result = processor.processEmail(email);

        // Assert
        assertEquals("user@gmail.com", result);
    }

    @Test
    void shouldThrowExceptionWhenEmailMissingAtSymbol() {

        // Arrange
        String email = "usergmail.com";

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(email);
        });
    }

    @Test
    void shouldThrowExceptionWhenEmailMissingDomain() {

        // Arrange
        String email = "user@";

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(email);
        });
    }

    @Test
    void shouldConvertEmailToLowercase() {

        // Arrange
        String email = "Example@Gmail.com";

        // Act
        String result = processor.processEmail(email);

        // Assert
        assertEquals("example@gmail.com", result);
    }
}