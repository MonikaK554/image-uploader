package pl.kochanowska.imageuploader.image;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestBody String path, Model model) {
        String pathUrl = imageService.uploadFileAndSaveToDB(path);
        model.addAttribute("pathUrl", pathUrl);
        return "upload";
    }

    @GetMapping("/gallery")
    public String getGallery(Model model){
        List<String> imageUrls = imageService.getAllImages().stream().map(i -> i.getPathUrl()).collect(Collectors.toList());
        model.addAttribute("imageUrls", imageUrls);
        return "gallery";
    }


}






