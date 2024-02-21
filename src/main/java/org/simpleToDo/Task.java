package org.simpleToDo;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Task {
    public Task(JCheckBox check, Date s, Date e) {
        checkBox = check;
        start = s;
        end = e;
        startDate = new Label(s.toString());
        endDate = new Label("Ongoing");
    }
    JCheckBox checkBox;
    Date start;
    Date end;
    Label startDate;
    Label endDate;
}
