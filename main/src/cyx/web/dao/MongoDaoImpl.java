package cyx.web.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MongoDaoImpl<T> implements IBaseDao<T> {

    private Class<T> clazz;
    private final static MongoClient mongoClient  = new MongoClient("localhost");
    private MongoDatabase database;

    public MongoDaoImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) type.getActualTypeArguments()[0];
        database = mongoClient.getDatabase("file_project");
    }

    @Override
    public int save(T entity) {
        String tableName = clazz.getName();
        MongoCollection<Document> collection = database.getCollection(tableName);
        Field[] fields = clazz.getFields();
        Document doc = new Document();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                doc.append(field.getName(), field.get(clazz));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    public int saveList(List<T> entityList) {
        String tableName = clazz.getName().replaceAll("\\.", "_");
        MongoCollection<Document> collection = database.getCollection(tableName);
        List<Document> documents = new ArrayList<>();
        for (T t : entityList) {
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            Map<String, Object> _map = new HashMap<>();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    _map.put(field.getName(), field.get(t));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            Document doc = new Document(_map);
            documents.add(doc);
        }
        collection.insertMany(documents);
        return entityList.size();
    }

    @Override
    public int delete(Serializable id) {
        return 0;
    }

    @Override
    public int update(T entity) {
        return 0;
    }

    @Override
    public List<T> queryAll() {
        List<T> resultList = new ArrayList<>();
        String tableName = clazz.getName().replaceAll("\\.", "_");
        MongoCollection<Document> collection = database.getCollection(tableName);
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> mongoCursor = documents.iterator();
        while (mongoCursor.hasNext()) {
            Document doc = mongoCursor.next();
            try {
                Object entity = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(entity, doc.get(field.getName()));
                }
                resultList.add((T) entity);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return resultList;
    }

    @Override
    public T queryByKey() {
        return null;
    }
}
