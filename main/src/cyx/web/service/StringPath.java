package cyx.web.service;

import cyx.web.utils.FileTool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StringPath implements ILocalPath {

    private Set path = new HashSet<>();

    public StringPath() {
        try {
            String fileContent = FileTool.getFileContent("urls.txt");
            String[] urls = fileContent.split("\r\n");
            System.out.println("总变动文件数:"+urls.length);
            for (String url : urls){
                path.add(url.trim());
            }
            System.out.println("去重后文件数:"+path.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set getPath() {

        return path;
    }

    @Override
    public void addPath(String path) {
        this.addPath(path);
    }

    @Override
    public void savePath() {

    }
}
