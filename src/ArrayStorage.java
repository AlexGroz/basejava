/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int len = 0;

    void clear() {
        for (int i = 0; i < len; i++) {
            storage[i] = null;
        }
        len = 0;
    }

    void save(Resume r) {
        storage[len] = r;
        len++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < len; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }


    void delete(String uuid) {
        boolean isNumberFind = false;

        for (int i = 0; i < len; i++) {
            if (storage[i].uuid == uuid) {
                isNumberFind = true;
            }
            if (isNumberFind) {
                storage[i] = storage[i + 1];
            }
        }
        storage[len - 1] = null;
        len = len - 1;
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[len];
        System.arraycopy(storage, 0, resumes, 0, len);
        return resumes;
    }

    int size() {
        return len;
    }
}
