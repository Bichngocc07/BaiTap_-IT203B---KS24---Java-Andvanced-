import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    // Lớp cần kiểm thử
    static class UserService {

        public boolean checkRegistrationAge(int age) {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            return age >= 18;
        }
    }

    @Test
    void testAge18_validBoundary() {
        // Arrange
        UserService service = new UserService();
        int age = 18;

        // Act
        boolean result = service.checkRegistrationAge(age);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void testAge17_invalid() {
        // Arrange
        UserService service = new UserService();
        int age = 17;

        // Act
        boolean result = service.checkRegistrationAge(age);

        // Assert
        assertEquals(false, result);
    }

    @Test
    void testNegativeAge_shouldThrowException() {
        // Arrange
        UserService service = new UserService();
        int age = -1;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.checkRegistrationAge(age);
        });
    }
}