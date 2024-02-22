package org.simpleToDo;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Task {
    public Task(JCheckBox check, Date s, Date e) {
        checkBox = check;
        start = s;
        end = e;
        startDate = new Label(start.toString());
        if (end == null)
            endDate = new Label("Ongoing");
        else
            endDate = new Label(end.toString());
    }
    JCheckBox checkBox;
    Date start;
    Date end;
    Label startDate;
    Label endDate;
}
