package cyx.web.service;

import cyx.web.utils.FileTool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class ProjectService {
    private ILocalPath iLocalPath;

    public ProjectService() {
        iLocalPath = new StringPath();
    }

    public void copyFile(String fromRoot, String toRoot) throws IOException {
        Set<String> urls = iLocalPath.getPath();
        int count = 0;
        for (String url : urls) {
            try {
                if (FileTool.copyFile(fromRoot + url, toRoot + url)) {
                    count++;
                }
            } catch (FileNotFoundException e) {
                System.err.println("文件可能已删除:"+fromRoot + url);
            }
        }
        System.out.println("待上传服务器文件数：" + count);
    }
}
