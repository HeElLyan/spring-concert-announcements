package ru.itis.models;

public enum MetalGenre {
    PROGRESSIVE_METAL(0, "ProgressiveMetal"),
    DEATH_METAL(1, "DeathMetal"),
    POST_METAl(2, "PostMetal"),
    POST_HARDCORE(3, "PostHardcore"),
    METALCORE(4, "Metalcore"),
    MELODIC_DEATH_METAL(5, "MDM"),
    NU_METAL(6,"Nu-metal");

    private int key;
    private String value;

    MetalGenre(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
