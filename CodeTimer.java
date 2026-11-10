public class CodeTimer {
    private static long startTime;

    private CodeTimer() {
        // Prevent objects from being created
    }

    public static void start() {
        startTime = System.nanoTime();
    }

    public static String stop() {
        return stop("milliseconds");
    }

    public static long stopMs() {
	        return (System.nanoTime() - startTime)/1_000_000L;
    }

    public static String stop(String unit) {
        long elapsedNanoseconds = System.nanoTime() - startTime;

        if (unit.equalsIgnoreCase("nano") || unit.equalsIgnoreCase("ns") ||  unit.equalsIgnoreCase("n") ) {
            return elapsedNanoseconds + " ns";
        } else if (unit.equalsIgnoreCase("seconds")|| unit.equalsIgnoreCase("sec") ||  unit.equalsIgnoreCase("s")) {
            double seconds = elapsedNanoseconds / 1_000_000_000.0;
            return String.format("%.1f seconds", seconds);
        } else if (unit.equalsIgnoreCase("minutes")|| unit.equalsIgnoreCase("min") ||  unit.equalsIgnoreCase("m")) {
            double minutes = elapsedNanoseconds / 60_000_000_000.0;
            return String.format("%.1f minutes", minutes);
        } else if (unit.equalsIgnoreCase("milliseconds")|| unit.equalsIgnoreCase("ms") ||  unit.equalsIgnoreCase("milli")) {
            double milliseconds = elapsedNanoseconds / 1_000_000.0;
            return String.format("%.1f ms", milliseconds);
        } else {
            throw new IllegalArgumentException(
                    "Unit must be milliseconds, nano, seconds, or minutes."
            );
        }
    }
}