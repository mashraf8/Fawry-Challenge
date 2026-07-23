import java.util.List;

public class Fine 
{
    private String plateNumber;
    private List<Violation> violations;

    public Fine(String plateNumber, List<Violation> violations) 
    {
        this.plateNumber = plateNumber;
        this.violations = violations;
    }

    public String getPlateNumber() 
    {
        return plateNumber;
    }

    public List<Violation> getViolations() 
    {
        return violations;
    }

    public int getTotalAmount()
    {
        int total = 0;
        for (Violation v : violations) 
        {
            total += v.getFee();
        }
        return total;
    }

    public String getDetails() 
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Traffic for car ").append(plateNumber).append("\n");
        sb.append("Total amount: ").append(getTotalAmount()).append(" EGP").append("\n");
        sb.append("Violations:").append("\n");
        for (Violation v : violations) 
        {
            sb.append(v.getDetails()).append("\n");
        }
        return sb.toString().trim();
    }
}
