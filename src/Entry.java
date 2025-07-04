import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Entry {
    private String name;
    private String password;
    private String category;
    private String login;
    private String website;
    private String location;

    public Entry(String name, String password, String category) {
        this.name = name;
        this.password = password;
        this.category = category;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getCategory() {
        return category;
    }
    public String getLogin() {
        return login;
    }
    public String getWebsite() {
        return website;
    }
    public String getLocation() {
        return location;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String toString() {
        return name;
    }

    public String toCSV(){
        return String.join(",",
        appendPrimaryPart(name),
        appendPrimaryPart(password),
        appendPrimaryPart(category),
        appendAdditionalPart(login),
        appendAdditionalPart(website),
        appendAdditionalPart(location)
        );
    }

    private String appendAdditionalPart(String val) {
        if(val == null) return "";
        if(val.contains(",") || val.contains("\"") || val.contains("\n")) {
            val = val.replace("\"", "\"\"");
            return "\"" + val + "\"";
        }
        return val;
    }

    private String appendPrimaryPart(String val) {
        if(val == null) return null;
        if(val.contains(",") || val.contains("\"") || val.contains("\n")) {
            val = val.replace("\"", "\"\"");
            return "\"" + val + "\"";
        }
        return val;
    }

    public static Entry fromCSV(String text){
        List<String> tokens = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if(inQuotes) {
                if(c == '"') {
                    if (i+1 < text.length() && text.charAt(i+1) == '"') {
                        sb.append('"');
                    } else {
                        inQuotes = false;
                    }
                } else {
                    sb.append(c);
                }
            } else {
                if(c == '"') {
                    inQuotes = true;
                } else if (c == ',') {
                    tokens.add(sb.toString());
                    sb.setLength(0);
                } else {
                    sb.append(c);
                }
            }
        }
        tokens.add(sb.toString());
        Entry entry = new Entry(
                tokens.size() > 0 ? tokens.get(0) : "",
                tokens.size() > 1 ? tokens.get(1) : "",
                tokens.size() > 2 ? tokens.get(2) : ""
        );
        entry.setLogin(tokens.size() > 3 ? tokens.get(3) : "");
        entry.setWebsite(tokens.size() > 4 ? tokens.get(4) : "");
        entry.setLocation(tokens.size() > 5 ? tokens.get(5) : "");
        return entry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Entry)) return false;
        Entry e = (Entry) o;
        return Objects.equals(name, e.name) && Objects.equals(password, e.password) && Objects.equals(category, e.category) && Objects.equals(login, e.login) && Objects.equals(website, e.website) && Objects.equals(location, e.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, category, login, website, location);
    }
}




