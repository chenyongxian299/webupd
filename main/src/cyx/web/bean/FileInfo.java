package cyx.web.bean;

public class FileInfo {
    private String filePath;
    private String fileMD5;
    private int isDelete;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFileMD5(String fileMD5) {
        this.fileMD5 = fileMD5;
    }

    public String getFileMD5() {
        return fileMD5;
    }

    public int isDelete() {
        return isDelete;
    }

    public void setDelete(int delete) {
        isDelete = delete;
    }
}
