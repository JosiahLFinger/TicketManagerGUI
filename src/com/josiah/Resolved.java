package com.josiah;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Josiah on 3/2/2015.
 */
public class Resolved extends Ticket {

    protected Date dateResolved;
    protected String whyClosed;
    private int staticStringCounter;
    private int ticketID2;

    public Resolved(int priority, String reporter, String description, Date dateReported, String whyClosed) {
        super(priority, reporter, description);
        this.dateResolved = new Date();
        this.whyClosed = whyClosed;
        staticStringCounter++;
    }

    public int getTicketID2() {
        return ticketID2;
    }

    @Override
    public String toString() {
        //formats date and time
        String datePattern = "HH:mm 'on' MM/dd/yy";
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
        //saved formatted date to string
        String dateResolvedString = format.format(dateResolved);
        //returns new string
        return ("ID: " + this.ticketID2 + " Problem: " + this.description + " Priority: " + this.priority + " Reported by: "
                + this.reporter + " Reported on: " + this.dateReported + " Date Resolved: " + dateResolvedString + " Reason: " + this.whyClosed);
    }
}
