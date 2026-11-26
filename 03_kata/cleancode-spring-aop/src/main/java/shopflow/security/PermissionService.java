package shopflow.security;

import shopflow.exception.ForbiddenException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PermissionService {
    @Autowired
    UserContext user;

    public void check(String permission) {
        String name = user.current();
        if (!name.equals("admin") && !switch (name) {
            case "seller" -> permission.equals("ORDER");
            case "warehouse" -> permission.equals("STOCK");
            case "finance" -> permission.equals("PAYMENT");
            default -> false;
        }) throw new ForbiddenException("Permission denied: " + permission);
    }
}
