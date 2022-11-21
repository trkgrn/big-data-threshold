package com.trkgrn_theomer.bigdatathresholding.api.utility;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Complaint;
import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.Threshold;
import com.trkgrn_theomer.bigdatathresholding.api.thread.ThresholdThread;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

@Component
public class TableUtil {
    static int count = 0;
    private final ThreadUtil threadUtil;

    public TableUtil(ThreadUtil threadUtil) {
        this.threadUtil = threadUtil;
    }

    public void showTableS3(JTable table, List<Threshold> similarityAverages, String selectedColumn) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
              "#" , "Kayıt-1",selectedColumn, "Kayıt-2",selectedColumn, "Benzerlik Oranı"
        });
        Object[] row = new Object[6];

        count = 0;
        switch (selectedColumn){
            case "Company":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getCompany();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getCompany();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
            case "Product":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getProduct();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getProduct();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
            case "Issue":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getIssue();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getIssue();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
            case "State":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getState();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getState();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
            case "ZIP Code":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getZipCode();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getZipCode();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
        }

        table.removeAll();
        table.setModel(model);
    }

    public void showThreadTable(JTable table,List<ThresholdThread> threads){
        threadUtil.waitAllThread(threads);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
                "#", "Thread ID", "Thread Adı", "Çalışma Süresi"
        });
        Object[] row = new Object[4];
        count = 0;
        threads.forEach(thread -> {
            count++;
            row[0] = count;
            row[1] = thread.getId();
            row[2] = thread.getName();
            row[3] = thread.getTotalTime();
            model.addRow(row);
        });
        table.removeAll();
        table.setModel(model);
    }

    public void showTableS1(JTable table, List<Threshold> similarityAverages, String selectedColumn){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
                "#" , "Kayıt-1",selectedColumn, "Kayıt-2",selectedColumn, "Benzerlik Oranı"
        });
        Object[] row = new Object[6];

        count = 0;
        switch (selectedColumn){
            case "Company":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getCompany();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getCompany();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
            case "Product":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getProduct();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getProduct();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
            case "Issue":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getIssue();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getIssue();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
            case "State":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getState();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getState();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
            case "ZIP Code":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getZipCode();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getZipCode();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
        }

        table.removeAll();
        table.setModel(model);
    }
    public void addRowTableS1(JTable table,List<Threshold> similarityAverages, String selectedColumn){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[6];
        switch (selectedColumn){
            case "Company":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getCompany();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getCompany();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
            case "Product":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getProduct();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getProduct();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
            case "Issue":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getIssue();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getIssue();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
            case "State":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getState();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getState();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
            case "ZIP Code":
                similarityAverages.forEach(similarityAverage -> {
                    count++;
                    row[0] = count;
                    row[1] = similarityAverage.getOriginComplaint().getComplaintId();
                    row[2] = similarityAverage.getOriginComplaint().getZipCode();
                    row[3] = similarityAverage.getDestinationComplaint().getComplaintId();
                    row[4] = similarityAverage.getDestinationComplaint().getZipCode();
                    row[5] = similarityAverage.getSimilarityAverage();
                    model.addRow(row);
                });
                break;
        }

        table.setModel(model);
    }

    public void showTableS2(JTable table, List<Threshold> similarityAverages, String selectedColumn,String selectedColumn2, String desiredColumn){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
                "#" , selectedColumn+"-1",selectedColumn+"-2", selectedColumn2+"-1",selectedColumn2+ "-2",desiredColumn+"-1",desiredColumn+ "-2"
        });
        Object[] row = new Object[7];

        count = 0;
        similarityAverages.forEach(similarityAverage -> {
            count++;
            row[0] = count;
            row[1] = getProperty(similarityAverage.getOriginComplaint(),selectedColumn);
            row[2] = getProperty(similarityAverage.getDestinationComplaint(),selectedColumn);;
            row[3] = getProperty(similarityAverage.getOriginComplaint(),selectedColumn2);
            row[4] = getProperty(similarityAverage.getDestinationComplaint(),selectedColumn2);
            row[5] = getProperty(similarityAverage.getOriginComplaint(),desiredColumn);
            row[6] = getProperty(similarityAverage.getDestinationComplaint(),desiredColumn);
            model.addRow(row);
        });

        table.removeAll();
        table.setModel(model);
    }
    public void addRowTableS2(JTable table,List<Threshold> similarityAverages, String selectedColumn,String selectedColumn2, String desiredColumn){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[7];

        similarityAverages.forEach(similarityAverage -> {
            count++;
            row[0] = count;
            row[1] = getProperty(similarityAverage.getOriginComplaint(),selectedColumn);
            row[2] = getProperty(similarityAverage.getDestinationComplaint(),selectedColumn);;
            row[3] = getProperty(similarityAverage.getOriginComplaint(),selectedColumn2);
            row[4] = getProperty(similarityAverage.getDestinationComplaint(),selectedColumn2);
            row[5] = getProperty(similarityAverage.getOriginComplaint(),desiredColumn);
            row[6] = getProperty(similarityAverage.getDestinationComplaint(),desiredColumn);
            model.addRow(row);
        });

        table.setModel(model);
    }
    public String getProperty(Complaint complaint, String selectedColumn){
        String data = "";
        switch (selectedColumn){
            case "Company":
                data = complaint.getCompany();
                break;
            case "Product":
                data = complaint.getProduct();
                break;
            case "Issue":
                data = complaint.getIssue();
                break;
            case "State":
                data = complaint.getState();
                break;
            case "ZIP Code":
                data = complaint.getZipCode();
                break;
            case "Complaint ID":
                data = complaint.getComplaintId();
        }
        return data;
    }

}
