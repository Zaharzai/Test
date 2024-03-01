import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class ComputerRepository {
    private List<Computer> computerList;

    public ComputerRepository() {
        computerList = new ArrayList<>();
    }

    public void addComputer(Computer computer) {
        computerList.add(computer);
    }

    public void removeComputer(Computer computer) {
        computerList.remove(computer);
    }

    public void updateComputer(int computerNumber, Computer updatedComputer) {
        for (Computer comp : computerList) {
            if (comp.getNumber() == computerNumber) {
                computerList.set(computerList.indexOf(comp), updatedComputer);
                System.out.println("Computer " + computerNumber + " updated successfully.");
                return;
            }
        }
        System.out.println("Computer " + computerNumber + " not found.");
    }

    public List<Computer> getAllComputers() {
        return computerList;
    }
}
class Computer {
    private int number;
    private Winchester winchester;
    private DiskDrive diskDrive;
    private RAM ram;
    private boolean isTurnedOn = false;
    private List<String> changes = new ArrayList<>();

    public Computer(int number, Winchester winchester, DiskDrive diskDrive, RAM ram) {
        this.number = number;
        this.winchester = winchester;
        this.diskDrive = diskDrive;
        this.ram = ram;
        changes.add("Initial Parameters: Winchester - " + winchester.getSize() + "GB, DiskDrive - " + diskDrive.getSpeed() + "RPM, RAM - " + ram.getSize() + "GB");
    }

    public void turnOn() {
        isTurnedOn = true;
        System.out.println("Computer " + number + " is turned on.");
    }

    public void turnOff() {
        isTurnedOn = false;
        System.out.println("Computer " + number + " is turned off.");
    }

    public void checkForViruses() {
        System.out.println("Checking for viruses on computer " + number + "...");
        // Add your virus checking logic here
        System.out.println("No viruses found on computer " + number + ".");
    }

    public void displayWinchesterSize() {
        System.out.println("Computer " + number + " - Winchester size: " + winchester.getSize() + "GB");
    }

    public void modifyWinchesterSize(int newSize) {
        changes.add("Winchester size changed to " + newSize + "GB");
        winchester.setSize(newSize);
    }

    public void modifyDiskDriveSpeed(int newSpeed) {
        changes.add("DiskDrive speed changed to " + newSpeed + "RPM");
        diskDrive.setSpeed(newSpeed);
    }

    public void modifyRAMSize(int newSize) {
        changes.add("RAM size changed to " + newSize + "GB");
        ram.setSize(newSize);
    }

    public int getNumber() {
        return number;
    }

    public List<String> getChanges() {
        return changes;
    }

    public String toString() {
        return "Computer " + number + " - Winchester: " + winchester.getSize() + "GB, DiskDrive Speed: " + diskDrive.getSpeed() + "RPM, RAM Size: " + ram.getSize() + "GB";
    }
}

class Winchester {
    private int size; // in gigabytes

    public Winchester(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

class DiskDrive {
    private int speed; // in RPM

    public DiskDrive(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

class RAM {
    private int size; // in gigabytes

    public RAM(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Computer> computers = new ArrayList<>();
        ComputerRepository computerRepository = new ComputerRepository();
        int computerNumber = 1;
        int computerCount = 1;

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add a computer");
            System.out.println("2. Display all computers and their data");
            System.out.println("3. Modify a computer's attribute");
            System.out.println("4. Display Winchester size of a computer");
                    System.out.println("5. Display changes in a computer's parameters");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Winchester size in GB: ");
                    int winchesterSize = scanner.nextInt();
                    Winchester winchester = new Winchester(winchesterSize);

                    System.out.println("Enter DiskDrive speed (RPM): ");
                    int diskDriveSpeed = scanner.nextInt();
                    DiskDrive diskDrive = new DiskDrive(diskDriveSpeed);

                    System.out.println("Enter RAM size in GB: ");
                    int ramSize = scanner.nextInt();
                    RAM ram = new RAM(ramSize);

                    Computer computer = new Computer(computerNumber, winchester, diskDrive, ram);
                    computers.add(computer);
                    System.out.println("Computer " + computerNumber + " added successfully.");
                    computerNumber++;
                    computerRepository.addComputer(computer);
                    System.out.println("Computer " + computerCount + " added successfully.");
                    computerCount++;
                    break;

                case 2:
                    System.out.println("Computers:");
                    for (Computer comp : computers) {
                        System.out.println(comp);
                    }
                    break;

                case 3:
                    System.out.println("Enter the computer number to modify:");
                    int compNumber = scanner.nextInt();
                    boolean found = false;
                    for (Computer comp : computers) {
                        if (comp.getNumber() == compNumber) {
                            found = true;
                            System.out.println("Computer found. Enter the number of the attribute to modify:");
                            System.out.println("1. Winchester size");
                            System.out.println("2. DiskDrive speed");
                            System.out.println("3. RAM size");
                            int attributeChoice = scanner.nextInt();
                            switch (attributeChoice) {
                                case 1:
                                    System.out.println("Enter new Winchester size in GB: ");
                                    int newWinchesterSize = scanner.nextInt();
                                    comp.modifyWinchesterSize(newWinchesterSize);
                                    System.out.println("Winchester size updated successfully.");
                                    break;
                                case 2:
                                    System.out.println("Enter new DiskDrive speed (RPM): ");
                                    int newDiskDriveSpeed = scanner.nextInt();
                                    comp.modifyDiskDriveSpeed(newDiskDriveSpeed);
                                    System.out.println("DiskDrive speed updated successfully.");
                                    break;
                                case 3:
                                    System.out.println("Enter new RAM size in GB: ");
                                    int newRamSize = scanner.nextInt();
                                    comp.modifyRAMSize(newRamSize);
                                    System.out.println("RAM size updated successfully.");
                                    break;
                                default:
                                    System.out.println("Invalid attribute choice.");
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("Computer not found.");
                    }
                    break;

                case 4:
                    System.out.println("Enter the computer number to display Winchester size:");
                    int compNumberWinchester = scanner.nextInt();
                    boolean foundWinchester = false;
                    for (Computer comp : computers) {
                        if (comp.getNumber() == compNumberWinchester) {
                            comp.displayWinchesterSize();
                            foundWinchester = true;
                            break;
                        }
                    }
                    if (!foundWinchester) {
                        System.out.println("Computer not found.");
                    }
                    break;

                case 5:
                    System.out.println("Enter the computer number to display changes in parameters:");
                    int compNumberChanges = scanner.nextInt();
                    boolean foundChanges = false;
                    for (Computer comp : computers) {
                        if (comp.getNumber() == compNumberChanges) {
                            System.out.println("Changes in Computer " + comp.getNumber() + ":");
                            for (String change : comp.getChanges()) {
                                System.out.println(change);
                            }
                            foundChanges = true;
                            break;
                        }
                    }
                    if (!foundChanges) {
                        System.out.println("Computer not found.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting program.");
                    scanner.close();
                System.out.println("Computers in the Repository:");
                for (Computer comp : computerRepository.getAllComputers()) {
                    System.out.println(comp);
                }
                return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}