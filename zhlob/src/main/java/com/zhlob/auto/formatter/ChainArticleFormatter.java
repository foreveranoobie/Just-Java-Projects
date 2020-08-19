package com.zhlob.auto.formatter;

import com.zhlob.auto.formatter.chain.ArticleFormatter;
import com.zhlob.auto.formatter.chain.HeaderArticleFormatter;
import com.zhlob.auto.formatter.chain.ImageArticleFormatter;
import com.zhlob.auto.formatter.chain.VideoArticleFormatter;
import org.springframework.stereotype.Component;

@Component
public class ChainArticleFormatter {
    private ArticleFormatter articleFormatter;

    public ChainArticleFormatter() {
        articleFormatter = new HeaderArticleFormatter();
        articleFormatter.setNextFormatter(new ImageArticleFormatter()).setNextFormatter(new VideoArticleFormatter());
    }

    public String doChain(String input) {
        return articleFormatter.formatInput(input);
    }
}
