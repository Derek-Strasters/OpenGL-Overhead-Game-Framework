package org.paracite.glframework.core;

import java.util.ArrayList;
import java.util.List;

public class Pool<T> {

	//What a beautifully done bit of code!
	
	//When you first define a PoolObjectFactory, you will specify the 
	//the createObject() method's actions using a anonymous inner class.
	public interface PoolObjectFactory<T> {
		public T createObject();
	}
	
	private final List<T> freeObjects;
	private final PoolObjectFactory<T> factory;
	private final int maxSize;
	
	public Pool(PoolObjectFactory<T> factory, int maxSize) {
		this.factory  = factory;
		this.maxSize = maxSize;
		this.freeObjects = new ArrayList<T>(maxSize);
	}
	
	//This returns either a new instance of type T, OR, it will take one
	//of the members of the pool waiting to be reused (the last one) 
	//and give it to you.
	public T newObject() {
		T object = null;
		
		if (freeObjects.size() == 0)
			object = factory.createObject();
		else
			object = freeObjects.remove(freeObjects.size() - 1);
		
		return object;
	}
	
	//This takes an object and put's it in the pool to wait for reuse.
	public void free(T object) {
		if (freeObjects.size() < maxSize)
			freeObjects.add(object);
	}
	
}
