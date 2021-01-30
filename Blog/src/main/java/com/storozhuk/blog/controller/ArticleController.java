package com.storozhuk.blog.controller;

import com.storozhuk.blog.formatter.ChainArticleFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/getForm")
    public String getArticleForm() {
        return "article/form";
    }

    @PostMapping("/saveArticle")
    public String saveArticle(@RequestParam String articleName, @RequestParam String articleText,
                              @Autowired ChainArticleFormatter articleFormatter) throws IOException {
        articleText = articleFormatter.doChain(articleText);
        articleName = "<h1>" + articleName + "</h1>";
        System.out.println(articleName + "\n" + articleText);
        FileOutputStream fos = new FileOutputStream("E:\\articles\\test.html");
        fos.write(articleName.getBytes());
        fos.write("\n".getBytes());
        fos.write(articleText.getBytes());
        fos.close();
        return null;
    }

    @PostMapping("/previewArticle")
    @ResponseBody
    public String previewArticle(@RequestParam String articleName, @RequestParam String articleText,
                                 @Autowired ChainArticleFormatter articleFormatter) {
        articleText = articleFormatter.doChain(articleText);
        articleName = "<h1>" + articleName + "</h1>";
        return articleName + articleText;
    }

    @PostMapping("/saveImage")
    @ResponseBody
    public String saveImage(@RequestParam MultipartFile image, @RequestParam int number) throws IOException {
        FileOutputStream fos = new FileOutputStream("src/main/resources/static/articles/template/" + image.getName() + "_" + number + ".jpg");
        fos.write(image.getBytes());
        fos.close();
        return image.getName() + "_" + number + ".jpg";
    }

    @GetMapping(value = "/getPreviewUploadedImage", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> getPreviewUploadedImage(@RequestParam String imageName) throws IOException {
        ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                "src/main/resources/static/articles/template/" + imageName)));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);
    }

    @PostMapping(value = "test")
    public String doTest(Model model, @RequestParam String articleName, @RequestParam String articleText,
                         @Autowired ChainArticleFormatter articleFormatter) {
        articleText = articleFormatter.doChain(articleText);
        model.addAttribute("code",
                articleText);
        return "article/test";
    }
}
