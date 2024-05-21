/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Model.Database;
import Model.UserSession;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author SINGER
 */
public class Profile extends javax.swing.JPanel {

    private String imgPath, current;
    private File imgFile;
    private HashMap<String, Integer> cityMap = new HashMap<>();
    private Model.User user;

    public Profile() {
        initComponents();
        this.user = UserSession.getInstance().getUser();
        loadCity();
        loadDetails();
        loadAddress();
    }

    private void loadCity() {
        Vector<String> v = new Vector<>();
        v.add("Select");

        try {
            ResultSet rs = Database.execute("SELECT * FROM `city`");
            while (rs.next()) {
                v.add(rs.getString("name"));
                cityMap.put(rs.getString("name"), rs.getInt("id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        DefaultComboBoxModel model = new DefaultComboBoxModel(v);
        jComboBox1.setModel(model);
    }

    private void loadDetails() {
        try {
            ResultSet resultSet = Database.execute("SELECT * FROM `user` INNER JOIN `gender` ON `user`.`gender_id` = `gender`.`id` WHERE `user`.`id` = '" + this.user.getuID() + "'");
            if (resultSet.next()) {
                jLabel3.setIcon(imageAdjust(resultSet.getString("image"), null));
                this.current = resultSet.getString("image");
                jTextField1.setText(resultSet.getString("username"));
                jTextField2.setText(resultSet.getString("fname"));
                jTextField3.setText(resultSet.getString("nic"));
                jTextField4.setText(resultSet.getString("lname"));
                jTextField5.setText(resultSet.getString("name"));
                jTextField6.setText(resultSet.getString("mobile"));
                jPasswordField1.setText(resultSet.getString("password"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
    }

    private void loadAddress() {
        try {
            ResultSet resultSet = Database.execute("SELECT * FROM `user_address` INNER JOIN `city` ON `user_address`.`city_id` = `city`.`id` WHERE `user_id` = '" + this.user.getuID() + "'");
            if (resultSet.next()) {
                jTextField9.setText(resultSet.getString("line1"));
                jTextField10.setText(resultSet.getString("line2"));
                jComboBox1.setSelectedItem(resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void saveFile(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            Path destination = Paths.get("user images", this.user.getuID() + ".jpg");
            Files.createDirectories(destination.getParent());
            Files.write(destination, fileContent);
            this.imgPath = System.getProperty("user.dir") + "\\" + destination;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private ImageIcon imageAdjust(String path, byte[] pic) {
        ImageIcon img = null;
        if (path != null) {
            img = new ImageIcon(path);
        } else {
            img = new ImageIcon(pic);
        }
        Image image = img.getImage();
        Image newImage = image.getScaledInstance((int) jLabel3.getPreferredSize().getWidth(), (int) jLabel3.getPreferredSize().getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    public void reset() {
        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jTextField4.setEditable(false);
        jTextField6.setEditable(false);
        jTextField9.setEditable(false);
        jTextField10.setEditable(false);
        jPasswordField1.setEditable(false);
        jComboBox1.setEnabled(false);
        jButton1.setVisible(true);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
    }

    private boolean validation() {
        if (jTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your username", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField1.requestFocus();
            return false;
        } else if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField2.requestFocus();
            return false;
        } else if (jTextField4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your last name", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField4.requestFocus();
            return false;
        } else if (jTextField6.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your mobile number", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField6.requestFocus();
            return false;
        } else if (!jTextField6.getText().matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Invalid your mobile number (ex:- 07xxxxxxxx)", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField6.requestFocus();
            return false;
        } else if (jTextField9.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your address line 01", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField9.requestFocus();
            return false;
        } else if (jTextField10.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your address line 02", "Warning", JOptionPane.WARNING_MESSAGE);
            jTextField10.requestFocus();
            return false;
        } else if (jComboBox1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select your city", "Warning", JOptionPane.WARNING_MESSAGE);
            jComboBox1.requestFocus();
            return false;
        } else if (String.valueOf(jPasswordField1.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your password", "Warning", JOptionPane.WARNING_MESSAGE);
            jPasswordField1.requestFocus();
            return false;
        } else if (!String.valueOf(jPasswordField1.getPassword()).matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            JOptionPane.showMessageDialog(this, "Invalid user's password (ex:- abcD#123&4)", "Warning", JOptionPane.WARNING_MESSAGE);
            jPasswordField1.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(102, 204, 255));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Profile");
        jLabel2.setOpaque(true);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/user-profile.png"))); // NOI18N
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel3.setPreferredSize(new java.awt.Dimension(280, 280));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Change");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(24, 24, 24)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1483, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(100, 40, 1, 20));
        jPanel5.setLayout(new java.awt.GridLayout(16, 1, 5, 15));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "First Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.add(jTextField2);

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "NIC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.add(jTextField3);

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Address Line 01", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.add(jTextField9);

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "none" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "City", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jComboBox1.setEnabled(false);
        jPanel5.add(jComboBox1);

        jPasswordField1.setEditable(false);
        jPasswordField1.setBackground(new java.awt.Color(255, 255, 255));
        jPasswordField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(0, 0, 0));
        jPasswordField1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.add(jPasswordField1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel2);

        jPanel4.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(100, 20, 1, 40));
        jPanel6.setLayout(new java.awt.GridLayout(16, 1, 5, 15));

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Last Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel6.add(jTextField4);

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Mobile Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });
        jPanel6.add(jTextField6);

        jTextField10.setEditable(false);
        jTextField10.setBackground(new java.awt.Color(255, 255, 255));
        jTextField10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField10.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Address Line 02", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel6.add(jTextField10);

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Gender", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel6.add(jTextField5);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Update Details");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Save");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 0, 51));
        jButton4.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Cancel");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel9);

        jPanel8.setOpaque(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel8);

        jPanel4.add(jPanel6);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextField1.setEditable(true);
        jTextField2.setEditable(true);
        jTextField4.setEditable(true);
        jTextField6.setEditable(true);
        jTextField9.setEditable(true);
        jTextField10.setEditable(true);
        jPasswordField1.setEditable(true);
        jComboBox1.setEnabled(true);
        jButton1.setVisible(false);
        jButton2.setVisible(true);
        jButton3.setVisible(true);
        jButton4.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Image");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.imgFile = selectedFile;
            String path = selectedFile.getAbsolutePath();
            jLabel3.setIcon(imageAdjust(path, null));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Do you want to update your details?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            String username = jTextField1.getText();
            String fname = jTextField2.getText();
            String lname = jTextField4.getText();
            String mobile = jTextField6.getText();
            String line1 = jTextField9.getText();
            String line2 = jTextField10.getText();
            String city = String.valueOf(jComboBox1.getSelectedItem());
            String password = String.valueOf(jPasswordField1.getPassword());
            boolean canUpdate = false;
            boolean address = false;
            String query = null;

            if (validation()) {
                query = "UPDATE `user` SET `fname` = '" + fname + "', `lname` = '" + lname + "', `mobile` = '" + mobile + "', `password` = '" + password + "', `username` = '" + username + "'";
                if (imgFile != null) {
                    saveFile(this.imgFile);
                    query += ", `image` = '" + this.imgPath.replace("\\", "\\\\") + "'";
                }
                query += " WHERE `id` = '" + this.user.getuID() + "'";
                canUpdate = true;
                address = true;
            } else {
                canUpdate = false;
                address = false;
            }
            if (canUpdate) {
                if (address) {
                    try {
                        ResultSet resultSet = Database.execute("SELECT * FROM `user_address` WHERE `id` = '" + this.user.getuID() + "'");
                        if (resultSet.next()) {
                            Database.execute("UPDATE `user_address` SET `line1` = '" + line1 + "', `line2` = '" + line2 + "', `city_id` = '" + cityMap.get(city) + "' WHERE `user_id` = '" + this.user.getuID() + "'");
                        } else {
                            Database.execute("INSERT INTO `user_address` (`line1`, `line2`, `city_id`, `user_id`) VALUES ('" + line1 + "', '" + line2 + "', '" + cityMap.get(city) + "', '" + this.user.getuID() + "')");
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                try {
                    Database.execute(query);
                    JOptionPane.showMessageDialog(this, "User's details successfully updated", "Information", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                    loadAddress();
                    loadDetails();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else {
            jLabel3.setIcon(imageAdjust(current, null));
            reset();
            loadAddress();
            loadDetails();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        reset();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        String mobile = jTextField6.getText();
        String text = mobile + evt.getKeyChar();

        if (mobile.length() == 10) {
            evt.consume();
        } else if (!Pattern.compile("[0-9]+").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField6KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
