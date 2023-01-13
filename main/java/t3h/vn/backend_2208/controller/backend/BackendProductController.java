package t3h.vn.backend_2208.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import t3h.vn.backend_2208.config.paging.PagingParam;
import t3h.vn.backend_2208.dto.ProductDto;
import t3h.vn.backend_2208.dto.ResponseDto;
import t3h.vn.backend_2208.dto.ResponseTableDto;
import t3h.vn.backend_2208.entities.ProductOrderEntity;
import t3h.vn.backend_2208.service.BrandCategoryService;
import t3h.vn.backend_2208.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
// 1: dựa vào link https://gist.github.com/m-cakir/05470e679b73e2036254cef949432fcc
// để thực hiện lưu ảnh trên google storage
// 2: Tạo 1 bảng product_image(id, productId, image_url) 1 sản phẩm có thể có nhiều ảnh
// 3: Tạo 1 tab (cùng màn hình với create và detail product)
// để thực hiện upload nhiều ảnh và lưu vào CSDL theo product id

@Controller
@RequestMapping("/backend/product")
public class BackendProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    BrandCategoryService brandCategoryService;

    @GetMapping("/list")
    public String listProduct(
            @PagingParam(path = "product")
                    ResponseTableDto responseTableDto) {
        productService.list(responseTableDto);
        return "product/list";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/create")
    private String create(Model model, HttpServletRequest request, HttpServletResponse response) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
//        model.addAttribute("brands", brandCategoryService.findAll("brand"));
//        model.addAttribute("categories",  brandCategoryService.findAll("category"));
        return "product/create";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String product(@PathVariable long id, Model model) {
        model.addAttribute("productDto", productService.getById(id));
        model.addAttribute("categories", brandCategoryService.findAll("category"));
        model.addAttribute("brands", brandCategoryService.findAll("brand"));
        return "product/edit";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        model.addAttribute("addToCart", productService.getOrderAddToCart());
        model.addAttribute("product", productService.getById(id));
        return "detail";
    }



    @PostMapping(value = "/save",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String save(@Valid @ModelAttribute("productDto") ProductDto productDto,
//                       @RequestParam("fileImage") MultipartFile file,
                       BindingResult bindingResult,
                       Model model, RedirectAttributes redirectAttributes) throws Exception {
        ResponseDto responseDto = productService.save(productDto);
        model.addAttribute("message", responseDto.getMessage());
        return "redirect:/backend/product/list";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping("/order/{id}")
    public String order(@PathVariable Long id, @RequestParam String color,
                        @RequestParam String size, @RequestParam Integer number, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", productService.createOrder(id, size, color, number));
        return "redirect:/backend/product/detail/" + id;
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping("cart")
    public String cart(Model model){
        model.addAttribute("products", productService.findAllByUserIdAndStatus());
        return "product/cart";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping("deleteCart/{productId}/{id}")
    public String deleteCart( @PathVariable Long productId, @PathVariable Long id){
        productService.deleteCart(id);
        return "redirect:/backend/product/cart";
    }
    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping("/checkout")
    @ResponseBody
    public String checkout(@RequestBody List<ProductOrderEntity> orderEntities){
        productService.checkout(orderEntities);
        return "Đã thanh toán thành công";
    }

}
