package com.trkgrn_theomer.bigdatathresholding;

import com.trkgrn_theomer.bigdatathresholding.ui.Test.Deneme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BigDataThresholdingApplication implements CommandLineRunner {

    private final Deneme deneme;

    public BigDataThresholdingApplication(Deneme deneme) {
        this.deneme = deneme;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(BigDataThresholdingApplication.class).headless(false).run(args);
    }

    @Override
    public void run(String... args) {
        deneme.setVisible(true);
    }

}

