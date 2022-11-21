/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.trkgrn_theomer.bigdatathresholding.ui.Test;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Complaint;
import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.Threshold;
import com.trkgrn_theomer.bigdatathresholding.api.service.ComplaintService;
import com.trkgrn_theomer.bigdatathresholding.api.thread.TestThread;
import com.trkgrn_theomer.bigdatathresholding.api.thread.ThresholdThread;
import com.trkgrn_theomer.bigdatathresholding.api.utility.CSVUtil;
import com.trkgrn_theomer.bigdatathresholding.api.utility.StringUtil;
import com.trkgrn_theomer.bigdatathresholding.api.utility.TableUtil;
import com.trkgrn_theomer.bigdatathresholding.api.utility.ThreadUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * @author trkgrn
 */
@Component
public class Deneme extends javax.swing.JFrame {

    /**
     * Creates new form Deneme
     */

    private final CSVUtil csvUtil;
    private final StringUtil stringUtil;
    private final ComplaintService complaintService;
    private final ThreadUtil threadUtil;
    private final TableUtil tableUtil;

    public Deneme(CSVUtil csvUtil, StringUtil stringUtil, ComplaintService complaintService, ThreadUtil threadUtil, TableUtil tableUtil) {
        this.csvUtil = csvUtil;
        this.stringUtil = stringUtil;
        this.complaintService = complaintService;
        this.threadUtil = threadUtil;
        this.tableUtil = tableUtil;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        runButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        runButton1 = new javax.swing.JButton();
        runButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        threadCount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        runButton.setText("RUN");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            @SneakyThrows
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        runButton1.setText("RUN1");
        runButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButton1ActionPerformed(evt);
            }
        });

        runButton2.setText("RUN2");
        runButton2.addActionListener(new java.awt.event.ActionListener() {
            @SneakyThrows
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Kayıt1", "Kayıt2", "Benzerlik Oranı"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(runButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(runButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(76, 76, 76)
                                                .addComponent(threadCount, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(54, 54, 54)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(26, 26, 26)
                                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(51, 51, 51)
                                                                .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(37, 37, 37)
                                                .addComponent(runButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(runButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(47, 47, 47)
                                                .addComponent(threadCount, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_runButtonActionPerformed
        List<Complaint> allData = complaintService.getAll();

        long start = System.currentTimeMillis();
        List<Complaint> complaints = stringUtil.getComplaintsByProperty(allData,"company","TRANSUNION INTERMEDIATE HOLDINGS");
        complaints.removeIf(Objects::isNull);
        System.out.println("List:" +(System.currentTimeMillis() - start) + "  Size: "+complaints.size());

        start = System.currentTimeMillis();
        List<Complaint> complaints2 = stringUtil.getComplaintsByPropertyWithSet(allData,"Company","TRANSUNION INTERMEDIATE HOLDINGS");
        System.out.println("Set:" +(System.currentTimeMillis() - start) + "  Size: "+complaints2.size());



//       List<String> products = stringUtil.distinctColumnWithSet(allData,"Company");
//        System.out.println("Set:" +(System.currentTimeMillis() - start) + "  Size: "+products.size());
//
//        start = System.currentTimeMillis();
//        List<String> products2  = stringUtil.distinctColumn(allData,"company");
//        System.out.println("List:" +(System.currentTimeMillis() - start) + "  Size: "+products2.size());
//
//        start = System.currentTimeMillis();
//        List<String> products3  = complaintService.getCompanies();
//        System.out.println("Database:" +(System.currentTimeMillis() - start) + "  Size: "+products3.size());



           //     System.out.println(products);

    }//GEN-LAST:event_runButtonActionPerformed

    private void runButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int threadCount = 1000; // 1-> 9.4M ,  10-> 4.5M, 100-> 5.8M , 1000-> 4.7M
        List<Complaint> allData = complaintService.getAll();
        List<List<Complaint>> jobs = threadUtil.splitComplaints(allData, threadCount);
        List<TestThread> threads = new ArrayList<>();

        TestThread.allData = allData;
        for (int i = 0; i < threadCount; i++) {
            TestThread thread = new TestThread("TestThread" + i, stringUtil);
            thread.setPartData(jobs.get(i));
            threads.add(thread);
        }

        threads.parallelStream().forEach(thread -> {
            thread.start();
        });

        threadUtil.printProcessTimeByThreads2(threads);

        List<Threshold> similarityAverages = new ArrayList<>();
        threads.stream().forEach(thread -> {
            similarityAverages.addAll(thread.getThresholds());
        });

       // tableUtil.showTableS3(jTable1, similarityAverages);


    }

    private void runButton2ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_runButton2ActionPerformed
          // csvUtil.createRegularData();

//        long start = System.currentTimeMillis();
//        start = System.currentTimeMillis();
//        List<Complaint> complaints = complaintService.getAll();
//        System.out.println("Postgre: "+complaints.size()+"  Süre:" +(System.currentTimeMillis()-start));


        int threadCount = Integer.parseInt(this.threadCount.getText());
        List<Complaint> allData = complaintService.getAll();
        Complaint complaint = complaintService.getComplaintById("2433219");
        List<List<Complaint>> jobs = threadUtil.splitComplaints(allData, threadCount);
        List<ThresholdThread> threads = new ArrayList<>();
        ThresholdThread.complaint = complaint;

        for (int i = 0; i < threadCount; i++) {
            ThresholdThread thread = new ThresholdThread("ThresholdThread" + i, stringUtil);
            thread.setPartData(jobs.get(i));
            threads.add(thread);
        }

        threads.parallelStream().forEach(thread -> {
            thread.start();
        });

        threadUtil.printProcessTimeByThreads(threads);

        List<Threshold> similarityAverages = new ArrayList<>();
        threads.stream().forEach(thread -> {
            similarityAverages.addAll(thread.getThresholds());
        });

     //   tableUtil.showTableS3(jTable1, similarityAverages);

    }//GEN-LAST:event_runButton2ActionPerformed


    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton runButton;
    private javax.swing.JButton runButton1;
    private javax.swing.JButton runButton2;
    private javax.swing.JTextField threadCount;
    // End of variables declaration//GEN-END:variables
}
