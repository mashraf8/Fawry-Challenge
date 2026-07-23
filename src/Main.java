import java.time.LocalDateTime;
import java.util.Map;

public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println("\n");

        // Create the radar and register rules
        Radar radar = new Radar();
        radar.addRule(new SpeedLimitRule());
        radar.addRule(new SeatbeltRule());

        System.out.println("=== Traffic Monitoring ===\n");

        // case 1: Private car speeding + seatbelt not fastened
        System.out.println("Case 1: Private car speeding + seatbelt not fastened");
        RadarObservation obs1 = new RadarObservation(
                "CAR1234",
                LocalDateTime.of(2026, 7, 23, 10, 30),
                CarType.PRIVATE,
                94,
                false
        );
        radar.processObservation(obs1);

        // case 2: Truck exceeding speed limit
        System.out.println("Case 2: Truck exceeding speed limit");
        RadarObservation obs2 = new RadarObservation(
                "GIZ1234",
                LocalDateTime.of(2026, 7, 23, 11, 15),
                CarType.TRUCK,
                75,
                true
        );
        radar.processObservation(obs2);

        // case 3: Bus with no violations
        System.out.println("Case 3: Bus with no violations\n");
        RadarObservation obs3 = new RadarObservation(
                "DAK1234",
                LocalDateTime.of(2026, 7, 23, 12, 0),
                CarType.BUS,
                60,
                true
        );
        radar.processObservation(obs3);

        // case 4: Private car - only seatbelt violation
        System.out.println("Case 4: Private car - only seatbelt violation");
        RadarObservation obs4 = new RadarObservation(
                "ALX1234",
                LocalDateTime.of(2026, 7, 23, 13, 45),
                CarType.PRIVATE,
                70,
                false
        );
        radar.processObservation(obs4);

        // case 5: Same car ABC1234 caught again
        System.out.println("Case 5: Same car CAR1234 caught again");
        RadarObservation obs5 = new RadarObservation(
                "CAR1234",
                LocalDateTime.of(2026, 7, 23, 14, 0),
                CarType.PRIVATE,
                90,
                true
        );
        radar.processObservation(obs5);

        // Get all fines summary
        System.out.println("=== All Fines Summary ===\n");
        Map<String, Integer> allFines = radar.getAllPossibleFines();
        for (Map.Entry<String, Integer> entry : allFines.entrySet()) 
        {
            System.out.println("Plate: " + entry.getKey() + " - Total: " + entry.getValue() + " EGP");
        }

        // Get violated rules count
        System.out.println("\n=== Violated Rules Count ===\n");
        Map<String, Integer> violatedRules = radar.getViolatedRulesCount();
        for (Map.Entry<String, Integer> entry : violatedRules.entrySet()) 
        {
            System.out.println("Rule: " + entry.getKey() + " - Violations: " + entry.getValue());
        }

        System.out.println("\n");
    }
}
