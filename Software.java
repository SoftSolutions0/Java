public class Software {
    
    private String name, version, type;
    private int size;

    public Software(String name, String version, int size, String type) {
        this.name = name;
        this.version = version;
        this.size = size;
        this.type = type;
    }

    public Software(Software other) {
        this.name = other.getName();
        this.version = other.getVersion();
        this.size = other.getSize();
        this.type = other.getType();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getVersion() {
        return version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Software(this);
    }

    @Override
    public String toString() {
        return String.format("%-22s %-10s %7s %6d", name, type, version, size);
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Software) obj).getName()) && this.version.equals(((Software) obj).getVersion());
    }
}
