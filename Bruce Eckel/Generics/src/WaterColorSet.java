import java.util.EnumSet;
import java.util.Set;

public class WaterColorSet {
	  public static void main(String[] args) {
	    Set<Watercolors> set1 =
	      EnumSet.range(Watercolors.BRILLIANT_RED, Watercolors.VIRIDIAN_HUE);
	    Set<Watercolors> set2 =
	      EnumSet.range(Watercolors.CERULEAN_BLUE_HUE, Watercolors.BURNT_UMBER);
	    System.out.println(Sets.union(set1, set2));
	  }	
	}

enum Watercolors {
	  ZINC, LEMON_YELLOW, MEDIUM_YELLOW, DEEP_YELLOW, ORANGE,
	  BRILLIANT_RED, CRIMSON, MAGENTA, ROSE_MADDER, VIOLET,
	  CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE,
	  COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE,
	  SAP_GREEN, YELLOW_OCHRE, BURNT_SIENNA, RAW_UMBER,
	  BURNT_UMBER, PAYNES_GRAY, IVORY_BLACK
	}
