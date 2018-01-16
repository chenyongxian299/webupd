package cyx.web.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    int save(T entity);

    int delete(Serializable id);

    int update(T entity);

    List<T> queryAll();

    T queryByKey();


}
