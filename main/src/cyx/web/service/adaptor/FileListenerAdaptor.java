package cyx.web.service.adaptor;

import cyx.web.dao.IFileListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class FileListenerAdaptor implements IFileListener {
    public void onStart(FileAlterationObserver observer) {

    }

    public void onDirectoryCreate(File directory) {

    }

    public void onDirectoryChange(File directory) {

    }

    public void onDirectoryDelete(File directory) {

    }

    public void onFileCreate(File file) {

    }

    public void onFileChange(File file) {

    }

    public void onFileDelete(File file) {

    }

    public void onStop(FileAlterationObserver observer) {

    }
}
