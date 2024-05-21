/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import Model.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author SINGER
 */
public class User extends javax.swing.JDialog {

    private HashMap<String, Integer> genderMap = new HashMap<>();
    private HashMap<String, Integer> typeMap = new HashMap<>();
    private String id;
    private int x, y;

    public User(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadTypes();
        loadGender();
        jButton3.setVisible(false);
        jPanel1.requestFocus();
    }

    public User(java.awt.Frame parent, boolean modal, HashMap<String, String> map) {
        super(parent, modal);
        initComponents();
        loadGender();
        loadTypes();
        fillDetails(map);
        jPanel1.requestFocus();
    }

    private void fillDetails(HashMap<String, String> map) {
        jPanel1.requestFocus();
        jButton2.setVisible(false);
        jLabel2.setText("Update User Details");
        this.id = map.get("id");

        jTextField2.setText(map.get("fname"));
        jTextField3.setText(map.get("lname"));
        jTextField4.setText(map.get("nic"));
        jTextField5.setText(map.get("mobile"));
        jTextField6.setText(map.get("username"));
        jPasswordField1.setText("Unkown@123");
        jComboBox3.setSelectedItem(map.get("gender"));
        jComboBox4.setSelectedItem(map.get("type"));

        jPasswordField1.setEditable(false);
        jTextField4.setEditable(false);
        jComboBox3.setEnabled(false);
        jComboBox4.setEnabled(false);
    }

    private void loadGender() {
        Vector<String> v = new Vector<>();
        v.add("Select Gender");

        try {
            ResultSet rs = Database.execute("SELECT * FROM `gender`");
            while (rs.next()) {
                v.add(rs.getString("name"));
                genderMap.put(rs.getString("name"), rs.getInt("id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(v);
        jComboBox3.setModel(model);
    }

    private void loadTypes() {
        Vector<String> v = new Vector<>();
        v.add("Select User Type");

        try {
            ResultSet rs = Database.execute("SELECT * FROM `user_type`");
            while (rs.next()) {
                v.add(rs.getString("type"));
                typeMap.put(rs.getString("type"), rs.getInt("id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        DefaultComboBoxModel model = new DefaultComboBoxModel(v);
        jComboBox4.setModel(model);
    }

    private boolean validation() {
        if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter user's first name", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField2.requestFocus();
            return false;
        } else if (jTextField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter user's last name", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField3.requestFocus();
            return false;
        } else if (jTextField4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter user's NIC number", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField4.requestFocus();
            return false;
        } else if (jTextField5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter user's contact number", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField5.requestFocus();
            return false;
        } else if (!jTextField5.getText().matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Invalid user's contact number (ex:- 07xxxxxxxx)", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField5.requestFocus();
            return false;
        } else if (jTextField6.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter user's username", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField6.requestFocus();
            return false;
        } else if (String.valueOf(jPasswordField1.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter user's password", "Warning", JOptionPane.WARNING_MESSAGE);
            jPasswordField1.requestFocus();
            return false;
        } else if (!String.valueOf(jPasswordField1.getPassword()).matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            JOptionPane.showMessageDialog(this, "Invalid user's password (ex:- abcD#123&4)", "Warning", JOptionPane.WARNING_MESSAGE);
            jPasswordField1.requestFocus();
            return false;
        } else if (String.valueOf(jComboBox3.getSelectedItem()).equals("Select Gender")) {
            JOptionPane.showMessageDialog(this, "Please select user's gender", "Warning", JOptionPane.WARNING_MESSAGE);
            jComboBox3.requestFocus();
            return false;
        } else if (String.valueOf(jComboBox4.getSelectedItem()).equals("Select Employee Type")) {
            JOptionPane.showMessageDialog(this, "Please select user's type", "Warning", JOptionPane.WARNING_MESSAGE);
            jComboBox4.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(102, 204, 255));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("User Registration");
        jLabel2.setOpaque(true);

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cancel");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Last Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "NIC Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Contact No", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });

        jPasswordField1.setBackground(new java.awt.Color(255, 255, 255));
        jPasswordField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPasswordField1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jComboBox3.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Gender", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jComboBox3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jComboBox4.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox4.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Employee Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jComboBox4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));
        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "First Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add New User");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 204, 51));
        jButton3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(51, 51, 51));
        jButton3.setText("Update User");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, 340, Short.MAX_VALUE)
                    .addComponent(jComboBox3, 0, 340, Short.MAX_VALUE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        String nic = jTextField4.getText();

        if (nic.length() == 12) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        String mobile = jTextField5.getText();
        String text = mobile + evt.getKeyChar();

        if (mobile.length() == 10) {
            evt.consume();
        } else if (!Pattern.compile("[0-9]+").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (validation()) {
            String fname = jTextField2.getText();
            String lname = jTextField3.getText();
            String nic = jTextField4.getText();
            String mobile = jTextField5.getText();
            String username = jTextField6.getText();
            String password = String.valueOf(jPasswordField1.getPassword());
            String gender = String.valueOf(jComboBox3.getSelectedItem());
            String type = String.valueOf(jComboBox4.getSelectedItem());

            try {
                ResultSet resultSet = Database.execute("SELECT * FROM `user` WHERE `nic` = '" + nic + "' OR `mobile` = '" + mobile + "'");
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This NIC number and contact number already exsits", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    String path = "D:\\PCJT\\Sodtware Project\\SAD_Viva\\user images\\user-profile.png";
                    Database.execute("INSERT INTO `user` (`fname`, `lname`, `mobile`, `username`, `nic`, `password`, `user_type_id`, `gender_id`, `image`) VALUES ('" + fname + "', '" + lname + "', '" + mobile + "', '" + username + "', '" + nic + "', '" + password + "', '" + typeMap.get(type) + "', '" + genderMap.get(gender) + "', '" + path.replace("\\", "\\\\") + "')");
                    JOptionPane.showMessageDialog(this, "User successfully added", "Information", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int check = JOptionPane.showConfirmDialog(this, "Do you want to update user?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (check == JOptionPane.YES_OPTION) {
            if (validation()) {
                String fname = jTextField2.getText();
                String lname = jTextField3.getText();
                String nic = jTextField4.getText();
                String mobile = jTextField5.getText();
                String username = jTextField6.getText();

                try {
                    ResultSet resultSet = Database.execute("SELECT * FROM `user` WHERE `nic` = '" + nic + "' OR `mobile` = '" + mobile + "' AND NOT `id` = '" + this.id + "'");

                    boolean updateStatus = false;

                    if (resultSet.next()) {
                        if (resultSet.getString("id").equals(this.id)) {
                            updateStatus = true;
                        } else {
                            JOptionPane.showMessageDialog(this, "This NIC number and contact number already exsits", "Warning", JOptionPane.WARNING_MESSAGE);
                            updateStatus = false;
                        }
                    } else {
                        updateStatus = true;
                    }
                    if (updateStatus) {
                        Database.execute("UPDATE `user` SET `fname` = '" + fname + "', `lname` = '" + lname + "', `nic` = '" + nic + "', `mobile` = '" + mobile + "', `username` = '" + username + "' WHERE `id` = '" + this.id + "'");
                        JOptionPane.showMessageDialog(this, "User's details successfully updated", "Information", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        this.x = evt.getX();
        this.y = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        this.setLocation(evt.getXOnScreen() - this.x, evt.getYOnScreen() - this.y);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6KeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
