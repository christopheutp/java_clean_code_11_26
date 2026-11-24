package miniboutique.persistence;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private final List<String> entries = new ArrayList<>();

    public void save(String email, int total) {
        entries.add(email + ":" + total);
    }

    public List<String> getEntries() {
        return List.copyOf(entries);
    }

}
