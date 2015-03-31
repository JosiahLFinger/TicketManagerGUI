package com.josiah;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;

/**
 * Created by Josiah on 3/30/2015.
 */
public class TicketForm extends JFrame {

    private JPanel rootPanel;
    private JTextArea problemTextArea;
    private JComboBox priorityComboBox;
    private JTextArea reportedByTextField;
    private JButton addTicketButton;
    private JList<Ticket> openTicketsJList;
    private JList resolvedTicketsJList;
    private JButton resolvedButton;
    private JTextArea howTextField;
    private JScrollPane openTicketScroll;
    private JScrollPane resolvedTicketScroll;
    private JList<Ticket> resolvedJList;

    DefaultListModel<Ticket> openTicketListModel;
    DefaultListModel<Ticket> resolvedTicketListModel;

    public TicketForm() {

        super("Ticket Manager");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 600);

        //combobox for the priority
        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;
        final int five = 5;
        priorityComboBox.addItem(one);
        priorityComboBox.addItem(two);
        priorityComboBox.addItem(three);
        priorityComboBox.addItem(four);
        priorityComboBox.addItem(five);
        priorityComboBox.setSelectedItem(one);

        //create the models for the lists
        openTicketListModel = new DefaultListModel<Ticket>();
        openTicketsJList.setModel(openTicketListModel);
        openTicketsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        resolvedTicketListModel = new DefaultListModel<Ticket>();
        resolvedTicketsJList.setModel(resolvedTicketListModel);
        resolvedTicketsJList.setSelectedIndex(0);


        addTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //grabs all info from fields as well as the date
                String problem = problemTextArea.getText();
                int priority = priorityComboBox.getSelectedIndex();
                String reportedBy = reportedByTextField.getText();
                Date newDate = new Date();

                //clears the fields so the user doesnt have to
                problemTextArea.setText("");
                reportedByTextField.setText("");

                //sends info to Ticket class and makes new ticket
//                ticketQueue.add(new Ticket(priority, reportedBy, problem, newDate));
                Ticket newTicket = new Ticket(priority, reportedBy, problem);
                TicketForm.this.openTicketListModel.addElement(newTicket);

                //writes to file as it is being added
                try {
                    FileWriter activeTicketWriter = new FileWriter("open_tickets.txt", true);
                    BufferedWriter activeBuffWriter = new BufferedWriter(activeTicketWriter);
                    //writes ticket string
                    activeBuffWriter.write(newTicket.toString());
                    activeBuffWriter.newLine();
                    activeBuffWriter.close();

                } catch (IOException ioe) {
                    //handle exceptions
                    System.out.println("Could not open or read the file.");
                    System.out.println(ioe.toString());
                }
            }
        });

        resolvedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //copies open ticket info to new Ticket object then deletes it from open list
                Ticket toResolve = TicketForm.this.openTicketsJList.getSelectedValue();
                TicketForm.this.openTicketListModel.removeElement(toResolve);
                //grabs user input and clears field
                String resolvedHow = howTextField.getText();
                howTextField.setText("");
                //sends data through to resolved class and adds new resolved ticket to the resolved list
                Resolved resolvedTicket = new Resolved(toResolve.getPriority(), toResolve.getReporter(), toResolve.getDescription(), toResolve.getDateReported(), resolvedHow);
                TicketForm.this.resolvedTicketListModel.addElement(resolvedTicket);

                //saves data to txt document
                // Initialize the buffered writers
                try {
                    FileWriter resolvedTicketWriter = new FileWriter("resolved_tickets.txt", true);
                    BufferedWriter resolvedBuffWriter = new BufferedWriter(resolvedTicketWriter);
                    //writes ticket string
                    resolvedBuffWriter.write(resolvedTicket.toString());
                    resolvedBuffWriter.newLine();
                    resolvedBuffWriter.close();

                } catch (IOException ioe) {
                    //handles exceptions
                    System.out.println("Could not open or read file.");
                    System.out.println(ioe.toString());
                }
            }
        });
    }
}
