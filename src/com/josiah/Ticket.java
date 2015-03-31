package com.josiah;

import java.util.*;
import java.text.SimpleDateFormat;

public class Ticket extends TicketManager{

    protected int priority;
    protected String reporter; //stores person or department who reported issure
    protected String description;
    protected Date dateReported;
    //static so all tickets can change it so it will never repeat.
    protected static int staticTicketIDCounter = 1;
    protected int ticketID;

    //constructor
    public Ticket(int priority, String reporter, String description) {
        this.priority = priority;
        this.reporter = reporter;
        this.description = description;
        this.dateReported = new Date();
        this.ticketID = staticTicketIDCounter++;
    }

    public int getTicketID() {
        return ticketID;
    }
    public String getDescription() {
        return description;
    }
    public String getReporter() {
        return reporter;
    }
    public int getPriority() {
        return priority;
    }
    public Date getDateReported() {
        return dateReported;
    }

    //called if ticket object is an argument to System.out.println
    public String toString() {
        //formats date and time
        String datePattern = "HH:mm 'on' MM/dd/yy";
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
        //saved formatted date to string
        String dateReportedString = format.format(dateReported);
        //returns new string
        return ("ID: " + this.ticketID + " Problem: " + this.description + " Priority: " + this.priority + " Reported by: "
                + this.reporter + " Reported on: " + dateReportedString);
    }
}