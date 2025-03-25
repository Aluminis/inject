package net.aluminis.inject.test.module;

import com.google.inject.Provides;
import net.aluminis.inject.api.annotation.ModId;
import net.aluminis.inject.api.model.AbstractModule;
import org.slf4j.LoggerFactory;

import static net.aluminis.inject.AluminisInjectInitializer.MOD_ID;

public class TestModule extends AbstractModule {

    @Provides
    @ModId
    public String provideModId() {
        return "aluminis-inject";
    }

    public TestModule() {
        LoggerFactory.getLogger(MOD_ID).info("TestModuleInit worked!");
    }
}
