package net.aluminis.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.aluminis.inject.module.CoreModule;
import net.aluminis.inject.service.loader.ModuleLoader;
import net.fabricmc.api.ModInitializer;

public class AluminisInjectInitializer implements ModInitializer {
	/**
	 * Mod Identifier
	 */
	public static final String MOD_ID = "aluminis-inject";

	private static Injector coreInjector;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onInitialize() {
		coreInjector = Guice.createInjector(new CoreModule());
		coreInjector.getInstance(ModuleLoader.class).loadModules();
	}

	public static void setCoreInjector(Injector injector) {
		if (coreInjector == null) coreInjector = injector;
	}

	public static Injector getCoreInjector() {
		return coreInjector;
	}
}