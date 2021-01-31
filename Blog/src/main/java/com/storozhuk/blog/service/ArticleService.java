package com.storozhuk.blog.service;

import com.storozhuk.blog.entity.Article;
import com.storozhuk.blog.formatter.ChainArticleFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ArticleService {
    private final String clipboardImagesTemplateDir = "src/main/resources/static/articles/autozhlob/template/";
    private final String clipboardImagesDir = "src/main/resources/static/articles/autozhlob/";
    private final String defaultArticleName = "article.html";

    public boolean saveArticle(Article article, @Autowired ChainArticleFormatter articleFormatter) {
        article.setText(articleFormatter.doChain(article.getText()));
        try {
            Files.createDirectory(Paths.get(clipboardImagesDir + article.getName()));
            checkClipboardImages(article);
            saveHtml(article);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void saveHtml(Article article) throws IOException {
        FileOutputStream fos = new FileOutputStream(clipboardImagesDir + article.getName() + "/" + defaultArticleName);
        fos.write(("<h1>" + article.getName() + "</h1><br/>").getBytes());
        fos.write(article.getText().getBytes());
        fos.close();
    }

    private void checkClipboardImages(Article article) throws IOException {
        String imagePattern =
                "<img style='display: block; margin: 1% auto 2% auto; max-width:50%; max-height:50%;' " +
                        "src='/articles/template/image_[0-9]+.jpg'/>";
        Pattern pattern = Pattern.compile(imagePattern, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(article.getText());
        String group;
        int indexOfImage;
        int indexOfImageEnd;
        String imageName;
        while (matcher.find()) {
            group = matcher.group();
            indexOfImage = group.indexOf("image_");
            indexOfImageEnd = group.indexOf("'", indexOfImage);
            imageName = group.substring(indexOfImage, indexOfImageEnd);
            Files.copy(Paths.get(clipboardImagesTemplateDir + imageName),
                    Paths.get(clipboardImagesDir + article.getName() + "/" + imageName));
        }
        for (File file : Objects.requireNonNull(Paths.get(clipboardImagesTemplateDir).toFile().listFiles())) {
            file.delete();
        }
    }
}
