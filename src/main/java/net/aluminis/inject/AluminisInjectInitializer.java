package net.aluminis.inject;

import com.google.inject.Guice;
import net.aluminis.inject.module.CoreModule;
import net.fabricmc.api.ModInitializer;

public class AluminisInjectInitializer implements ModInitializer {
	/**
	 * Mod Identifier
	 */
	public static final String MOD_ID = "aluminis-inject";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onInitialize() {
		Guice.createInjector(new CoreModule());
	}
}