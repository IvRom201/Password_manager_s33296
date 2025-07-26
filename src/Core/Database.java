package Core;

import java.time.LocalDateTime;
import java.util.*;

public class Database {
    private List<Entry> entries = new ArrayList<>();
    private Set<String> categories = new HashSet<>();
    private LocalDateTime lastTry;

    public void addEntry(Entry entry) {
        entries.add(entry);
        if(entry.getCategory() != null && !entry.getCategory().isEmpty()) {
            categories.add(entry.getCategory());
        }
    }

    public void removeEntry(Entry entry) {
        entries.remove(entry);
        recalculateCategories();
    }

    public void recalculateCategories() {
        categories.clear();
        for(Entry entry : entries) {
            if(entry.getCategory() != null && !entry.getCategory().isEmpty()) {
                categories.add(entry.getCategory());
            }
        }
    }

    public void removeCategory(String category) {
        entries.removeIf(entry -> entry.getCategory().equalsIgnoreCase(category));
        recalculateCategories();
    }

    public List<Entry> findByName(String name) {
        List<Entry> res = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getName().toLowerCase().contains(name.toLowerCase())) {
                res.add(entry);
            }
        }
        return res;
    }

    public void sortByName(){
        entries.sort(Comparator.comparing(Entry::getName, String.CASE_INSENSITIVE_ORDER));
    }

    public void sortByCategory(){
        entries.sort(Comparator.comparing(Entry::getCategory, String.CASE_INSENSITIVE_ORDER));
    }

    public String toRawString() {
        StringBuilder sb = new StringBuilder();
        if(lastTry != null) {
            sb.append("#last_decrypt_try=").append(lastTry.toString()).append("\n");
        }
        for(Entry entry : entries) {
            sb.append(entry.toCSV()).append("\n");
        }
        return sb.toString();
    }

    public void fromString(String text) {
        entries.clear();
        categories.clear();
        String[] lines = text.split("\n");
        for(String line : lines) {
            if(line.startsWith("#last_decrypt_try=")) {
                lastTry = LocalDateTime.parse(line.substring(18));
            } else if(!line.trim().isEmpty()) {
                try {
                    Entry entry = Entry.fromCSV(line);
                    addEntry(entry);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Entry> getEntries() {
        return entries;
    }
    public void setLastTry(LocalDateTime time) {
        lastTry = time;
    }
    public Set<String> getCategories() {
        return categories;
    }

}
