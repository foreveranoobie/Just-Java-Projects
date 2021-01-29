package com.storozhuk.blog.formatter.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageArticleFormatter extends ArticleFormatter {
    private final String imagePattern = "<img>((?:(?!</?img[ >]).)*)</img>";

    @Override
    public String formatInput(String input) {
        Pattern pattern = Pattern.compile(imagePattern, Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        StringBuilder currentUrl;
        String group;
        String src;
        while (matcher.find()) {
            group = matcher.group();
            currentUrl = new StringBuilder(group.substring(5, group.length() - 6));
            if (currentUrl.toString().startsWith("image_")) {
                src = "src='/article/getPreviewUploadedImage?imageName=" + currentUrl + "'";
            } else {
                src = "src='" + currentUrl + "'";
            }
            input = input.replace(matcher.group(),
                    "<br/><img style='max-width:30%; max-height:30%;' " + src + "/></br>");
        }
        return (next != null ? next.formatInput(input) : input);
    }
}
