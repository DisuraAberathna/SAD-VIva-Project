/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Model.User;
import Model.UserSession;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author SINGER
 */
public class Dashboard extends javax.swing.JFrame {

    private int width;
    private AdminView adminView;
    private UserView userView;
    private Model.User user;

    private void setUser() {
        this.user = UserSession.getInstance().getUser();
        jLabel1.setText(this.user.getFname() + " " + this.user.getLname());

        if (this.user.getuType() == 2) {
            setUserView(new UserView(this));
            jPanel2.add(getUserView(), BorderLayout.CENTER);
            jButton1.setVisible(false);
        } else {
            setAdminView(new AdminView(this));
            jPanel2.add(getAdminView(), BorderLayout.CENTER);
        }
    }

    public User getUser() {
        return user;
    }

    private UserView getUserView() {
        return userView;
    }

    private void setUserView(UserView userView) {
        this.userView = userView;
    }

    public JPanel getjPanel3() {
        return jPanel3;
    }

    private AdminView getAdminView() {
        return adminView;
    }

    private void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }

    public Dashboard() {
        initComponents();

        jPanel3.add(new Home(), BorderLayout.CENTER);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jPanel1.requestFocus();
        clock();
        setUser();

        Timer timer = new Timer(1000, e -> clock());
        timer.start();
    }

    private void clock() {
        Date currentTime = new Date();
        Date currentDate = new Date();

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String formattedTime = timeFormat.format(currentTime);
        String formattedDate = dateFormat.format(currentDate);

        jLabel2.setText(formattedDate + "   " + formattedTime);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(371, 48));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/ringing.png"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setPreferredSize(new java.awt.Dimension(48, 35));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Welcome, User");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("2024/05/08 11:11");

        jToggleButton1.setBackground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/menu.png"))); // NOI18N
        jToggleButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 526, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
            .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 367));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if (jToggleButton1.isSelected()) {
            width = jPanel2.getWidth();

            if (width == jPanel2.getWidth()) {
                if (this.user.getuType() == 2) {
                    userView.removeText();
                } else {
                    adminView.removeText();
                }
                Thread t = new Thread(() -> {
                    for (int i = this.width; i >= 80; i -= 10) {
                        jPanel2.setPreferredSize(new Dimension(i, jPanel2.getHeight()));
                        SwingUtilities.updateComponentTreeUI(jPanel2);
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                });
                t.start();
            }
        } else {
            if (jPanel2.getWidth() == 80) {
                Thread t = new Thread(() -> {
                    for (int i = 80; i <= this.width; i += 10) {
                        jPanel2.setPreferredSize(new Dimension(i, jPanel2.getHeight()));
                        SwingUtilities.updateComponentTreeUI(jPanel2);
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                });
                t.start();
                if (this.user.getuType() == 2) {
                    userView.addText();
                } else {
                    adminView.addText();
                }
            }
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
