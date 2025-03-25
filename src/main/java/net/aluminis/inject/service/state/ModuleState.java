package net.aluminis.inject.service.state;

import com.google.inject.Injector;

import java.util.HashMap;
import java.util.Map;

public class ModuleState {

    public final Map<String, Injector> scopedInjectors = new HashMap<>();
}
