package com.joo.abysshop.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductImageController {

    private final String IMAGE_DIR = "C:/Users/juhyu/abysshop_img/";

    @GetMapping("/image/{originalFileName}")
    public Resource getImage(@PathVariable String originalFileName, Model model)
        throws MalformedURLException {
        Path path = Paths.get(IMAGE_DIR + originalFileName);
        return new UrlResource(path.toUri());
    }
}
