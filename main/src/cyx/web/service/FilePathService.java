package cyx.web.service;

import com.sun.org.apache.xml.internal.resolver.readers.TR9401CatalogReader;
import cyx.web.bean.FileInfo;
import cyx.web.dao.IBaseDao;
import cyx.web.dao.MongoDaoImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilePathService {
    private IBaseDao<FileInfo> dao = new MongoDaoImpl<FileInfo>() {
    };

    public enum Status {
        NEW, DELETE, UPDATE
    }

    public FilePathService() {
    }

    ;

    public boolean save(List<FileInfo> fileInfoList) {
        ((MongoDaoImpl) dao).saveList(fileInfoList);
        return true;
    }

    public List<FileInfo> findAll() {
        return dao.queryAll();
    }

    public Map<Status, List<FileInfo>> compare_t(List<FileInfo> newFileInfoList){
        Map<Status, List<FileInfo>> result = new HashMap<>();
        return result;
    }
    /*
    * 遍历小效率太低了
    *
    * */
    public Map<Status, List<FileInfo>> compare(List<FileInfo> newFileInfoList) {
        Map<Status, List<FileInfo>> result = new HashMap<>();
        List<FileInfo> newFile = new ArrayList<>();
        result.put(Status.NEW, newFile);
        List<FileInfo> deleteFile = new ArrayList<>();
        result.put(Status.DELETE, deleteFile);
        List<FileInfo> updateFile = new ArrayList<>();
        result.put(Status.UPDATE, updateFile);

        List<FileInfo> oldFileInfoList = findAll();
        for (FileInfo oldFileInfo : oldFileInfoList) {
            if (newFileInfoList.size() == 0) {
                break;
            }
            boolean deleted = true;
            for (FileInfo newFileInfo : newFileInfoList) {
                if (oldFileInfo.getFilePath().equals(newFileInfo.getFilePath())) {
                    deleted = false;
                    if (!oldFileInfo.getFileMD5().equals(newFileInfo.getFileMD5())) {
                        oldFileInfo.setFileMD5(newFileInfo.getFileMD5());
                        updateFile.add(oldFileInfo);
                    }
                    newFileInfoList.remove(newFileInfo);
                    break;
                }
            }
            if (deleted) {
                deleteFile.add(oldFileInfo);
            }
        }
        return result;
    }


}
