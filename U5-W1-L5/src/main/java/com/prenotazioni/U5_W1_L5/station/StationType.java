package com.prenotazioni.U5_W1_L5.station;

import java.util.Random;

public enum StationType {
    PRIVATE,
    OPENSPACE,
    MEETINGROOM;

    private static final StationType[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static StationType getRandomType() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
