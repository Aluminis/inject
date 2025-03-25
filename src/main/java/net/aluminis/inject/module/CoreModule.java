package net.aluminis.inject.module;

import com.google.inject.AbstractModule;
import net.aluminis.inject.service.loader.ModuleLoader;
import net.aluminis.inject.service.provider.ModuleProvider;

public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        this.bind(ModuleLoader.class).asEagerSingleton();
        this.bind(ModuleProvider.class).asEagerSingleton();
    }
}
