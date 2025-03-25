package net.aluminis.inject.service.provider;

import net.aluminis.inject.api.model.AbstractModule;
import net.fabricmc.loader.api.FabricLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleProvider {
    List<AbstractModule> modules = new ArrayList<>();

    public List<AbstractModule> provideModules() {
        if (modules.isEmpty()) {
            modules = Collections.unmodifiableList(
                    FabricLoader.getInstance().getEntrypoints("aluminis-inject:bootstrap", AbstractModule.class));
        }

        return modules;
    }
}
