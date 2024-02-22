/*
 * Created by JFormDesigner on Sun Feb 18 21:27:34 EET 2024
 */

package org.simpleToDo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public void addTask(String content, Date start, Date end) {
        JCheckBox newTaskBox = new JCheckBox(content);
        Task newTask;
        if (start == null && end == null)
            newTask = new Task(newTaskBox, new Date(),null);
        else {
            if (start == null) {
                return;
            }
            newTask = new Task(newTaskBox, start, end);
            if (end != null) {
                newTask.checkBox.setSelected(true);
            }
        }


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
                } else {
                    newTask.end = null;
                    newTask.endDate = new Label("Ongoing");
                }
                refreshTasks();
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

    private void menuItem5(ActionEvent e) {
        System.exit(0);
    }

    private void menuItem3(ActionEvent e) {
        StringBuilder data = new StringBuilder("TASK\tSTART_DATE\tEND_DATE\n");
        for (Task t : allTasks) {
            String tname = t.checkBox.getText();
            String sdate = t.startDate.getText();
            String edate = t.endDate.getText();

            data.append(tname).append("\t").append(sdate).append("\t").append(edate).append("\n");
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save tasks file");
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            if (!path.endsWith(".csv")) {
                path += ".csv";
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
                writer.write(data.toString());
                writer.close();
            } catch (IOException x) {
                x.printStackTrace();
            }

        }

    }

    private void menuItem4(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load tasks file");
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getAbsolutePath();

            try {
                BufferedReader reader;

                reader = new BufferedReader(new FileReader(path));
                allTasks = new ArrayList<>();
                reader.readLine(); // first line is not important
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] toks = line.split("\t");
                    Date startD = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(toks[1]);
                    if (!toks[2].equals("Ongoing")) {
                        Date endD = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(toks[2]);
                        addTask(toks[0], startD, endD);
                    } else {
                        addTask(toks[0], startD, null);
                    }
                }

                reader.close();
            } catch (IOException x) {
                x.printStackTrace();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

        }




    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Anthony Thomakos (lolcc iojvnd)
        scrollPane1 = new JScrollPane();
        button1 = new JButton();
        menuBar1 = new JMenuBar();
        menu2 = new JMenu();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();

        //======== this ========
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                thisFocusGained(e);
            }
        });

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

        //======== menuBar1 ========
        {
            menuBar1.setBackground(Color.gray);
            menuBar1.setBorderPainted(false);

            //======== menu2 ========
            {
                menu2.setText("File");

                //---- menuItem3 ----
                menuItem3.setText("Save as CSV");
                menuItem3.addActionListener(e -> menuItem3(e));
                menu2.add(menuItem3);

                //---- menuItem4 ----
                menuItem4.setText("Load CSV");
                menuItem4.addActionListener(e -> menuItem4(e));
                menu2.add(menuItem4);

                //---- menuItem5 ----
                menuItem5.setText("Exit");
                menuItem5.addActionListener(e -> menuItem5(e));
                menu2.add(menuItem5);
            }
            menuBar1.add(menu2);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(menuBar1, GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                        .addComponent(scrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 589, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(menuBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Anthony Thomakos (lolcc iojvnd)
    private JScrollPane scrollPane1;
    private JButton button1;
    private JMenuBar menuBar1;
    private JMenu menu2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private GridBagLayout taskLayout;
    private GridBagConstraints gbc;
    public JPanel contentP;
}
