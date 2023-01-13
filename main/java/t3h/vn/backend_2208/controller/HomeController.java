package t3h.vn.backend_2208.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import t3h.vn.backend_2208.dto.ResponseDto;
import t3h.vn.backend_2208.dto.UserDto;
import t3h.vn.backend_2208.service.BrandCategoryService;
import t3h.vn.backend_2208.service.ProductService;
import t3h.vn.backend_2208.service.UserService;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    BrandCategoryService brandCategoryService;
    @GetMapping({"home","","/"})
    public String home(Model model) {
        model.addAttribute("categories", brandCategoryService.findAll("category"));
        model.addAttribute("products", productService.findTop4());
        model.addAttribute("productlist", productService.findAll());
        return "home";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "register/save",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registerSave(UserDto userDto, RedirectAttributes redirectAttributes) {
        ResponseDto responseDto = userService.register(userDto);
        redirectAttributes.addFlashAttribute("message", responseDto.getMessage());
        return "redirect:/register";
    }

    @GetMapping("verify/{id}")
    public String verify(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        String result = userService.verify(id);
        redirectAttributes.addFlashAttribute("message", result);
        return "redirect:/login";
    }

}
