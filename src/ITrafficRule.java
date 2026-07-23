import java.util.List;

public interface ITrafficRule 
{
    List<Violation> check(RadarObservation observation);
    String getRuleName();
}
