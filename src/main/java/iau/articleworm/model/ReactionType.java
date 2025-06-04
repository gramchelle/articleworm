package iau.articleworm.model;

public enum ReactionType {
    LIKE((short) 1),
    DISLIKE((short) 2),
    LOVE((short) 3),
    SAD((short) 4),
    FUNNY((short) 5),
    SUPPORT((short) 6);

    private final short value;

    ReactionType(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public static ReactionType fromValue(short value) {
        for (ReactionType type : values()) {
            if (type.value == value) return type;
        }
        throw new IllegalArgumentException("Unknown reaction type: " + value);
    }
}
