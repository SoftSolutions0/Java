package LabManagement;

public class PC {
    private int ID;
    private String name, lcd;
    private int ram, space;
    private boolean gpu;
    static int count = 0;

    public PC(String name, String lcd, int ram, int space, boolean gpu) {
        this.ID = ++count;
        this.name = name;
        this.lcd = lcd;
        this.ram = ram;
        this.space = space;
        this.gpu = gpu;
    }

    public PC(int ID, String name, String lcd, int ram, int space, boolean gpu) {
        this.ID = ID;
        this.name = name;
        this.lcd = lcd;
        this.ram = ram;
        this.space = space;
        this.gpu = gpu;
        count++;
    }

    public PC(PC other) {
        this.ID = other.getID();
        this.name = other.getName();
        this.lcd = other.getLcd();
        this.ram = other.getRam();
        this.space = other.getSpace();
        this.gpu = other.getGpu();
    }

    public int getID() {
        return ID;
    }

    public String getLcd() {
        return lcd;
    }

    public String getName() {
        return name;
    }

    public int getRam() {
        return ram;
    }

    public boolean getGpu() {
        return gpu;
    }

    public int getSpace() {
        return space;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLcd(String lcd) {
        this.lcd = lcd;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public void setGpu(boolean gpu) {
        this.gpu = gpu;
    }

    @Override
    public String toString() {
        return String.format("ID: %%d, Name: %s, LCD: %s, RAM: %d, Space: %d, hasGPU: %s", ID, name, lcd, ram, space, gpu);
    }

    @Override
    protected Object clone() {
        return new PC(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PC && ((PC) obj).getID() == this.ID;
    }

    public static PC scanPC() {
        System.out.println("Enter PC Details:");
        System.out.print("Name: ");
        String name = Menu.scanner.nextLine();
        System.out.print("LCD: ");
        String lcd = Menu.scanner.nextLine();
        System.out.print("RAM: ");
        int ram = Menu.scanner.nextInt();
        System.out.print("Space: ");
        int space = Menu.scanner.nextInt();
        System.out.print("GPU: ");
        boolean gpu = Menu.scanner.nextBoolean();
        Menu.scanner.nextLine();
        return new PC(name, lcd, ram, space, gpu);
    }
    
}
