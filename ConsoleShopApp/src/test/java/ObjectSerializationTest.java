import com.epam.storozhuk.dao.impl.ProductDAO;
import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.entities.VideoCard;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ObjectSerializationTest {
    private ProductDAO productDAO;
    private GZIPOutputStream gzipOutputStream;
    private GZIPInputStream gzipInputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private String fileName;

    @Before
    public void initStreamAndDAO() {
        fileName = "testProducts.gzip";
        Path filePath = Paths.get(fileName);
        productDAO = new ProductDAO();
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        productDAO.addProduct(new CPU(0, 0, BigDecimal.valueOf(0), "0", "0"));
        productDAO.addProduct(new VideoCard(1, 1, BigDecimal.valueOf(1), "1", "1"));
        productDAO.addProduct(new Intel(2, 2, BigDecimal.valueOf(2), "2", "2", "2", "2"));
    }

    @Test
    public void testProductsArchiving() {
        List<Hardware> hardwareList = new ArrayList<>();
        Intel expectedLastElement = new Intel(2, 2, BigDecimal.valueOf(2), "2", "2", "2", "2");
        try {
            gzipOutputStream = new GZIPOutputStream(new FileOutputStream(fileName));
            objectOutputStream = new ObjectOutputStream(gzipOutputStream);
            objectOutputStream.writeObject(productDAO.getProducts());
            objectOutputStream.close();
            gzipInputStream = new GZIPInputStream(new FileInputStream(fileName));
            objectInputStream = new ObjectInputStream(gzipInputStream);
            hardwareList = (List<Hardware>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(3, hardwareList.size());
        assertEquals(expectedLastElement, hardwareList.get(hardwareList.size() - 1));
    }

    @Test
    public void testArchiveAndBinaryFilesSizes() throws IOException {
        gzipOutputStream = new GZIPOutputStream(new FileOutputStream(fileName));
        objectOutputStream = new ObjectOutputStream(gzipOutputStream);
        objectOutputStream.writeObject(productDAO.getProducts());
        long archivedFileSize = Paths.get(fileName).toFile().length();
        objectOutputStream = new ObjectOutputStream(new FileOutputStream("testProducts.ser"));
        objectOutputStream.writeObject(productDAO.getProducts());
        long binaryFileSize = Paths.get("testProducts.ser").toFile().length();
        assertTrue(archivedFileSize < binaryFileSize);
        objectOutputStream.close();
    }

    @Test
    public void checkMultipleSerialization() throws IOException {
        objectOutputStream = new ObjectOutputStream(new FileOutputStream("testProducts.ser"));
        objectOutputStream.writeObject(productDAO.getProducts());
        long oneTimeSerializedSize = Paths.get("testProducts.ser").toFile().length();
        for (int i = 0; i < 20; i++) {
            objectOutputStream.writeObject(productDAO.getProducts());
        }
        long tenTimeSerializedSize = Paths.get("testProducts.ser").toFile().length();
        assertTrue(tenTimeSerializedSize < oneTimeSerializedSize * 2);
        objectOutputStream.close();
    }
}
