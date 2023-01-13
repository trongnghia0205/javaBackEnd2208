package t3h.vn.backend_2208.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import t3h.vn.backend_2208.utils.FileUtils;

@Data
public class BrandCategoryDto {
    @Autowired
    FileUtils fileUtils;
    Long id;
    String name;
    String description;
    MultipartFile fileImage;
    String mainImage;
    public void setFileImage(MultipartFile fileImage) {
        if (fileImage != null && StringUtils.isEmpty(fileImage.getOriginalFilename()))
            this.fileImage = null;
        else
            this.fileImage = fileImage;
    };
    public String setNameImage(MultipartFile fileImage) throws Exception{
        return fileUtils.savefile(fileImage);
    }
}
