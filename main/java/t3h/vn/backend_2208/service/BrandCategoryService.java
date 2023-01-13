package t3h.vn.backend_2208.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t3h.vn.backend_2208.dto.BrandCategoryDto;
import t3h.vn.backend_2208.dto.ResponseDto;
import t3h.vn.backend_2208.dto.ResponseTableDto;
import t3h.vn.backend_2208.entities.BrandEntity;
import t3h.vn.backend_2208.entities.CategoryEntity;
import t3h.vn.backend_2208.entities.UserEntity;
import t3h.vn.backend_2208.repositories.BrandRepository;
import t3h.vn.backend_2208.repositories.CategoryRepository;
import t3h.vn.backend_2208.utils.FileUtils;

import java.util.List;

@Service
public class BrandCategoryService {
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    FileUtils fileUtils;


    public ResponseDto save(BrandCategoryDto dto, String path) throws Exception{
        ResponseDto responseDto = new ResponseDto("Tạo mới lỗi");
        if ("brand".equalsIgnoreCase(path)) {
            BrandEntity brandEntity = new BrandEntity();
            BeanUtils.copyProperties(dto, brandEntity);
            if (dto.getFileImage() != null) {
                brandEntity.setMainImage(fileUtils.savefile(dto.getFileImage()));
            }
            brandRepository.save(brandEntity);
        } else if ("category".equalsIgnoreCase(path)){
            CategoryEntity categoryEntity = new CategoryEntity();
            BeanUtils.copyProperties(dto, categoryEntity);
            if (dto.getFileImage() != null) {
                categoryEntity.setMainImage(fileUtils.savefile(dto.getFileImage()));
            }
            categoryRepository.save(categoryEntity);
        }
        responseDto = new ResponseDto("Tạo mới thành công");
        return responseDto;
    }

    public BrandCategoryDto getById(Long id, String path) {
        BrandCategoryDto brandCategoryDto = new BrandCategoryDto();
        if ("brand".equalsIgnoreCase(path)) {
            BrandEntity brandEntity = brandRepository.findById(id).get();
            BeanUtils.copyProperties(brandEntity, brandCategoryDto);
        } else if ("category".equalsIgnoreCase(path)){
            CategoryEntity categoryEntity = categoryRepository.findById(id).get();
            BeanUtils.copyProperties(categoryEntity, brandCategoryDto);
        }
        return brandCategoryDto;
    }

    public void list(ResponseTableDto dataTableDto, String path) {
        if ("brand".equalsIgnoreCase(path)) {
            dataTableDto.list(brandRepository);
        } else if("category".equalsIgnoreCase(path)){
            dataTableDto.list(categoryRepository);
        }
    }

    public String delete(String path, Long id) {
        if ("brand".equalsIgnoreCase(path)) {
            brandRepository.deleteById(id);
        } else if("category".equalsIgnoreCase(path)){
            categoryRepository.deleteById(id);
        }
        return "Xóa thành công";
    }

    public List<?> findAll(String path) {
        if ("brand".equalsIgnoreCase(path))
            return brandRepository.findAll();
        else
            return categoryRepository.findAll();
    }
}
