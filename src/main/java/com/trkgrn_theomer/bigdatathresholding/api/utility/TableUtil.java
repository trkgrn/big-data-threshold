package com.trkgrn_theomer.bigdatathresholding.api.utility;

import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.SimilarityAverage;
import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.Threshold;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

@Component
public class TableUtil {
    static int count = 0;
    public void showTable(JTable table, List<Threshold> similarityAverages) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
              "#" , "Kayıt-1", "Kayıt-2", "Benzerlik Oranı"
        });
        Object[] row = new Object[4];

        count = 0;
        similarityAverages.forEach(similarityAverage -> {
            count++;
            row[0] = count;
            row[1] = similarityAverage.getOriginComplaint().getCompany();
            row[2] = similarityAverage.getDestinationComplaint().getCompany();
            row[3] = similarityAverage.getSimilarityAverage();
            model.addRow(row);
        });

        table.removeAll();
        table.setModel(model);
    }


}
