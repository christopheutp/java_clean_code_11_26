package shopflow.audit;

import shopflow.security.UserContext;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import shopflow.model.Models.Audit;

@Service
public class AuditService {
    @Autowired
    UserContext user;
    private final List<Audit> entries = new ArrayList<>();

    public void record(String operation, boolean success) {
        entries.add(new Audit(user.current(), operation, java.time.Instant.now().toString(), success));
    }

    public List<Audit> entries() {
        return List.copyOf(entries);
    }

    public void reset() {
        entries.clear();
    }
}
