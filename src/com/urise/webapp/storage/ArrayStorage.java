package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int len = 0;

    public void clear() {
//        for (int i = 0; i < len; i++) {
//            storage[i] = null;
//        }

        Arrays.fill(storage, null);
        len = 0;
    }
    public void save(Resume r) {
        // TODO: check if resume not present
        if(checkNoExist(r.getUuid())) {
            storage[len] = r;
            len++;
        }
    }

    public void update(Resume r) {
        // TODO: check if resume present
        if (checkExist(r.getUuid())) {
            for (int i = 0; i < len; i++) {
                if (storage[i].getUuid() == r.getUuid()) {
                    storage[i].setUuid("uuid4");
                    break;
                }
            }
        }
    }

    public Resume get(String uuid) {
        if(checkExist(uuid)) {
            for (int i = 0; i < len; i++) {
                if (storage[i].getUuid() == uuid) {
                    return storage[i];
                }
            }

        }
        return null;
    }

    public void delete(String uuid) {
        // TODO: check if resume present
        if (checkExist(uuid)) {
            for (int i = 0; i < len; i++) {
                if (storage[i].getUuid() == uuid) {
                    storage[i] = storage[len - 1];
                    storage[len - 1] = null;
                    len--;
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
       Resume[] resumes;// = new Resume[len];
//        System.arraycopy(storage, 0, resumes, 0, len);
        resumes = Arrays.copyOf(storage, len);
        return resumes;
    }

    public int size() {
        return len;
    }

    private boolean checkExist( String uuid) {
        for (int i = 0; i < len; i++) {
            if (storage[i].getUuid() == uuid){
                return true;
            }
        }
        System.out.println("ERROR");
        return false;
    }

    private boolean checkNoExist( String uuid) {
        for (int i = 0; i < len; i++) {
            if (storage[i].getUuid() == uuid){
                System.out.println("ERROR");
                return false;
            }
        }
        return true;
    }
}
