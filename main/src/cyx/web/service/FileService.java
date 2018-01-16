package cyx.web.service;

import cyx.web.service.adaptor.FileListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class FileService extends FileListenerAdaptor {

    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
        System.out.println("启动文件检测器");
    }

    @Override
    public void onDirectoryCreate(File directory) {
        super.onDirectoryCreate(directory);
        System.out.println("创建文件夹"+directory.getPath());
    }

    @Override
    public void onDirectoryChange(File directory) {
        super.onDirectoryChange(directory);
        System.out.println("改变文件夹"+directory.getPath());
    }

    @Override
    public void onDirectoryDelete(File directory) {
        super.onDirectoryDelete(directory);
        System.out.println("删除文件夹"+directory.getPath());
    }

    @Override
    public void onFileCreate(File file) {
        super.onFileCreate(file);
        System.out.println("创建文件"+file.getPath());
    }

    @Override
    public void onFileChange(File file) {
        super.onFileChange(file);
        System.out.println("改变文件"+file.getPath());
    }

    @Override
    public void onFileDelete(File file) {
        super.onFileDelete(file);
        System.out.println("删除文件"+file.getPath());
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
        System.out.println("关闭文件检测器");
    }
}
