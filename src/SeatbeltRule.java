import java.util.ArrayList;
import java.util.List;

public class SeatbeltRule implements ITrafficRule 
{
    private static final String RULE_NAME = "Seatbelt";
    private static final int SEATBELT_VIOLATION_FEE = 100;

    @Override
    public List<Violation> check(RadarObservation observation) 
    {
        List<Violation> violations = new ArrayList<>();

        if (!observation.isSeatbeltFastened()) 
        {
            violations.add(new Violation("Seatbelt not fastned", SEATBELT_VIOLATION_FEE, RULE_NAME));
        }

        return violations;
    }

    @Override
    public String getRuleName() 
    {
        return RULE_NAME;
    }
}
