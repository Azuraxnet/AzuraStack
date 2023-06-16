package net.azura.version;

public interface VersionRegistry<V,E> {
    void register(V Version,Version<E> version);
    Version<E> getVersion(V version);
}
