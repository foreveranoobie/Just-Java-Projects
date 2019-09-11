package exer34;

public abstract class GenBase<T extends GenBase<T>> {
   abstract T retVal(T val);
   public T callRet(T value) {
	   return retVal(value);
   }
}
