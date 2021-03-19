package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int len = 0;

    public int size() {
        return len;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, len);
    }

    public void clear() {
        Arrays.fill(storage, 0, len, null);
        len = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (len == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, index);
            storage[len] = r;
            len++;
        }
    }

    protected abstract void insertElement(Resume r, int index);

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            fillDeletedElement(index);
            storage[len - 1] = null;
            len--;
        }
    }

    protected abstract void fillDeletedElement(int index);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}


