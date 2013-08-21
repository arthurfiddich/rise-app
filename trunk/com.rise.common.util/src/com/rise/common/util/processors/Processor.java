package com.rise.common.util.processors;

public interface Processor<T> {

	public void process(T argParameter);
	
	public void process();
}
