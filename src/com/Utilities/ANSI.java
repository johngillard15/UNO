package com.Utilities;

public class ANSI {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BLACK_BG = "\u001B[40m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String PURPLE_BG = "\u001B[45m";
    public static final String CYAN_BG = "\u001B[46m";
    public static final String WHITE_BG = "\u001B[47m";

    public static String getCode(String color){
        return switch(color){
            case RESET -> RESET;
            case BLACK -> BLACK;
            case RED -> RED;
            case GREEN -> GREEN;
            case YELLOW -> YELLOW;
            case BLUE -> BLUE;
            case PURPLE -> PURPLE;
            case CYAN -> CYAN;
            case WHITE -> WHITE;
            case BLACK_BG -> BLACK_BG;
            case RED_BG -> RED_BG;
            case GREEN_BG -> GREEN_BG;
            case YELLOW_BG -> YELLOW_BG;
            case BLUE_BG -> BLUE_BG;
            case PURPLE_BG -> PURPLE_BG;
            case CYAN_BG -> CYAN_BG;
            case WHITE_BG -> WHITE_BG;
            default -> throw new IllegalStateException("Unexpected value: " + color);
        };
    }
}
