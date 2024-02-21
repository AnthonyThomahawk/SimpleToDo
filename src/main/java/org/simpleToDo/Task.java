package org.simpleToDo;

import javax.swing.*;
import java.util.Date;

public class Task {
    public Task(JCheckBox check, Date s, Date e) {
        checkBox = check;
        start = s;
        end = e;
    }
    JCheckBox checkBox;
    Date start;
    Date end;
}
