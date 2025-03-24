package net.aluminis.inject.service;

import net.aluminis.inject.api.annotation.AutoLoad;
import org.slf4j.LoggerFactory;

import static net.aluminis.inject.AluminisInjectInitializer.MOD_ID;

@AutoLoad
public class TestService {
    public TestService() {
        LoggerFactory.getLogger(MOD_ID).info("TestService worked!");
    }
}
