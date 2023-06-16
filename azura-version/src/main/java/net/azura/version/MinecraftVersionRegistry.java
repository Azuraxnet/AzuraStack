package net.azura.version;

import net.azura.version.container.Versioned;
import net.azura.version.types.MinecraftVersion;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

public class MinecraftVersionRegistry<E> implements VersionRegistry<MinecraftVersion, E> {
    private TreeSet<Register<MinecraftVersion,E>> versionSet = new TreeSet<>();
    @Override
    public void register(MinecraftVersion minecraftVersion, Version<E> version) {
        if(version == null){
            throw new IllegalArgumentException("Version cannot be null");
        }
        if(minecraftVersion == null){
            throw new IllegalArgumentException("MinecraftVersion cannot be null");
        }
        versionSet.add(new Register<>(minecraftVersion,version));
//        versionMap.put(minecraftVersion, version);
    }

    @Override
    public Version<E> getVersion(MinecraftVersion minecraftVersion) {
//        return minecraftVersion.getVersion();
        //return versionSet;
        return null;
    }

    private static class Register<V extends Versioned, E>{
        private V version;
        private Version<E> element;
        public Register(V version, Version<E> element){
            this.version = version;
            this.element = element;
        }

        public Version<E> getElement() {
            return element;
        }

        public V getVersion() {
            return version;
        }
    }

}
