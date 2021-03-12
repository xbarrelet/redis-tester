package mn.lettuce.tester;

import io.micrometer.core.instrument.MeterRegistry;
import io.micronaut.configuration.metrics.aggregator.MeterRegistryConfigurer;

import javax.inject.Singleton;

@Singleton
public class MetricsConfigurer implements MeterRegistryConfigurer {

    @Override
    public void configure(MeterRegistry meterRegistry) {
        meterRegistry.config().commonTags("application", "mn-lettuce-tester");
    }

    @Override
    public boolean supports(MeterRegistry meterRegistry) {
        return true;
    }
}
