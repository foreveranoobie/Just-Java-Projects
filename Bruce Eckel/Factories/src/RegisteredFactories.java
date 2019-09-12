import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

class Part {
  public String toString() {
    return getClass().getSimpleName();
  }
  static List<Class<? extends Factories>> partFactories =
    new ArrayList<Class<? extends Factories>>();	
  static {
    // Collections.addAll() gives an "unchecked generic
    // array creation ... for varargs parameter" warning.
    partFactories.add(FuelFilter.Factory.class);
    partFactories.add(AirFilter.Factory.class);
    partFactories.add(CabinAirFilter.Factory.class);
    partFactories.add(OilFilter.Factory.class);
    partFactories.add(FanBelt.Factory.class);
    partFactories.add(PowerSteeringBelt.Factory.class);
    partFactories.add(GeneratorBelt.Factory.class);
    partFactories.add(NullPart.Factory.class);
  }
  private static Random rand = new Random();
  public static Object createRandom() throws InstantiationException, IllegalAccessException {
    int n = rand.nextInt(partFactories.size());
    return partFactories.get(n).newInstance().create();
  }
}	

class Filter extends Part {}

class FuelFilter extends Filter {
  // Create a Class Factory for each specific type:
  public static class Factory
  implements Factories<FuelFilter> {
    public FuelFilter create() { return new FuelFilter(); }
  }
}

class AirFilter extends Filter {
  public static class Factory
  implements Factories<AirFilter> {
    public AirFilter create() { return new AirFilter(); }
  }
}	

class CabinAirFilter extends Filter {
  public static class Factory
  implements Factories<CabinAirFilter> {
    public CabinAirFilter create() {
      return new CabinAirFilter();
    }
  }
}

class OilFilter extends Filter {
  public static class Factory
  implements Factories<OilFilter> {
    public OilFilter create() { return new OilFilter(); }
  }
}	

class Belt extends Part {}

class FanBelt extends Belt {
  public static class Factory
  implements Factories<FanBelt> {
    public FanBelt create() { return new FanBelt(); }
  }
}

class GeneratorBelt extends Belt {
  public static class Factory
  implements Factories<GeneratorBelt> {
    public GeneratorBelt create() {
      return new GeneratorBelt();
    }
  }
}	

class PowerSteeringBelt extends Belt {
  public static class Factory
  implements Factories<PowerSteeringBelt> {
    public PowerSteeringBelt create() {
      return new PowerSteeringBelt();
    }
  }
}	
interface Null{
	public String toString();
}
class NullPart extends Part
implements Null{
	private NullPart() { super(); }
	public static final Part NULL = new NullPart();
	public NullPart create() { return new NullPart(); }
	public static class Factory implements Factories<NullPart> {
		public NullPart create() {
			return (NullPart)NULL;
		}
	}
	public String toString() {
		return "NULL";
	}
}
public class RegisteredFactories {
  public static void main(String[] args) throws InstantiationException, IllegalAccessException {
    for(int i = 0; i < 10; i++)
      System.out.println(Part.createRandom());
  }
}
