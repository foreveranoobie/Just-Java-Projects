package com.epam.storozhuk.util;

import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.exceptions.ApplicationException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ProductSerializer {
    private static String fileName;

    public static void setFileName(String fileName) {
        ProductSerializer.fileName = fileName;
    }

    public static void serializeProduct(List<Hardware> toSerialize, int loopCount) throws ApplicationException {
        ArrayList<Hardware> productToSerialize = new ArrayList<>(toSerialize);
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            for (int loopIteration = 0; loopIteration < loopCount; loopIteration++) {
                objectOutputStream.writeObject(productToSerialize);
            }
            objectOutputStream.close();
        } catch (IOException e) {
            throw new ApplicationException("Error while product serialization");
        }
    }

    public static List<Hardware> deserializeProduct() throws ApplicationException {
        if (!Files.exists(Paths.get(fileName))) {
            return Collections.emptyList();
        }
        List<Hardware> deserializedProducts;
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            deserializedProducts = (List<Hardware>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new ApplicationException("Error while product deserialization");
        }
        return deserializedProducts;
    }
}
