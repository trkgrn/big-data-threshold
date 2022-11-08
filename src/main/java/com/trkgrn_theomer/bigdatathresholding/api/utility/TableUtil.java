package com.trkgrn_theomer.bigdatathresholding.api.utility;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Threshold;
import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.SimilarityAverage;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

@Component
public class TableUtil {
    static int count = 0;
    public void showTable(JTable table, List<SimilarityAverage> similarityAverages) {
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
            row[1] = similarityAverage.getData1();
            row[2] = similarityAverage.getData2();
            row[3] = similarityAverage.getAverage();
            model.addRow(row);
        });

        table.removeAll();
        table.setModel(model);
    }


}
