package pl.kochanowska.imageuploader.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
public class ImageService {

    private Cloudinary cloudinary;
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dbe7dr12d",
                "api_key", "261636148983117",
                "api_secret", "VHikULzd9DN9mbcK6gNgIizpu7I"));
    }


    public String uploadFileAndSaveToDB(String path) {
        File file = new File(path);
        Map uploadResult = null;

        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepository.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {

        }
        return uploadResult.get("url").toString();
    }

    public List<Image> getAllImages(){
        return imageRepository.findAll();
    }

}

