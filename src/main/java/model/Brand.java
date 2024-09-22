package model;

public class Brand {
    private String name;

    public Brand(String name) {
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Brand brand = (Brand) obj;
        return this.name.equals(brand.name);
    }

    public String name() {
        return this.name;
    }
}
