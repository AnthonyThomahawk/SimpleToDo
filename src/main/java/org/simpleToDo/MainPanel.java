/*
 * Created by JFormDesigner on Sun Feb 18 21:27:34 EET 2024
 */

package org.simpleToDo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * @author Enterprise
 */
public class MainPanel extends JPanel {
    public MainPanel() {
        initComponents();
        initTaskList();
    }

    public ArrayList<JCheckBox> getTasks() {
        ArrayList<JCheckBox> res = new ArrayList<>();
        for (int i = 0; i < contentP.getComponentCount(); i++) {
            Component c = contentP.getComponent(i);
            if (c instanceof JCheckBox) {
                res.add((JCheckBox) c);
            }
        }

        return res;
    }

    public void removeTask(int index) {
        ArrayList<JCheckBox> allTasks = getTasks();
        JCheckBox target = allTasks.get(index);
        for (int i = 0; i < contentP.getComponentCount(); i++) {
            if (target.equals(contentP.getComponent(i))) {
                contentP.remove(i);
                contentP.remove(i-1);
                scrollPane1.setViewportView(contentP);
                return;
            }
        }
    }

    public void removeTask(JCheckBox target) {
        for (int i = 0; i < contentP.getComponentCount(); i++) {
            if (target.equals(contentP.getComponent(i))) {
                contentP.remove(i);
                contentP.remove(i-1);
                scrollPane1.setViewportView(contentP);
                return;
            }
        }
    }

    public void addTask(String content) {
        JCheckBox newTask = new JCheckBox(content);
        contentP.add(Box.createRigidArea(new Dimension(5, 5)));
        contentP.add(newTask);
        scrollPane1.setViewportView(contentP);
    }

    private void initTaskList() {
        contentP = new JPanel();
        taskLayout = new BoxLayout(contentP, BoxLayout.Y_AXIS);
        contentP.setLayout(taskLayout);
        contentP.add(Box.createRigidArea(new Dimension(0, 10)));
        scrollPane1.setViewportView(contentP);
    }

    private void button1(ActionEvent e) {
        JDialog d = new JDialog(Main.mainWindow, "Add a task...", true);
        d.getContentPane().add(new AddTask());
        d.pack();
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }

    private void button2(ActionEvent e) {
        JDialog d = new JDialog(Main.mainWindow, "Remove tasks...", true);
        d.getContentPane().add(new RemoveTasks());
        d.pack();
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Anthony Thomakos (lolcc iojvnd)
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========

        //---- label1 ----
        label1.setText("Tasks");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 14f));

        //---- button1 ----
        button1.setText("Add a task (+)");
        button1.addActionListener(e -> button1(e));

        //---- button2 ----
        button2.setText("Remove tasks (-)");
        button2.addActionListener(e -> button2(e));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label1)
                            .addGap(0, 3, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap(7, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button1)
                                .addComponent(button2))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Anthony Thomakos (lolcc iojvnd)
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private BoxLayout taskLayout;
    public JPanel contentP;
}
