package shopflow.security;

import org.springframework.stereotype.Service;

@Service
public class UserContext {
    private final ThreadLocal<String> user = new ThreadLocal<>();

    public String current() {
        return user.get() == null ? "guest" : user.get();
    }

    public <T> T run(String name, java.util.function.Supplier<T> action) {
        String previous = user.get();
        user.set(name);
        try {
            return action.get();
        } finally {
            if (previous == null) user.remove();
            else user.set(previous);
        }
    }
}
