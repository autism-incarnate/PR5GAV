package framework.runner;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameters({
        @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/framework/features"),
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "framework.steps"),
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value="io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm, pretty")
})
public class TestRunner {
}
