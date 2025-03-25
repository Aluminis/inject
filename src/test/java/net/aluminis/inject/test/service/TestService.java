package net.aluminis.inject.test.service;

import com.google.inject.Inject;
import net.aluminis.inject.api.annotation.AutoLoad;
import net.aluminis.inject.api.annotation.ModId;
import org.slf4j.LoggerFactory;

@AutoLoad()
public class TestService {
    @Inject
    public TestService(@ModId String modId) {
        LoggerFactory.getLogger(modId).info("TestService worked!");
    }
}
