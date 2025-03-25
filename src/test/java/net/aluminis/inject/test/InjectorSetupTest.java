package net.aluminis.inject.test;

import net.minecraft.Bootstrap;
import net.minecraft.SharedConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class InjectorSetupTest {
    @BeforeAll
    static void beforeAll() {
        SharedConstants.createGameVersion();
        Bootstrap.initialize();
    }

    @Test
    void testServiceInstantiation() {
        Assertions.assertTrue(true); // TODO Finish unit tests
    }
}
