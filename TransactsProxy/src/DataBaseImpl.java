import java.io.FileWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class DataBaseImpl implements DataBase {
	private Map<String, Integer> data = new HashMap<String, Integer>();

	public void getID(String str) {
		DataChng proxy = (DataChng) Proxy.newProxyInstance(DataChng.class.getClassLoader(),
				new Class[] { DataChng.class }, new TransactProxy(new DataChngImpl()));
		if (data.containsKey(str)) {
			proxy.dataOffer(data.get(str), str, true);
			return;
		}
		proxy.dataOffer(-1, str, false);
	}

	public DataBaseImpl(String... names) {
		int i = 0;
		for (String str : names) {
			data.put(str, Integer.valueOf(++i));
		}
	}

	public static void main(String... args) {
		DataBaseImpl base = new DataBaseImpl("Johny Batch", "Harry Potter", "Iosif Brodskiy");
		base.getID("Johny Batch");
	}
}

class TransactProxy implements InvocationHandler {
	private Object proxied;

	public TransactProxy(Object proxied) {
		this.proxied = proxied;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		FileWriter fw = new FileWriter("Data Info", true);
		fw.write(args[0].toString() + " - " + args[1] + " : " + args[2] + "\n");
		fw.close();
		return method.invoke(proxied, args);
	}
}