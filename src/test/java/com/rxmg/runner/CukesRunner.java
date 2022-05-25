package com.rxmg.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //html report - Visualize it via target folder
        plugin = "html:target/cucumber-report.html",
        features = "src/test/resources/features",
        glue = "com/rxmg/step_definitions",
        dryRun = false,
        tags = ""
)
public class CukesRunner {
}