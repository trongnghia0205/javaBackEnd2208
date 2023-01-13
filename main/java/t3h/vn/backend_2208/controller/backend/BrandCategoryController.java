package t3h.vn.backend_2208.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import t3h.vn.backend_2208.config.paging.PagingParam;
import t3h.vn.backend_2208.dto.BrandCategoryDto;
import t3h.vn.backend_2208.dto.ResponseDto;
import t3h.vn.backend_2208.dto.ResponseTableDto;
import t3h.vn.backend_2208.service.BrandCategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller
public class BrandCategoryController {
    @Autowired
    BrandCategoryService brandCategoryService;

    @GetMapping("/backend/{path}/list")
    public String list(@PathVariable String path, @PagingParam(path = "path") ResponseTableDto dataTableDto, Model model){
        model.addAttribute("title", "brand".equalsIgnoreCase(path) ? " thương hiệu": "thể loại");
        model.addAttribute("categories", brandCategoryService.findAll("category"));
        dataTableDto.setPath(path);
        brandCategoryService.list(dataTableDto, path);
        return "brand-category/list";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/backend/{path}/create")
    public String create(Model model, @PathVariable String path,
                         HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("title", "brand".equalsIgnoreCase(path) ? " thương hiệu": "thể loại");
        model.addAttribute("path", path);
        return "brand-category/create";
    }

    @GetMapping("/backend/{path}/edit/{id}")
    public String edit(@PathVariable String path, @PathVariable Long id, Model model){
        model.addAttribute("title", "brand".equalsIgnoreCase(path) ? " thương hiệu": "thể loại");
        model.addAttribute("path", path);
        model.addAttribute("model", brandCategoryService.getById(id, path));
        return "brand-category/edit";
    }

    @PostMapping(value = "/backend/{path}/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createform(@PathVariable String path, BrandCategoryDto dto, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Model model) throws Exception {
        ResponseDto responseDto = brandCategoryService.save(dto, path);
        model.addAttribute("message", responseDto.getMessage());
        return "redirect:/backend/" + path + "/list";
    }

    @DeleteMapping(value = "/backend/{path}/delete/{id}")
    @ResponseBody
    public String delete(Model model, @PathVariable String path, @PathVariable Long id)
            throws SQLException {
        model.addAttribute("path", path);
        return brandCategoryService.delete(path, id);

//        return "/jsp/user/create.jsp";
    }
}
