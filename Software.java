public class Software {
    
    private String name, version, type, company;
    private int size;

    public Software(String name, String type, String company, String version, int size) {
        this.name = name;
        this.version = version;
        this.size = size;
        this.type = type;
        this.company = company;
    }

    public Software(Software other) {
        this.name = other.getName();
        this.version = other.getVersion();
        this.size = other.getSize();
        this.type = other.getType();
        this.company = other.getCompany();
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
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

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Software(this);
    }

    @Override
    public String toString() {
        return String.format("%-18s %-15s %-15s %7s %6d", name, type, company, version, size);
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Software) obj).getName()) && this.version.equals(((Software) obj).getVersion());
    }
}
