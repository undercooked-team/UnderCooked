package helper;

/** A Utilities Static Class. Contains many helpful functions.*/
public class Util {
    /**
     * Calculates the distance between 2 points.
     * @param x1 The x coordinate of the first point
     * @param y1 The y coordinate of the first point
     * @param x2 The x coordinate of the second point
     * @param y2 The y coordinate of the second point
     * @return {@code float}: The distance between the 2 points.
     */
    public static float distancePoints(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }

    /**
     * Returns a string containing the given time elapsed in the parameters.
     * @param hours The time's in hours.
     * @param minutes The time's minutes.
     * @param seconds The time's seconds.
     * @return {@link String} : The time in mins:secs if hrs == 0, or hrs:mins:secs if hrs &gt; 0.
     */
    public static String formatTime(int hours, int minutes, int seconds) {
        String timeString = "";
        if (hours > 0) {
            timeString += hours + ":";
        }
        timeString += minutes + ":";
        if (seconds < 10) {
            timeString += "0";
        }
        timeString += seconds;
        return timeString;
    }
}
