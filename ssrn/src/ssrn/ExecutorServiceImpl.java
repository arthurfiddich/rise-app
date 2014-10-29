package ssrn;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceImpl {

	/** The Constant DEFAULT_THREAD_POOL_SIZE. */
	private static final int DEFAULT_THREAD_POOL_SIZE = 40;

	/** The Constant DEFAULT_QUEUE_SIZE. */
	private static final int DEFAULT_QUEUE_SIZE = 200;

	/** The Constant DEFAULT_KEEP_ALIVE. */
	private static final long DEFAULT_KEEP_ALIVE = 40;

	/** The Constant DEFAULT_WAIT_TIME_TO_QUEUE. */
	private static final long DEFAULT_WAIT_TIME_TO_QUEUE = 1 * 1000L;

	private ExecutorService executorService;
	private long timeout;
	private int threads;
	private int queueSize;

	public ExecutorServiceImpl() {
		initialize();
	}

	public void initialize() {
		if (Precondition.checkZero(this.getThreads())) {
			this.setThreads(DEFAULT_THREAD_POOL_SIZE);
		}
		if (Precondition.checkZero(this.getQueueSize())) {
			this.setQueueSize(DEFAULT_QUEUE_SIZE);
		}
		this.setExecutorService(createExecutorService());
	}

	private ExecutorService createExecutorService() {
		BlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingDeque<Runnable>(
				this.getThreads());
		ThreadPoolExecutor threadPoolExecutor = new DataGeneratorThreadPoolExecutor(
				this.getThreads(), this.getThreads(), DEFAULT_KEEP_ALIVE,
				TimeUnit.SECONDS, linkedBlockingQueue);
		return threadPoolExecutor;
	}

	public void execute(Runnable argParameter) {
		boolean queued = false;
		do {
			try {
				this.getExecutorService().execute(argParameter);
				queued = true;
			} catch (RejectedExecutionException e) {
				try {
					Thread.sleep(DEFAULT_WAIT_TIME_TO_QUEUE);
				} catch (InterruptedException ignore) {
					// ignore
				} 
			}
		} while (!queued);
	}

	public <V> Future<V> execute(Callable<V> argParameter) {
		boolean queued = false;
		Future<V> result = null;
		do {
			try {
				result = this.getExecutorService().submit(argParameter);
				queued = true;
			} catch (RejectedExecutionException e) {
				try {
					Thread.sleep(DEFAULT_WAIT_TIME_TO_QUEUE);
				} catch (InterruptedException ignore) {
					// ignore
				}
			}
		} while (!queued);
		return result;
	}

	public ExecutorService getExecutorService() {
		return this.executorService;
	}

	public void setExecutorService(ExecutorService argExecutorService) {
		this.executorService = argExecutorService;
	}

	public long getTimeout() {
		return this.timeout;
	}

	public void setTimeout(long argTimeout) {
		this.timeout = argTimeout;
	}

	public int getThreads() {
		return this.threads;
	}

	public void setThreads(int argThreads) {
		this.threads = argThreads;
	}

	public int getQueueSize() {
		return this.queueSize;
	}

	public void setQueueSize(int argQueueSize) {
		this.queueSize = argQueueSize;
	}

}
