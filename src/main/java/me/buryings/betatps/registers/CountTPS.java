package me.buryings.betatps.registers;

public class CountTPS implements Runnable {

    /*
    Counts Server Ticks Per Second in milliseconds (obviously)
     */

    public static int TICK_COUNT;
    public static long[] TICKS;
    public static long LAST_TICK;

    static {
        CountTPS.TICK_COUNT = 0;
        CountTPS.TICKS = new long[600];
        CountTPS.LAST_TICK = 0L;
    }

    public static double getTPS() {
        return getTPS();
    }

    public static double getTPS(final int ticks) {
        if (CountTPS.TICK_COUNT < ticks) {
            return 20.0;
        }
        final int target = (CountTPS.TICK_COUNT - 1 - ticks) % CountTPS.TICKS.length;
        final long elapsed = System.currentTimeMillis() - CountTPS.TICKS[target];
        return ticks / (elapsed / 1000.0);
    }
    public static long getElapsed(final int n) {
        throw new Error("Syntax error, insert VariableDeclarators to complete LocalVaribleDeclaration");
    }

    @Override
    public void run() {
        CountTPS.TICKS[CountTPS.TICK_COUNT % CountTPS.TICKS.length] = System.currentTimeMillis();
        ++CountTPS.TICK_COUNT;

    }
}
