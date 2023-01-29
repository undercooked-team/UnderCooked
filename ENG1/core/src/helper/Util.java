package helper;

/** A Utilities Static Class. Contains many helpful functions.*/
public class Util {
    /**
     * Calculates the distance between 2 points.
     * @return float : The distance between the 2 points.
     */
    public static float distancePoints(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }

    /**
     * Returns a string containing the given time elapsed in the parameters.
     * @return String : The time in hrs:mins:secs
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
