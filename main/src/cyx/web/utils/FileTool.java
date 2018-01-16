package cyx.web.utils;

import cyx.web.config.Configer;
import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class FileTool {

    public static String getFileContent(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(Configer.getResource() + fileName);
        StringBuffer sBuffer = new StringBuffer("");
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            sBuffer.append(new String(bytes, 0, len));
        }
        return sBuffer.toString();
    }

    public static boolean copyFile(String fromFilePath, String toFilePath) throws IOException {
        File fromFile = new File(fromFilePath);
        if (!fromFile.exists()) {
            throw new FileNotFoundException("源文件不存在");
        }
        File toFile = FileTool.createFile(toFilePath);
        if (toFile == null) {
            throw new FileNotFoundException("文件创建失败");
        }
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int len;
        while ((len = ins.read(b)) != -1) {
            out.write(b, 0, len);
        }
        ins.close();
        out.close();
        return true;
    }

    public static File createDir(String dirPath) {
        File file = new File(dirPath);
        if (file.exists()) {
            return file;
        }
        if (file.mkdirs()) {
            return file;
        } else {
            return null;
        }
    }

    public static File createFile(String dirPath, String fileName) throws IOException {
        if (createDir(dirPath) != null) {
            File file = new File(dirPath + fileName);
            if (file.exists()) {
                return file;
            }
            if (file.createNewFile()) {
                return file;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static File createFile(String filePath) throws IOException {
        int lastIndexOf = filePath.lastIndexOf("/") + 1;
        String dir = filePath.substring(0, lastIndexOf);
        String fileName = filePath.substring(lastIndexOf, filePath.length());
        return FileTool.createFile(dir, fileName);
    }

    public static String getMD5(File file) {
        FileInputStream fis = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            return bigInt.toString(16);
        } catch (IOException ex) {
            return null;
        } catch (NoSuchAlgorithmException ex) {
            return null;
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {

            }
        }
    }

    public static TreeNode dirToTree(File[] files, String rootPath) {
        String root = rootPath.substring(0, rootPath.lastIndexOf(File.pathSeparator) + 1);
        TreeNode treeNodes = new TreeNode(root);
        for (File file : files) {
            String path = file.getPath();
            path = path.replace(root, "");

            String[] pathNodes = path.split(File.pathSeparator);

        }
        return treeNodes;
    }


    private static class TreeNode {
        List<TreeNode> treeNodes = new ArrayList<>();
        private String nodeName;
        private boolean isFile;
        private String fileMD5;

        public void setFile(boolean file) {
            isFile = file;
        }

        public boolean isFile() {
            return isFile;
        }

        public String getFileMD5() {
            return fileMD5;
        }

        public void setFileMD5(String fileMD5) {
            this.fileMD5 = fileMD5;
        }

        public TreeNode(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public List<TreeNode> getTreeNodes() {
            return treeNodes;
        }

        public void setTreeNodes(List<TreeNode> treeNodes) {
            this.treeNodes = treeNodes;
        }

        public void addTreeNode(TreeNode treeNode) {
            this.treeNodes.add(treeNode);
        }
    }
}
