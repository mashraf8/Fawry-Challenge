import java.util.*;

public class Radar 
{
    private final List<ITrafficRule> rules;
    private final List<Fine> issuedFines;

    public Radar() 
    {
        this.rules = new ArrayList<>();
        this.issuedFines = new ArrayList<>();
    }

    public void addRule(ITrafficRule rule) 
    {
        rules.add(rule);
    }

    public Fine processObservation(RadarObservation observation) 
    {
        List<Violation> violations = new ArrayList<>();

        for (ITrafficRule rule : rules) 
        {
            violations.addAll(rule.check(observation));
        }

        if (!violations.isEmpty()) 
        {
            Fine fine = new Fine(observation.getPlateNumber(), violations);
            issuedFines.add(fine);
            System.out.println(fine.getDetails());
            System.out.println();
            return fine;
        }

        return null;
    }

    public Map<String, Integer> getAllPossibleFines() 
    {
        Map<String, Integer> finesSummary = new LinkedHashMap<>();

        for (Fine fine : issuedFines) 
        {
            String plate = fine.getPlateNumber();
            finesSummary.put(plate, finesSummary.getOrDefault(plate, 0) + fine.getTotalAmount());
        }

        return finesSummary;
    }

    public Map<String, Integer> getViolatedRulesCount() 
    {
        Map<String, Integer> violatedRules = new LinkedHashMap<>();

        for (Fine fine : issuedFines)
        {
            for (Violation v : fine.getViolations()) 
            {
                String ruleName = v.getRuleName();
                violatedRules.put(ruleName, violatedRules.getOrDefault(ruleName, 0) + 1);
            }
        }

        return violatedRules;
    }
}
