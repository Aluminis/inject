package net.aluminis.inject.module;

import com.google.inject.AbstractModule;
import net.aluminis.inject.api.annotation.AutoLoad;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.impl.ModContainerImpl;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static net.aluminis.inject.AluminisInjectInitializer.MOD_ID;

public class CoreModule extends AbstractModule {
    public final List<Class<?>> classesToAutoLoad = new ArrayList<>();

    @Override
    protected void configure() {
        for (ModContainer modContainer : FabricLoader.getInstance().getAllMods()) {
            if (modContainer instanceof ModContainerImpl) {
                for (Path path : ((ModContainerImpl) modContainer).getCodeSourcePaths()) {
                    File file = path.toFile();


                    if (file.isDirectory()) findClasses(file, file.getAbsolutePath()).forEach(this::validateClassAndAutoload);
                    else if (file.getName().endsWith(".jar")) {
                        try (JarFile jar = new JarFile(path.toFile())) {
                            Enumeration<JarEntry> entries = jar.entries();
                            while (entries.hasMoreElements()) {
                                JarEntry entry = entries.nextElement();
                                if (entry.getName().endsWith(".class")) {
                                    String className = entry.getName()
                                            .replace('/', '.')
                                            .replace(".class", "");
                                    Class<?> clazz = Class.forName(className);
                                    validateClassAndAutoload(clazz);
                                }
                            }
                        } catch (ExceptionInInitializerError | NoClassDefFoundError | Exception ignored) {}
                    }
                }
            }
        }
    }

    private void validateClassAndAutoload(Class<?> clazz) {
        if (clazz.isAnnotationPresent(AutoLoad.class)) {
            classesToAutoLoad.add(clazz);
            LoggerFactory.getLogger(MOD_ID).info(clazz.getName() + " marked for autoloading");
            bind(clazz).asEagerSingleton();
        }
    }

    private List<Class<?>> findClasses(File fileOrDirectory, String baseDir) {
        List<Class<?>> classes = new ArrayList<>();
        if (!fileOrDirectory.exists()) return classes;

        File[] files = fileOrDirectory.listFiles();

        if (files == null) files = new File[]{fileOrDirectory};

        for (File file : files) {
            if (file.isDirectory()) classes.addAll(findClasses(file, baseDir));
            else if (file.getName().endsWith(".class")) {
                String dottedBaseDir = baseDir.replace('/', '.')
                        .replace("\\", ".");

                String className = file.getAbsolutePath()
                        .replace('/', '.')
                        .replace("\\", ".")
                        .replace(dottedBaseDir, "")
                        .replaceFirst(".", "")
                        .replace(".class", "");

                try {
                    Class<?> clazz = Class.forName(className);

                    classes.add(clazz);
                } catch (ClassNotFoundException | RuntimeException ignored) {}
            }
        }

        return classes;
    }
}
