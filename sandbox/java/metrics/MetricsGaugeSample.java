package metrics;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

public class MetricsGaugeSample {
    static final MetricRegistry metrics = new MetricRegistry();
    public static void main(String args[]) {
      startReport();
      metrics.register(MetricRegistry.name("User", "personsearchquery", "start"),
              new Gauge<Long>() {
                  @Override
                  public Long getValue() {
                      return Instant.now().toEpochMilli();
                  }
              });
      metrics.register(MetricRegistry.name("User", "personsearchquery", "end"),
              new Gauge<Long>() {
                  @Override
                  public Long getValue() {
                      return Instant.now().toEpochMilli();
                  }
              });
      wait5Seconds();
    }

  static void startReport() {
		ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
          .convertRatesTo(TimeUnit.SECONDS)
          .convertDurationsTo(TimeUnit.MILLISECONDS)
          .build();
      reporter.start(1, TimeUnit.SECONDS);
  }
  
  static void wait5Seconds() {
      try {
          Thread.sleep(5*1000);
      }
      catch(InterruptedException e) {}
  }
}
