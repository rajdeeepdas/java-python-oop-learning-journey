package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.ucalgary.oop.*;

public class SmartHomeGUI extends JFrame {

    // Smart Home and Device Objects
    private final SmartHome smartHome;
    private final SmartLight smartLight;
    private final SmartLock smartLock;
    private final SmartThermostat smartThermostat;

    // GUI Components for Light Control
    private JLabel lightStatusLabel;
    private JButton lightOnButton;
    private JButton lightOffButton;

    // GUI Components for Lock Control
    private JLabel lockStatusLabel;
    private JButton lockButton;
    private JButton unlockButton;

    // GUI Components for Thermostat Control
    private JLabel thermostatLabel;
    private JTextField thermostatField;
    private JButton setTempButton;

    // GUI Components for Mode Control
    private JButton sleepModeButton;
    private JButton vacationModeButton;

    public SmartHomeGUI() {
        super("Smart Home System");

        // Initialize your smart devices
        smartLight = new SmartLight();
        smartLock = new SmartLock();
        smartThermostat = new SmartThermostat();

        // Create and build the SmartHome by adding devices
        smartHome = new SmartHome();
        smartHome.addDevice(smartLight)
                 .addDevice(smartLock)
                 .addDevice(smartThermostat)
                 .build();

        // Initialize and set up GUI components
        initComponents();
        layoutComponents();

        // Configure frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    private void initComponents() {
        // Initial label (default state is OFF, i.e. false)
        lightStatusLabel = new JLabel("Light is OFF");
        lightOnButton = new JButton("Turn On");
        lightOffButton = new JButton("Turn Off");

        // When the user clicks "Turn On", set the light's state to true
        lightOnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                smartHome.setDeviceState(smartLight, true);
                lightStatusLabel.setText("Light is ON");
            }
        });
        // When the user clicks "Turn Off", set the light's state to false
        lightOffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                smartHome.setDeviceState(smartLight, false);
                lightStatusLabel.setText("Light is OFF");
            }
        });


        // The default state for SmartLock is LOCKED (true)
        lockStatusLabel = new JLabel("Lock is LOCKED");
        lockButton = new JButton("Lock");
        unlockButton = new JButton("Unlock");

        lockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                smartHome.setDeviceState(smartLock, true);
                lockStatusLabel.setText("Lock is LOCKED");
            }
        });
        unlockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                smartHome.setDeviceState(smartLock, false);
                lockStatusLabel.setText("Lock is UNLOCKED");
            }
        });

    
        thermostatLabel = new JLabel("Current Temperature: " + smartThermostat.getState() + "°C");
        thermostatField = new JTextField(5);
        setTempButton = new JButton("Set Temperature");

        setTempButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int newTemp = Integer.parseInt(thermostatField.getText());
                    // Use the adjustTemperature method to simulate adjusting temperature
                    smartThermostat.adjustTemperature(newTemp);
                    thermostatLabel.setText("Current Temperature: " + smartThermostat.getState() + "°C");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SmartHomeGUI.this, "Please enter a valid integer for temperature.");
                }
            }
        });

        
        sleepModeButton = new JButton("Sleep Mode");
        vacationModeButton = new JButton("Vacation Mode");

        sleepModeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                smartHome.sendMessage("Sleep");
                lightStatusLabel.setText("Light is " + (smartLight.getState() ? "ON" : "OFF"));
                lockStatusLabel.setText("Lock is " + (smartLock.getState() ? "LOCKED" : "UNLOCKED"));
                thermostatLabel.setText("Current Temperature: " + smartThermostat.getState() + "°C");
            }
        });
        vacationModeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                smartHome.sendMessage("Vacation");
                lightStatusLabel.setText("Light is " + (smartLight.getState() ? "ON" : "OFF"));
                lockStatusLabel.setText("Lock is " + (smartLock.getState() ? "LOCKED" : "UNLOCKED"));
                thermostatLabel.setText("Current Temperature: " + smartThermostat.getState() + "°C");
            }
        });
    }

    private void layoutComponents() {

        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JPanel lightPanel = new JPanel();
        lightPanel.setBorder(BorderFactory.createTitledBorder("Smart Light Control"));
        lightPanel.add(lightStatusLabel);
        lightPanel.add(lightOnButton);
        lightPanel.add(lightOffButton);

        JPanel lockPanel = new JPanel();
        lockPanel.setBorder(BorderFactory.createTitledBorder("Smart Lock Control"));
        lockPanel.add(lockStatusLabel);
        lockPanel.add(lockButton);
        lockPanel.add(unlockButton);

  
        JPanel thermostatPanel = new JPanel();
        thermostatPanel.setBorder(BorderFactory.createTitledBorder("Smart Thermostat Control"));
        thermostatPanel.add(thermostatLabel);
        thermostatPanel.add(new JLabel("Set Temp:"));
        thermostatPanel.add(thermostatField);
        thermostatPanel.add(setTempButton);

      
        JPanel modePanel = new JPanel();
        modePanel.setBorder(BorderFactory.createTitledBorder("Mode Control"));
        modePanel.add(sleepModeButton);
        modePanel.add(vacationModeButton);

        
        mainPanel.add(lightPanel);
        mainPanel.add(lockPanel);
        mainPanel.add(thermostatPanel);
        mainPanel.add(modePanel);

        add(mainPanel);
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SmartHomeGUI();
            }
        });
    }

    public SmartThermostat getSmartThermostat() {
        return smartThermostat;
    }
}
