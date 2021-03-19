package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    int zg = 2;
    @Override
    protected void insertElement(Resume r, int index) {
        storage[len] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[len - 1];
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */

    protected int getIndex(String uuid) {
        for (int i = 0; i < len; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}


