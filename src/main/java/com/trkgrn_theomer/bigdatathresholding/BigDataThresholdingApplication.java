package com.trkgrn_theomer.bigdatathresholding;

import com.trkgrn_theomer.bigdatathresholding.ui.Test.Deneme;
import com.trkgrn_theomer.bigdatathresholding.ui.threshold.ThresholdUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BigDataThresholdingApplication implements CommandLineRunner {

    private final Deneme deneme;
    private final ThresholdUI thresholdUI;

    public BigDataThresholdingApplication(Deneme deneme, ThresholdUI thresholdUI) {
        this.deneme = deneme;
        this.thresholdUI = thresholdUI;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(BigDataThresholdingApplication.class).headless(false).run(args);
    }

    @Override
    public void run(String... args) {
//        deneme.setVisible(true);
        thresholdUI.setVisible(true);
    }

}

