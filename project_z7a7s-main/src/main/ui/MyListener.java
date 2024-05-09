package ui;

import model.Event;
import model.EventLog;
import model.exception.LogException;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//defines specific WindowListener for given application
//Code influced by the AlarmSystem https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
public class MyListener implements WindowListener, LogPrinter {

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            printLog(EventLog.getInstance());
        } catch (LogException ex) {
            System.out.println("Printing Failed");
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        try {
            printLog(EventLog.getInstance());
        } catch (LogException ex) {
            System.out.println("Printing Failed");
        }
    }


    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        try {
            printLog(EventLog.getInstance());
        } catch (LogException ex) {
            System.out.println("Printing Failed");
        }
    }

    @Override
    public void printLog(EventLog el) throws LogException {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

}
