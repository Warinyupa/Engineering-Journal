/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data.TimeLog;
import database.SQLConnection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class OverviewStudent extends javax.swing.JFrame {
DefaultTableModel model = new DefaultTableModel();
     Object[] data = new Object[0];
    /**
     * Creates new form Overview
     */
    public void clearTable(){
         int i = table.getRowCount()-1;
         while (i > -1) {
             model.removeRow(i);
             i--;
         }
     }
    
    public OverviewStudent() throws SQLException {
        initComponents();
        CurrentDate();
        model = (DefaultTableModel)table.getModel();
        clearTable();
        
        Date d1 = new Date(),d2=new Date();
        SQLConnection MyCon = new SQLConnection();
        Connection c = MyCon.getConnection("journal");
        Statement stmt = c.createStatement();
        String name = MyCon.getUsername();
        String SQL = "select * from "+name+"_timelog order by dates;";
        ResultSet rs = stmt.executeQuery(SQL);
        while(rs.next()){
            d2 = rs.getDate(1);
            break;
        }
        SQL = "select * from "+name+"_timelog order by dates;";
        rs = stmt.executeQuery(SQL);
        TimeLog timelog1 = new TimeLog(),timelog2= new TimeLog();
        double result1=0,result2=0,result3=0,result4=0,result5=0;
        
        //d2 = {2011,11,5};
        int i=0,j=0;
        timelog1.setActivities("dd");
        timelog1.setDate(d2);
                    while(rs.next()){
                        
                            timelog2 = timelog1;
                            j++;
                        if(j == 1){
                            d2.setDate(d2.getDate()-1);
                        }
                        timelog1.setDate(rs.getDate(1));
                        timelog1.setRealTime(rs.getDouble(5));
                        timelog1.setActivities(rs.getString(6));
                        
                        if(d2.getYear() == rs.getDate(1).getYear() && d2.getMonth() == rs.getDate(1).getMonth() && d2.getDate() == rs.getDate(1).getDate()){
                            System.out.println(1);
                            
                            if(timelog1.getActivities().equals(timelog2.getActivities()) && timelog1.getActivities().equals("Homework")){
                                result1 += timelog1.getRealTime();
                            }else if(timelog1.getActivities().equals(timelog2.getActivities()) && timelog1.getActivities().equals("Study")){
                                result2 += timelog1.getRealTime();
                            }else if(timelog1.getActivities().equals(timelog2.getActivities()) && timelog1.getActivities().equals("Activities")){
                                result3 += timelog1.getRealTime();
                            }else if(timelog1.getActivities().equals(timelog2.getActivities()) && timelog1.getActivities().equals("Reading")){
                                result4 += timelog1.getRealTime();
                            }else if(timelog1.getActivities().equals(timelog2.getActivities()) && timelog1.getActivities().equals("Other")){
                                result5 += timelog1.getRealTime();
                            }
                            
                            
                        }else{
                            
                            
                            d1 = timelog1.getDate(); d2= rs.getDate(1);

                        
                            model.addRow(data);
                            table.setValueAt(timelog1.getDate(),i,0);
                           if(timelog1.getActivities().equals(timelog2.getActivities()) && timelog1.getActivities().equals("Homework")){
                                result1 += timelog1.getRealTime();
                            }else if(timelog1.getActivities().equals(timelog2.getActivities()) && timelog1.getActivities().equals("Study")){
                                result2 += timelog1.getRealTime();
                            }else if(timelog1.getActivities().equals(timelog2.getActivities()) && timelog1.getActivities().equals("Activities")){
                                result3 += timelog1.getRealTime();
                            }else if(timelog1.getActivities().equals(timelog2.getActivities()) && timelog1.getActivities().equals("Reading")){
                                result4 += timelog1.getRealTime();
                            }else if(timelog1.getActivities().equals(timelog2.getActivities()) && timelog1.getActivities().equals("Other")){
                                result5 += timelog1.getRealTime();
                            }
                            table.setValueAt(timelog1.getDate(),i,0);
                            table.setValueAt(result1,i, 1);
                            table.setValueAt(result2,i, 2);
                            table.setValueAt(result3,i, 3);
                            table.setValueAt(result4,i, 4);
                            table.setValueAt(result5,i++, 5);
                            
                            result1 = 0;
                            result2 = 0;
                            result3 = 0;
                            result4 = 0;
                            result5 = 0;
                        }
                        
                    }
                        
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        Date_day = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Overview");
        setBounds(new java.awt.Rectangle(400, 150, 0, 0));
        setMinimumSize(new java.awt.Dimension(600, 450));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(600, 450));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 450));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Today ");

        jButton2.setText("Table");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date   ", "Homework ", "Study  ", "Activities   ", "Reading", "Other"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(table);

        jButton4.setBackground(new java.awt.Color(102, 204, 255));
        jButton4.setText("Overview");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Home page");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        Date_day.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Date_day.setText("Date");

        jButton6.setForeground(new java.awt.Color(255, 0, 0));
        jButton6.setText("Log out");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Date_day, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Date_day))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-616)/2, (screenSize.height-488)/2, 616, 488);
    }// </editor-fold>//GEN-END:initComponents

    public void CurrentDate(){
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Date_day.setText(day+" / "+(month+1)+" / "+year);        
  
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new Timelog().show();
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(OverviewStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        new HomePageStudent().show();
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            new Login().show();
            this.dispose();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OverviewStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OverviewStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OverviewStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OverviewStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OverviewStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OverviewStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new OverviewStudent().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(OverviewStudent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date_day;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
