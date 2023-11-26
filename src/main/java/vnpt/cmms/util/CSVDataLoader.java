package vnpt.cmms.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class CSVDataLoader {

    private static Logger logger = LoggerFactory.getLogger(CSVDataLoader.class);
    public static final String USER_FILE = "C:\\Users\\Admin\\Desktop\\MobileMoney\\concen\\app\\src\\main\\resources\\data.csv";

    public static <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
//            InputStream inputStream = new ClassPathResource(fileName).getInputStream();
            InputStream inputStream = new FileInputStream(new File(fileName));;
            File file = File.createTempFile("tmp", ".csv");
            FileUtils.copyInputStreamToFile(inputStream, file);
//            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues =
                    mapper.reader(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
}
