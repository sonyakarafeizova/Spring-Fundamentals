package com.philately.model.entity;

public enum PaperType {
    WOVE_PAPER("Has an even texture without any particular distinguishing features."),
    LAID_PAPER("When held up to the light, shows parallel lines of greater or less width running across the stamp."),
    GRANITE_PAPER("Has tiny specks of coloured fibre in it, which can usually be seen with the naked eye.");
    private final String description;

    PaperType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
