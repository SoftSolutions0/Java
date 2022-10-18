public class Software {
    
    // Not completed yet
    private String name, version, description, url, author, platform;

    public Software(String name, String version, String description, String url, String author, String platform) {
        this.name = name;
        this.version = version;
        this.description = description;
        this.url = url;
        this.author = author;
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

    public String getPlatform() {
        return platform;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
