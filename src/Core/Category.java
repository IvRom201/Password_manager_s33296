package Core;

public class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean equals(Category c) {
        return this.name.equals(c.getName());
    }
    public int hashCode() {
        return this.name.hashCode();
    }
}
