package com.trkgrn_theomer.bigdatathresholding.api.utility;

import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.Threshold;
import com.trkgrn_theomer.bigdatathresholding.api.thread.ThresholdThread;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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


}
