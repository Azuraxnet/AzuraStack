package net.azura.version.types;

import net.azura.version.container.Versioned;

public enum MinecraftVersion implements Versioned {
    LATEST("1.19.4", 38),
    V_1_19_4("1.19.4",38),
    V_1_19_3("1.19.3",37),
    V_1_19_2("1.19.2",36),
    V_1_19_1("1.19.1",35),
    V_1_19("1.19", 34),

    V_1_18_2("1.18.2",33),
    V_1_18_1("1.18.1",32),
    V_1_18("1.18",31),

    V_1_17_1("1.17.1", 30),
    V_1_17("1.17", 29),

    V_1_16_5("1.16.5", 28),
    V_1_16_4("1.16.4", 27),
    V_1_16_3("1.16.3", 26),
    V_1_16_2("1.16.2",25),
    V_1_16_1("1.16.1",24),

    V_1_15_2("1.15.2",23),
    V_1_15_1("1.15.1",22),
    V_1_15("1.15", 21),

    V_1_14_4("1.14.4", 20),
    V_1_14_3("1.14.3", 19),
    V_1_14_2("1.14.2", 18),
    V_1_14_1("1.14.1", 17),
    V_1_14("1.14", 16),

    V_1_13_2("1.13.2", 15),
    V_1_13_1("1.13.1", 14),
    V_1_13("1.13", 13),

    V_1_12_2("1.12.2", 12),
    V_1_12_1("1.12.1",11),
    V_1_12("1.12", 10),

    V_1_11_2("1.11.2",9),
    V_1_11_1("1.11.1",8),
    V_1_11("1.11",7),

    V_1_10_2("1.10.2",6),

    V_1_9_4("1.9.4",5),
    V_1_9_2("1.9.2",4),
    V_1_9("1.9",3),

    V_1_8_8("1.8.8",2),
    V_1_8_3("1.8.3",1),
    V_1_8("1.8",0),
    ;
    private float order;
    private String version;
    MinecraftVersion(String version, float order){
        this.version = version;
        this.order = order;
    }

    public String getVersion() {
        return version;
    }

    public static MinecraftVersion from(String version){
        for(MinecraftVersion v : values()){
            if(version.contains(v.getVersion())){
                return v;
            }
        }
        return null;
    }

    public static class Range{
        private MinecraftVersion from, to;
        public Range(MinecraftVersion from, MinecraftVersion to){
            this.from = from;
            this.to = to;
        }

        public boolean isSupported(MinecraftVersion version){
            return version.order >= from.order && version.order <= to.order;
        }

    }
}
