package net.aluminis.inject.service.loader;


import com.google.inject.Inject;
import net.aluminis.inject.service.provider.ModuleProvider;
import net.aluminis.inject.service.state.ModuleState;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.ModContainerImpl;

import static java.lang.Class.forName;
import static net.aluminis.inject.AluminisInjectInitializer.getCoreInjector;

public class ModuleLoader {
    @Inject
    public ModuleProvider moduleProvider;
    @Inject
    public ModuleState moduleState;

    public void loadModules() {
        moduleProvider.provideModules().forEach(module -> {

            ModContainerImpl modContainer = ((ModContainerImpl) FabricLoader.getInstance().getModContainer(module.provideModId()).orElseThrow());
            modContainer.getMetadata().getEntrypointKeys().forEach(entrypointKey -> {
                modContainer.getMetadata().getEntrypoints(entrypointKey).forEach(entrypoint -> {
                    try {
                        Class<?> clazz = forName(entrypoint.getValue());
                        Object object = getCoreInjector().getInstance(clazz);

                        if (object instanceof ModInitializer) {
                            ((ModInitializer) object).onInitialize();
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }


                });
            });

            moduleState.scopedInjectors.put(
                    module.provideModId(),
                    getCoreInjector().createChildInjector(module)
            );
        });
    }

}
