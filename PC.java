

public class PC {
    private String assetID;
    private String name;
    private String LCDName;
    private int RAMSizeMB;
    private int DiskSizeGB;
    private boolean hasGraphicsCard;

    public PC(String assetID, String name, String LCDName, int RAMSizeMB, int DiskSizeGB, boolean hasGraphicsCard) {
        this.assetID = assetID;
        this.name = name;
        this.LCDName = LCDName;
        this.RAMSizeMB = RAMSizeMB;
        this.DiskSizeGB = DiskSizeGB;
        this.hasGraphicsCard = hasGraphicsCard;
    }

    public PC(PC other){
        this.assetID = other.getAssetID();
        this.name = other.getName();
        this.LCDName = other.getLCDName();
        this.RAMSizeMB = other.getRAMSizeMB();
        this.DiskSizeGB = other.getDiskSizeGB();
        this.hasGraphicsCard = other.gethasGraphicsCard();
    }

    public String getAssetID() {
        return assetID;
    }

    public int getDiskSizeGB() {
        return DiskSizeGB;
    }

    public String getLCDName() {
        return LCDName;
    }

    public boolean gethasGraphicsCard(){
        return hasGraphicsCard;
    }

    public String getName() {
        return name;
    }

    public int getRAMSizeMB() {
        return RAMSizeMB;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public void setDiskSizeGB(int diskSizeGB) {
        this.DiskSizeGB = diskSizeGB;
    }

    public void setHasGraphicsCard(boolean hasGraphicsCard) {
        this.hasGraphicsCard = hasGraphicsCard;
    }

    public void setLCDName(String lCDName) {
        LCDName = lCDName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRAMSizeMB(int rAMSizeMB) {
        RAMSizeMB = rAMSizeMB;
    }

    @Override
    public String toString() {
        return String.format("Asset ID: %s; Name: %s; LCDName: %s, RAMSize: %dMB; DiskSpace: %dGB", assetID, name, LCDName, RAMSizeMB, DiskSizeGB);
    }

    @Override
    public boolean equals(Object obj) {
        PC temp = (PC) obj;
        if (temp == null){
            return false;
        }
        return this.assetID == temp.getAssetID();
    }

    @Override
    protected Object clone() {
        return new PC(this);
    }
}

