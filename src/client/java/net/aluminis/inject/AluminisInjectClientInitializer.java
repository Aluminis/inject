package net.aluminis.inject;

import com.google.inject.Guice;
import net.aluminis.inject.module.CoreModule;
import net.aluminis.inject.service.loader.ModuleLoader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;

import static net.aluminis.inject.AluminisInjectInitializer.*;

public class AluminisInjectClientInitializer implements ClientModInitializer {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STARTED .register(server -> {
			setCoreInjector(Guice.createInjector(new CoreModule()));
			getCoreInjector().getInstance(ModuleLoader.class).loadModules();
		});
	}
}