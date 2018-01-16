package cyx.web.service;

import java.io.IOException;
import java.util.Set;

public interface ILocalPath {

     Set getPath();

     void addPath(String path);

     void savePath();

}
