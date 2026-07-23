import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpeedLimitRule implements ITrafficRule 
{
    private static final String RULE_NAME = "Speed Limit";
    private static final int SPEED_VIOLATION_FEE = 300;
    private final Map<CarType, Integer> speedLimits;

    public SpeedLimitRule() 
    {
        speedLimits = new HashMap<>();
        speedLimits.put(CarType.TRUCK, 60);
        speedLimits.put(CarType.PRIVATE, 80);
        speedLimits.put(CarType.BUS, 70);
    }

    @Override
    public List<Violation> check(RadarObservation observation) 
    {
        List<Violation> violations = new ArrayList<>();
        Integer maxSpeed = speedLimits.get(observation.getCarType());

        if (maxSpeed != null && observation.getSpeed() > maxSpeed) 
        {
            String description = "speed of " + observation.getSpeed() + " exceeded max allowed " + maxSpeed;
            violations.add(new Violation(description, SPEED_VIOLATION_FEE, RULE_NAME));
        }

        return violations;
    }

    @Override
    public String getRuleName() 
    {
        return RULE_NAME;
    }
}
