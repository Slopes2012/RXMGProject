package com.rxmg.step_definitions;

import com.rxmg.utils.Driver;
import io.cucumber.java.After;

public class Hooks {
    //Created this class to close the driver's instance after each test so we can start fresh in the next test.
    @After
    public void tearDownScenario(){
        System.out.println("Tear down steps are being applied");
        Driver.closeDriver();
    }
}
