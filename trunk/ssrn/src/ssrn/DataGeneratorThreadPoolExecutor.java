package ssrn;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DataGeneratorThreadPoolExecutor extends ThreadPoolExecutor{

	public DataGeneratorThreadPoolExecutor(int argArg0, int argArg1,
			long argArg2, TimeUnit argArg3, BlockingQueue<Runnable> argArg4) {
		super(argArg0, argArg1, argArg2, argArg3, argArg4);
	}

}
