package net.aluminis.inject.service.loader;


import com.google.inject.Inject;
import net.aluminis.inject.AluminisInjectInitializer;
import net.aluminis.inject.service.provider.ModuleProvider;
import net.aluminis.inject.service.state.ModuleState;

public class ModuleLoader {
    @Inject
    public ModuleProvider moduleProvider;
    @Inject
    public ModuleState moduleState;

    public void loadModules() {
        moduleProvider.provideModules().forEach(module -> moduleState.scopedInjectors.put(
                module.provideModId(),
                AluminisInjectInitializer.getCoreInjector().createChildInjector(module)
        ));
    }

}
