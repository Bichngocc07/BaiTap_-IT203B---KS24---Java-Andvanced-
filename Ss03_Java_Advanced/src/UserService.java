import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class UserService {

    public List<User> getVerifiedUsers(List<User> users) {
        return users.stream()
                .filter(User::isVerified)
                .toList();
    }

    public Tier classifyTier(long months) {

        return switch ((int) months) {
            default -> {
                if (months > 24) yield new Gold();
                if (months > 12) yield new Silver();
                yield new Bronze();
            }
        };
    }

    public long calculateMonths(LocalDate createdAt) {
        return Period.between(createdAt, LocalDate.now()).toTotalMonths();
    }
}