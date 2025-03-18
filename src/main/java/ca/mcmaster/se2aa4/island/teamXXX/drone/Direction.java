package ca.mcmaster.se2aa4.island.teamXXX.drone;

public enum Direction {
    NORTH('N'),
    SOUTH('S'),
    EAST('E'),
    WEST('W');

    private final char dir;

    Direction(char dir) {
        this.dir = dir;
    } // constructor

    /**
     * Maps the strings N,S,E,& W to the corresponding Direction
     * @param dir String direction
     * @return Heading corresponding to dir
     */
    public static Direction toDirection(String dir) {
        return switch (dir.toUpperCase()) {
            case "N" -> NORTH;
            case "S" -> SOUTH;
            case "E" -> EAST;
            case "W" -> WEST;
            default -> throw new IllegalArgumentException("Invalid direction: " + dir);
        };
    }

    @Override
    public String toString() {
        return "" + dir;
    }
}
