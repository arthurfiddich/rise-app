package com.data.generator.file.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import com.data.generator.constants.CommonConstants;
import com.data.generator.file.Watcher;
import com.data.generator.util.Precondition;

public class FileWatcher implements Watcher<String> {

	@Override
	public void watch(String argPathName) {
		try {
			Precondition.ensureNotEmpty(argPathName, "Path Name");
			String watcherDirectoryPath = prepareWatcherDirectory(argPathName);
			Path watcherDirectory = Paths.get(watcherDirectoryPath);
			Precondition.ensureNotNull(watcherDirectory, "Watcher Directory");
			WatchService watchService = watcherDirectory.getFileSystem()
					.newWatchService();
			Precondition.ensureNotNull(watchService, "Watch Service");
			watcherDirectory.register(watchService,
					StandardWatchEventKinds.ENTRY_CREATE);
			WatchKey watchKey = watchService.take();
			List<WatchEvent<?>> watchEventsList = watchKey.pollEvents();
			if (Precondition.checkNotEmpty(watchEventsList)) {
				for (WatchEvent<?> watchEvent : watchEventsList) {
					if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_CREATE) {

					}
				}
			}
		} catch (InterruptedException e) {
		} catch (IOException e) {
		}
	}

	private String prepareWatcherDirectory(String argPathName) {
		return new StringBuilder().append(CommonConstants.DOT)
				.append(CommonConstants.FORWARD_SLASH).append(argPathName)
				.toString();
	}

}
