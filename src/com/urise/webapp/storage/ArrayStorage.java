package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int len = 0;

    public void clear() {
        for (int i = 0; i < len; i++) {
            storage[i] = null;
        }
        len = 0;
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
                System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (len == storage.length) {
            System.out.println("Storage overflow");
        } else {
            storage[len] = r;
            len++;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            storage[index] = storage[len - 1];
            storage[len - 1] = null;
            len--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] result = new Resume[len];
        for (int i = 0; i < len; i++) {
            result[i] = storage[i];
        }
        return result;
    }

    public int size() {
        return len;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < len; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }
}


