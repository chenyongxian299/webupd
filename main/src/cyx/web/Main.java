package cyx.web;

import com.alibaba.fastjson.JSON;
import cyx.web.bean.FileInfo;
import cyx.web.service.FilePathService;
import cyx.web.utils.FileTool;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        /*ProjectService project = new ProjectService();

        try {
            project.copyFile("D:/Project/main_php/", "D:/Project/webupd/Project/main_php/");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
       /* FileMonitor monitor = new FileMonitor(5000);
        monitor.addListener("D:\\Project\\main_php",new FileService());
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Collection<File> cFile = FileUtils.listFiles(new File("D:\\Project\\webupd\\Project\\main_php"), null, true);

        File[] files = FileUtils.convertFileCollectionToFileArray(cFile);

        FilePathService filePathService = new FilePathService();
      /*  List<FileInfo> fileInfoList = filePathService.findAll();
        System.out.println("查询成功"+ JSON.toJSONString(fileInfoList));
        ArrayList<FileInfo> fileInfoList = new ArrayList<>();
        fileInfoList.ensureCapacity(files.length);
        System.out.println("循环开始");
        for (File file : files) {
            FileInfo fileInfo = new FileInfo();
            String md5 = FileTool.getMD5(file);
            fileInfo.setFilePath(file.getPath());
            fileInfo.setFileMD5(md5);
            fileInfo.setDelete(0);
            fileInfoList.add(fileInfo);
        }
        System.out.println("循环结束");
        boolean isSuccess = filePathService.save(fileInfoList);
        System.out.println("插入成功");*/
        ArrayList<FileInfo> fileInfoList = new ArrayList<>();
        fileInfoList.ensureCapacity(files.length);
        for (File file : files) {
            FileInfo fileInfo = new FileInfo();
            String md5 = FileTool.getMD5(file);
            fileInfo.setFilePath(file.getPath());
            fileInfo.setFileMD5(md5);
            fileInfo.setDelete(0);
            fileInfoList.add(fileInfo);
        }
        Map<FilePathService.Status, List<FileInfo>> result =  filePathService.compare(fileInfoList);
        System.out.println("////");

    }
}
