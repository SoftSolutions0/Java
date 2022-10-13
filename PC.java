public class PC {
    private int ID;
    private String name;
    private String LCDName;
    private int RAMSizeGB;
    private int DiskSizeGB;
    private boolean hasGraphicsCard;
    static int count = 0;

    public PC(String name, String LCDName, int RAMSizeGB, int DiskSizeGB, boolean hasGraphicsCard) {
        this.ID = count++;
        this.name = name;
        this.LCDName = LCDName;
        this.RAMSizeGB = RAMSizeGB;
        this.DiskSizeGB = DiskSizeGB;
        this.hasGraphicsCard = hasGraphicsCard;
    }
    
    public PC(int ID, String name, String LCDName, int RAMSizeGB, int DiskSizeGB, boolean hasGraphicsCard) {
        this.ID = ID;
        this.name = name;
        this.LCDName = LCDName;
        this.RAMSizeGB = RAMSizeGB;
        this.DiskSizeGB = DiskSizeGB;
        this.hasGraphicsCard = hasGraphicsCard;
        count++;
    }

    public PC(PC other){
        this.ID = count++;;
        this.name = other.getName();
        this.LCDName = other.getLCDName();
        this.RAMSizeGB = other.getRAMSizeGB();
        this.DiskSizeGB = other.getDiskSizeGB();
        this.hasGraphicsCard = other.gethasGraphicsCard();
    }

    public int getID() {
        return ID;
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

    public int getRAMSizeGB() {
        return RAMSizeGB;
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

    public void setRAMSizeGB(int RAMSizeMB) {
        RAMSizeGB = RAMSizeMB;
    }

    @Override
    public String toString() {
        return String.format("%5d   %-22s %-15s %10d %10d", ID, name, LCDName, RAMSizeGB, DiskSizeGB);
    }

    @Override
    public boolean equals(Object obj) {
        PC temp = (PC) obj;
        if (temp == null){
            return false;
        }
        return this.ID == temp.getID();
    }

    @Override
    protected Object clone() {
        return new PC(this);
    }
}

