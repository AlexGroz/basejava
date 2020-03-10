/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int len = 0;

    void clear() {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] == null) break;
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[len] = r;
        len++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) break;
            if (storage[i].uuid == uuid) return storage[i];
        }
        return null;
    }


    void delete(String uuid) {
        int lastNumber = 0;
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] == null) break;
            if (storage[i].uuid == uuid) {
                for (int j = i; j < storage.length - 1; j++) {
                    if (storage[j + 1] == null) {
                        lastNumber = j;
                        break;
                    }
                    storage[j] = storage[j + 1];
                }
                storage[lastNumber] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int i = 0;
        for (; i < storage.length - 1; i++) {
            if (storage[i] == null) break;
        }
        Resume[] storageClone = new Resume[i];
        System.arraycopy(storage, 0, storageClone, 0, i);
        return storageClone;
    }

    int size() {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] == null) return i;
        }
        return 0;
    }
}
