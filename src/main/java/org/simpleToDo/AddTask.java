/*
 * Created by JFormDesigner on Sun Feb 18 23:08:23 EET 2024
 */

package org.simpleToDo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Enterprise
 */
public class AddTask extends JPanel {
    public AddTask() {
        initComponents();
    }

    private void button1(ActionEvent e) {
        if (textField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Task name cannot be empty.",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Main.mainPanel.addTask(textField1.getText(), null ,null);
        Component component = (Component) e.getSource();
        JDialog dialog = (JDialog) SwingUtilities.getRoot(component);
        dialog.dispose();
    }

    private void textField1KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            button1.doClick();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Anthony Thomakos (lolcc iojvnd)
        label1 = new JLabel();
        textField1 = new JTextField();
        button1 = new JButton();

        //======== this ========

        //---- label1 ----
        label1.setText("Task name");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 11f));

        //---- textField1 ----
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                textField1KeyPressed(e);
            }
        });

        //---- button1 ----
        button1.setText("Add task (+)");
        button1.addActionListener(e -> button1(e));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(label1)
                            .addGap(0, 198, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 211, Short.MAX_VALUE)
                            .addComponent(button1)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(button1)
                    .addContainerGap(7, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Anthony Thomakos (lolcc iojvnd)
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
