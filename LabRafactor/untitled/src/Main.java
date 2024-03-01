import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Computer {
    public DiskDrive diskDrive;
    public RAM ram;
    private Winchester winchester;

    public Computer(Winchester winchester, DiskDrive diskDrive, RAM ram) {
        this.winchester = winchester;
        this.diskDrive = diskDrive;
        this.ram = ram;
    }

    public void turnOn() {
        System.out.println("Computer is turning on...");
    }

    public void turnOff() {
        System.out.println("Computer is turning off...");
    }

    public void checkForViruses() {
        System.out.println("Checking for viruses...");
    }

    public void updateWinchesterSize(int newSize) {
        this.winchester = new Winchester(newSize);
    }

    public void updateDiskDriveType(String newType) {
        this.diskDrive = new DiskDrive(newType);
    }

    public void updateRAMSize(int newSize) {
        this.ram = new RAM(newSize);
    }

    public int getWinchesterSize() {
        return winchester.getSize();
    }

    public String getAllInfo() {
        return "Информация о компе:\n" +
                "Размер винчестера: " + winchester.getSize() + "\n" +
                "Модель жесткого диска: " + diskDrive.getType() + "\n" +
                "RAM размер: " + ram.getSize();
    }

    public String getChanges(String oldVal, String newVal) {
        return "Изменения:\n" + oldVal + " -> " + newVal;
    }

    public String getSpecificCharacteristic(String characteristic) {
        switch (characteristic) {
            case "Winchester":
                return "Winchester размер: " + winchester.getSize();
            case "Disk Drive":
                return "Disk drive тип: " + diskDrive.getType();
            case "RAM":
                return "RAM размер: " + ram.getSize();
            default:
                return "Неправильные данные.";
        }
    }
}

class Winchester {
    private int size;

    public Winchester(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}

class DiskDrive {
    private String type;

    public DiskDrive(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class RAM {
    private int size;

    public RAM(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}

public class Main {
    private static List<Computer> computers = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Компьютерный менеджмент");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton addComputerButton = new JButton("Добавить компьютер");
        addComputerButton.setBounds(50, 50, 150, 30);

        JButton updateComputerButton = new JButton("Обновить компьютер");
        updateComputerButton.setBounds(200, 50, 150, 30);

        JButton showAllButton = new JButton("Показать все компьютеры");
        showAllButton.setBounds(50, 100, 150, 30);

        JButton showChangesButton = new JButton("Показать изменения");
        showChangesButton.setBounds(200, 100, 150, 30);

        JButton showCharacteristicButton = new JButton("Инфа по характеристикам");
        showCharacteristicButton.setBounds(50, 150, 300, 30);

        JButton deleteComputerButton = new JButton("Удалить данные");
        deleteComputerButton.setBounds(50, 200, 150, 30);

        addComputerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String winchesterSizeStr = JOptionPane.showInputDialog("Введите Winchester размер:");
                int winchesterSize = Integer.parseInt(winchesterSizeStr);

                String diskDriveType = JOptionPane.showInputDialog("Введите Disk Drive тип:");

                String ramSizeStr = JOptionPane.showInputDialog("Введите RAM размер:");
                int ramSize = Integer.parseInt(ramSizeStr);

                Computer newComputer = new Computer(new Winchester(winchesterSize), new DiskDrive(diskDriveType), new RAM(ramSize));
                computers.add(newComputer);
            }
        });

        updateComputerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] computerChoices = computers.toArray();
                Computer selectedComputer = (Computer) JOptionPane.showInputDialog(null, "Выберите компьютер:", "Обновить", JOptionPane.PLAIN_MESSAGE, null, computerChoices, computerChoices[0]);

