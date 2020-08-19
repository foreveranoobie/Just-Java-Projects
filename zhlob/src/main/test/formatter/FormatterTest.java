package formatter;

import com.zhlob.auto.formatter.chain.ArticleFormatter;
import org.springframework.beans.factory.annotation.Autowired;

public class FormatterTest {
    @Autowired
    private ArticleFormatter articleFormatter;

    public void testOnCorrectImageFormatting() {
        String image = "<image>https://cdn4.riastatic.com/photos/ir/new/auto/photo/toyota_camry__344979124-620x415x70.jpg</image>";
    }

    public void testOnCorrectVideoFormatting() {

    }

    public void testOnCorrectHeaderFormatting() {

    }
}
