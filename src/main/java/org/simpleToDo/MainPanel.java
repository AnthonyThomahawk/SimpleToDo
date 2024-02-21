/*
 * Created by JFormDesigner on Sun Feb 18 21:27:34 EET 2024
 */

package org.simpleToDo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * @author Enterprise
 */
public class MainPanel extends JPanel {
    ArrayList<Task> allTasks;
    int column = 0;
    int row = 0;
    double wY = 100;
    public MainPanel() {
        initComponents();
        initTaskList();
    }

    public void removeTask(Task target) {
        contentP.remove(target.checkBox);
        contentP.remove(target.startDate);
        contentP.remove(target.endDate);
        allTasks.remove(target);
        scrollPane1.setViewportView(contentP);
    }

    public void refreshTasks() {
        contentP.removeAll();
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.ipady = 0;
        gbc.weighty = 1;
        gbc.weightx = 0.5;
        row = 0;
        column = 0;
        gbc.gridx = row;
        gbc.gridy = column;
        contentP.add(new Label("Task"),gbc);
        gbc.gridx = row+1;
        contentP.add(new Label("Start date"),gbc);
        gbc.gridx = row+2;
        contentP.add(new Label("End date"),gbc);
        column++;

        for (Task t : allTasks) {
            gbc.weighty = wY;
            wY = gbc.weighty * 100;
            gbc.gridy = column;
            gbc.gridx = row;
            contentP.add(t.checkBox, gbc);
            gbc.gridx = row+1;
            contentP.add(t.startDate, gbc);
            gbc.gridx = row+2;
            contentP.add(t.endDate, gbc);
            column++;
        }

        contentP.validate();
        scrollPane1.setViewportView(contentP);
    }

    public void moveTask(Task target, boolean up) {
        for (int i = 0; i < allTasks.size(); i++) {
            if (allTasks.get(i).equals(target)) {
                if (up) {
                    if (i == 0) {
                        return;
                    }
                    Collections.swap(allTasks, i, i-1);
                }
                else {
                    if (i == allTasks.size()-1) {
                        return;
                    }
                    Collections.swap(allTasks, i, i+1);
                }
                break;
            }
        }

        wY = 100;
        refreshTasks();
    }

    public void addTask(String content) {
        JCheckBox newTaskBox = new JCheckBox(content);
        Task newTask = new Task(newTaskBox, new Date(),null);
        newTask.checkBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu contextmenu = new JPopupMenu();
                    JMenuItem deleteAction = new JMenuItem("Remove");
                    deleteAction.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            removeTask(newTask);
                        }
                    });

                    JMenuItem moveUpAction = new JMenuItem("Move up");
                    moveUpAction.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            moveTask(newTask, true);
                        }
                    });

                    JMenuItem moveDownAction = new JMenuItem("Move down");
                    moveDownAction.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            moveTask(newTask, false);
                        }
                    });
                    contextmenu.add(moveUpAction);
                    contextmenu.add(moveDownAction);
                    contextmenu.add(deleteAction);
                    contextmenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        newTask.checkBox.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newTask.checkBox.isSelected()) {
                    newTask.end = new Date();
                    newTask.endDate = new Label(newTask.end.toString());
                    refreshTasks();
                } else {
                    newTask.end = null;
                    newTask.endDate = new Label("Ongoing");
                    refreshTasks();
                }
            }
        });
        allTasks.add(newTask);
        refreshTasks();
    }

    private void initTaskList() {
        contentP = new JPanel();
        taskLayout = new GridBagLayout();
        allTasks = new ArrayList<>();
        gbc = new GridBagConstraints();
        contentP.setLayout(taskLayout);
        refreshTasks();
    }

    private void button1(ActionEvent e) {
        JDialog d = new JDialog(Main.mainWindow, "Add a task...", true);
        d.getContentPane().add(new AddTask());
        d.pack();
        d.setLocationRelativeTo(null);
        d.setVisible(true);
        d.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void thisFocusGained(FocusEvent e) {
        // TODO add your code here
    }

    private void scrollPane1FocusGained(FocusEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Anthony Thomakos (lolcc iojvnd)
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        button1 = new JButton();

        //======== this ========
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                thisFocusGained(e);
            }
        });

        //---- label1 ----
        label1.setText("Tasks");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 14f));

        //======== scrollPane1 ========
        {
            scrollPane1.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    scrollPane1FocusGained(e);
                }
            });
        }

        //---- button1 ----
        button1.setText("Add a task \u2795");
        button1.addActionListener(e -> button1(e));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(scrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Anthony Thomakos (lolcc iojvnd)
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private GridBagLayout taskLayout;
    private GridBagConstraints gbc;
    public JPanel contentP;
}
