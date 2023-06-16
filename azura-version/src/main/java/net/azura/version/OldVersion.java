package net.azura.version;

import net.azura.version.container.Versioned;
import net.azura.version.types.MinecraftVersion;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class OldVersion<E extends Versioned> {
    private E element;
    private OldVersionRegistry<E> registry;
    private Supplier<E> supplier;
    public OldVersion(String version, Class<E> clazz){
        this.registry = VersionRegistryManager.getRegistry(clazz);
        this.element = registry.getElementVerison(version).getElement();
    }
    public OldVersion(E element, Class<E> clazz){
        this.registry = VersionRegistryManager.getRegistry(clazz);
        this.element = element;
        this.registry.register(this);
    }
    public OldVersion(Supplier<E> supplier, Class<E> clazz){
        this.registry = VersionRegistryManager.getRegistry(clazz);
        this.supplier = supplier;
        this.registry.register(this);
    }

    public OldVersion<E> convert(String version){
        return registry.getElementVerison(version);
    }

    public E getElement() {
        if(element != null){
            return element;
        }
        if(supplier != null){
           return supplier.get();
        }
        return registry.getElementVerison(MinecraftVersion.LATEST.getVersion()).getElement();
    }

    public String getVersion() {
        return element.getVersion();
    }
    public static <E extends Versioned> void registerVersion(E element, Class<E> clazz){
        new OldVersion(element, clazz);
    }
    public static <E extends Versioned> E get(Class<E> clazz, String version) {
        OldVersionRegistry<E> manager = VersionRegistryManager.getRegistry(clazz);
        return manager.getElementVerison(version).getElement();
    }

    private static class OldVersionRegistry<E extends Versioned>{
        private Map<String, OldVersion<E>> versionMap = new HashMap<>();


        public void register(OldVersion<E> oldVersion){
            if(oldVersion == null){
                throw new IllegalArgumentException("Version cannot be null");
            }
            System.out.println("Registering: " + oldVersion.getVersion() + " " + oldVersion.element);
            versionMap.put(oldVersion.getVersion(), oldVersion);
        }
        public OldVersion<E> getElementVerison(String version){
            if(version == null){
                return null;
            }
            return versionMap.containsKey(version) ? versionMap.get(version) : null;
        }
    }

    public static class VersionRegistryManager {
        private static final Map<Class<?>, OldVersionRegistry<?>> REGISTRIES = new HashMap<>();
        @SuppressWarnings("unchecked")
        public static <E extends Versioned> OldVersionRegistry<E> getRegistry(Class<E> clazz){
            return (OldVersionRegistry<E>) REGISTRIES.computeIfAbsent(clazz, k -> new OldVersionRegistry<>());
        }
    }
}