                if (selectedComputer != null) {
                    String[] options = {"Winchester Size", "Disk Drive Type", "RAM Size"};
                    String selectedOption = (String) JOptionPane.showInputDialog(null, "Что хотите заменить:", "Обновить", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                    if (selectedOption != null) {
                        if (selectedOption.equals("Winchester Size")) {
                            int oldSize = selectedComputer.getWinchesterSize();
                            String newWinchesterSizeStr = JOptionPane.showInputDialog("Введите новый Winchester size:");
                            int newWinchesterSize = Integer.parseInt(newWinchesterSizeStr);
                            selectedComputer.updateWinchesterSize(newWinchesterSize);
                            JOptionPane.showMessageDialog(null, selectedComputer.getChanges("Winchester size: " + oldSize, "Winchester size: " + newWinchesterSize), "Изменение", JOptionPane.INFORMATION_MESSAGE);
                        } else if (selectedOption.equals("Disk Drive Type")) {
                            String oldType = selectedComputer.diskDrive.getType();
                            String newDiskDriveType = JOptionPane.showInputDialog("Введите новый Disk Drive type:");
                            selectedComputer.updateDiskDriveType(newDiskDriveType);
                            JOptionPane.showMessageDialog(null, selectedComputer.getChanges("Disk drive type: " + oldType, "Disk drive type: " + newDiskDriveType), "Изменение", JOptionPane.INFORMATION_MESSAGE);
                        } else if (selectedOption.equals("RAM Size")) {
                            int oldSize = selectedComputer.ram.getSize();
                            String newRAMSizeStr = JOptionPane.showInputDialog("Введите новый RAM size:");
                            int newRAMSize = Integer.parseInt(newRAMSizeStr);
                            selectedComputer.updateRAMSize(newRAMSize);
                            JOptionPane.showMessageDialog(null, selectedComputer.getChanges("RAM size: " + oldSize, "RAM size: " + newRAMSize), "Изменение", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });

        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder allComputerInfo = new StringBuilder();
                for (Computer computer : computers) {
                    allComputerInfo.append(computer.getAllInfo()).append("\n\n");
                }
                JTextArea textArea = new JTextArea(allComputerInfo.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(300, 200));
                JOptionPane.showMessageDialog(null, scrollPane, "Информация про все компьютеры", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        showChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] computerChoices = computers.toArray();
                Computer selectedComputer = (Computer) JOptionPane.showInputDialog(null, "По какому компьютеру хотите увидеть изменения:", "Показать", JOptionPane.PLAIN_MESSAGE, null, computerChoices, computerChoices[0]);
                if (selectedComputer != null) {
                    JOptionPane.showMessageDialog(null, selectedComputer.getAllInfo(), "Computer Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        showCharacteristicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] computerChoices = computers.toArray();
                Computer selectedComputer = (Computer) JOptionPane.showInputDialog(null, "Выберите комп:", "Показать", JOptionPane.PLAIN_MESSAGE, null, computerChoices, computerChoices[0]);

                String[] characteristicOptions = {"Winchester", "Disk Drive", "RAM"};
                String selectedCharacteristic = (String) JOptionPane.showInputDialog(null, "Выберите характеристику:", "Показать", JOptionPane.PLAIN_MESSAGE, null, characteristicOptions, characteristicOptions[0]);

                if (selectedComputer != null && selectedCharacteristic != null) {
                    String specificCharacteristic = selectedComputer.getSpecificCharacteristic(selectedCharacteristic);
                    JOptionPane.showMessageDialog(null, specificCharacteristic, "Specific Characteristic", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        deleteComputerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] computerChoices = computers.toArray();
                Computer selectedComputer = (Computer) JOptionPane.showInputDialog(null, "Выберите комп который надо удалить:", "Удалить", JOptionPane.PLAIN_MESSAGE, null, computerChoices, computerChoices[0]);

                if (selectedComputer != null) {
                    computers.remove(selectedComputer);
                    JOptionPane.showMessageDialog(null, "Успешно удалился", "Удалить", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        frame.setLayout(null);
        frame.add(addComputerButton);
        frame.add(updateComputerButton);
        frame.add(showAllButton);
        frame.add(showChangesButton);
        frame.add(showCharacteristicButton);
        frame.add(deleteComputerButton);
        frame.setVisible(true);
    }
}