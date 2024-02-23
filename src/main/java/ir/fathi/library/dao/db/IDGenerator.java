package ir.fathi.library.dao.db;

import java.util.UUID;

public class IDGenerator {
    private static Long currentId;
    public static Long nextId(){
        if (currentId == null){
            currentId =1L;
        }
        return currentId++;
    }
}
