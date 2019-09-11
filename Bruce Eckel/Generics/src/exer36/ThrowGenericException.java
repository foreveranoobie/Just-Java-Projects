package exer36;
import java.util.*;

interface Processor<T, E extends Exception, F extends Exception>{
	void process(List<T> resultCollector) throws E;
}

class ProcessRunner<T,E extends Exception, F extends Exception>
extends ArrayList<Processor<T,E,F>>{
	List<T> processAll() throws E{
		List<T> resultCollector = new ArrayList<T>();
		for(Processor<T,E,F> processor : this)
			processor.process(resultCollector);
		return resultCollector;
	}
}
class MainFailure extends Exception{}
class Failure1 extends Exception{}

class Processor1 implements Processor<String, Failure1, MainFailure>{
	static int count = 3;
	public void process(List<String> resultCollector)
	throws Failure1{
		if(count-- > 1)
			resultCollector.add("Hep!");
		else
			resultCollector.add("Ho!");
		if(count < 0)
			throw new Failure1();
	}
}

class Failure2 extends Exception{}

class Processor2 implements Processor<Integer, Failure2, MainFailure>{
	static int count = 2;
	public void process(List<Integer> resultCollector)
	throws Failure2{
		if(count-- == 0)
			resultCollector.add(47);
		else
			resultCollector.add(11);
		if(count < 0)
			throw new Failure2();
	}
}

public class ThrowGenericException {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ProcessRunner<String,Failure1,MainFailure> runner =
        		new ProcessRunner<String,Failure1,MainFailure>();
        for(int i = 0; i < 3; i++)
        	runner.add(new Processor1());
        try {
        	System.out.println(runner.processAll());
        }catch(Failure1 e) {
        	System.out.println(e);
        }
        ProcessRunner<Integer, Failure2,MainFailure> runner2 =
        		new ProcessRunner<Integer,Failure2,MainFailure>();
        for(int i = 0; i < 3; i++) {
        	runner2.add(new Processor2());
        }
        try {
        	System.out.println(runner2.processAll());
        }catch(Failure2 e) {
        	System.out.println(e);
        }
	}

}
