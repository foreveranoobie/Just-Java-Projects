package com.storozhuk.blog.formatter;

import com.storozhuk.blog.formatter.chain.ArticleFormatter;
import com.storozhuk.blog.formatter.chain.HeaderArticleFormatter;
import com.storozhuk.blog.formatter.chain.ImageArticleFormatter;
import com.storozhuk.blog.formatter.chain.VideoArticleFormatter;
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
