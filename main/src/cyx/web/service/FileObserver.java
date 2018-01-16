package cyx.web.service;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.io.monitor.FileEntry;

import java.io.File;
import java.io.FileFilter;

public class FileObserver extends FileAlterationObserver {

    public FileObserver(String directoryName) {
        super(directoryName);
    }

    public FileObserver(String directoryName, FileFilter fileFilter) {
        super(directoryName, fileFilter);
    }

    public FileObserver(String directoryName, FileFilter fileFilter, IOCase caseSensitivity) {
        super(directoryName, fileFilter, caseSensitivity);
    }

    public FileObserver(File directory) {
        super(directory);
    }

    public FileObserver(File directory, FileFilter fileFilter) {
        super(directory, fileFilter);
    }

    public FileObserver(File directory, FileFilter fileFilter, IOCase caseSensitivity) {
        super(directory, fileFilter, caseSensitivity);
    }

    protected FileObserver(FileEntry rootEntry, FileFilter fileFilter, IOCase caseSensitivity) {
        super(rootEntry, fileFilter, caseSensitivity);
    }
}
