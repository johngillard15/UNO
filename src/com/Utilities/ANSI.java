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
            case RESET -> "\u001B[0m";
            case BLACK -> "\u001B[30m";
            case RED -> "\u001B[31m";
            case GREEN -> "\u001B[32m";
            case YELLOW -> "\u001B[33m";
            case BLUE -> "\u001B[34m";
            case PURPLE -> "\u001B[35m";
            case CYAN -> "\u001B[36m";
            case WHITE -> "\u001B[37m";
            case BLACK_BG -> "\u001B[40m";
            case RED_BG -> "\u001B[41m";
            case GREEN_BG -> "\u001B[42m";
            case YELLOW_BG -> "\u001B[43m";
            case BLUE_BG -> "\u001B[44m";
            case PURPLE_BG -> "\u001B[45m";
            case CYAN_BG -> "\u001B[46m";
            case WHITE_BG -> "\u001B[47m";
            default -> throw new IllegalStateException("Unexpected value: " + color);
        };
    }
}
