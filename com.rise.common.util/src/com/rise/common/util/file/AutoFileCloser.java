package com.rise.common.util.file;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rise.common.util.checker.Precondition;

public abstract class AutoFileCloser {

	protected abstract void doWork() throws Throwable;

	/*
	 * Track a list of closeables to close when finished.
	 */
	private List<Closeable> closableList = new ArrayList<Closeable>();

	protected final <T extends Closeable> T autoClose(T argCloseable) {
		closableList.add(argCloseable);
		return argCloseable;
	}

	public AutoFileCloser() {
		Throwable pending = null;

		try {
			doWork();
		} catch (Throwable throwable) {
			pending = throwable;
		} finally {
			// close the watched streams
			for (Closeable closeable : closableList) {
				if (Precondition.checkNotNull(closeable)) {
					try {
						closeable.close();
					} catch (IOException throwable) {
						if (!Precondition.checkNotNull(pending)) {
							pending = throwable;
						}
					}
				}
			}
		}
		/*
		 * if we had a pending exception, rethrow it this is necessary because
		 * the close can throw an exception, which would remove the pending
		 * status of any exception thrown in the try block
		 */
		if (Precondition.checkNotNull(pending)) {
			if (pending instanceof RuntimeException) {
				throw (RuntimeException) pending;
			} else {
				throw new RuntimeException(pending);
			}
		}
	}

}
