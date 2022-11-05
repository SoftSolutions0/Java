package LabManagement;

public class Software {
    private String name, version, type, company;
    private int size;

    public Software(String name, String version, String type, String company, int size) {
        this.name = name;
        this.version = version;
        this.type = type;
        this.company = company;
        this.size = size;
    }

    public Software(Software other) {
        this.name = other.getName();
        this.version = other.getVersion();
        this.type = other.getType();
        this.company = other.getCompany();
        this.size = other.getSize();
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Software && ((Software) obj).getName().equals(this.name)
                && ((Software) obj).getVersion().equals(this.version);
    }

    @Override
    protected Object clone() {
        return new Software(this);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Version: %s, Type: %s, Company: %s, Size: %d",
                name, version, type, company, size);
    }

    public static Software scanSoftware() {
        System.out.println("Enter the name of the software:");
        String name = Menu.scanner.nextLine();
        System.out.println("Enter the version of the software:");
        String version = Menu.scanner.nextLine();
        System.out.println("Enter the type of the software:");
        String type = Menu.scanner.nextLine();
        System.out.println("Enter the company of the software:");
        String company = Menu.scanner.nextLine();
        System.out.println("Enter the size of the software:");
        int size = Menu.scanner.nextInt();
        Menu.scanner.nextLine();
        return new Software(name, version, type, company, size);
    }
}
