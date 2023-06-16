package net.azura.version;

import net.azura.version.container.Versioned;

public abstract class ClassRegister<V extends Versioned, E>{
    private V version;
    private Version<E> element;
    public ClassRegister(V version, Version<E> element){
        this.version = version;
        this.element = element;
    }

    public Version<E> getElement() {
        return element;
    }

    public V getVersion() {
        return version;
    }
    public abstract boolean isSupported(V version);
}
