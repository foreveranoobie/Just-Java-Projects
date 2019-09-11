import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Exc22 {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Params<Integer> pr = new Params<Integer>(Integer.class);
		Integer num = pr.getCl("3");
		System.out.print(num);
	}
}

class Params<T>{
	Class<T> kind;
	Params(Class<T> cl){
		kind = cl;
	}
	public T getCl(String params) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<T> constructor = kind.getConstructor(params.getClass());
		return (T)constructor.newInstance(params);
	}
}