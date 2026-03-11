import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    // Class cần kiểm thử
    static class UserValidator {
        public boolean isValidUsername(String username) {
            if (username == null) return false;

            int length = username.length();

            if (length < 6 || length > 20) return false;

            if (username.contains(" ")) return false;

            return true;
        }
    }

    @Test
    void TC01_validUsername() {

        // Arrange
        UserValidator validator = new UserValidator();
        String username = "user123";

        // Act
        boolean result = validator.isValidUsername(username);

        // Assert
        assertTrue(result);
    }

    @Test
    void TC02_usernameTooShort() {

        // Arrange
        UserValidator validator = new UserValidator();
        String username = "abc";

        // Act
        boolean result = validator.isValidUsername(username);

        // Assert
        assertFalse(result);
    }

    @Test
    void TC03_usernameContainsSpace() {

        // Arrange
        UserValidator validator = new UserValidator();
        String username = "user name";

        // Act
        boolean result = validator.isValidUsername(username);

        // Assert
        assertFalse(result);
    }
}