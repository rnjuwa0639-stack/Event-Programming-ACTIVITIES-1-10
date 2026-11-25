package week02.activity1;

public class StatisticsDisplay implements Observer {

    // -Float.MAX_VALUE (not Float.MIN_VALUE, which is the smallest POSITIVE value)
    // so that negative temperatures are handled correctly.
    private float maxTemp = -Float.MAX_VALUE;
    private float minTemp = Float.MAX_VALUE;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        maxTemp = Math.max(maxTemp, temperature);
        minTemp = Math.min(minTemp, temperature);
        System.out.println("  [Statistics] Max: " + maxTemp
                + "°C, Min: " + minTemp + "°C");
    }
}
