package cyx.web.service;

import cyx.web.dao.IFileListener;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class FileMonitor {

    private FileAlterationMonitor monitor = null;

    public FileMonitor(long interval) {
        monitor = new FileAlterationMonitor(interval > 1000 ? interval : 1000);
    }

    public void addListener(String filePath, IFileListener fileListener) {
        FileObserver observer = new FileObserver(new File(filePath));
        monitor.addObserver(observer);
        observer.addListener(fileListener);
    }

    public void stop() throws Exception {
        monitor.stop();
    }

    public void start() throws Exception {
        monitor.start();
    }
}
