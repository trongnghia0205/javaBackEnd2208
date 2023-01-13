package t3h.vn.backend_2208.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import t3h.vn.backend_2208.dto.ProductDto;
import t3h.vn.backend_2208.dto.ResponseDto;
import t3h.vn.backend_2208.dto.ResponseTableDto;
import t3h.vn.backend_2208.entities.OrderEntity;
import t3h.vn.backend_2208.entities.ProductOrderEntity;
import t3h.vn.backend_2208.entities.ProductsEntity;
import t3h.vn.backend_2208.entities.UserEntity;
import t3h.vn.backend_2208.repositories.product.OrderRepository;
import t3h.vn.backend_2208.repositories.product.ProductOrderRepository;
import t3h.vn.backend_2208.repositories.product.ProductRepository;
import t3h.vn.backend_2208.utils.FileUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    FileUtils fileUtils;

    public List<ProductsEntity> findTop4(){
        return productRepository.findTop4Product();
    }
    public List<ProductsEntity> findAll(){
        return productRepository.findAll();
    }

    public ResponseDto save(ProductDto productDto) throws Exception {
        String mesage = "";
        ProductsEntity productsEntity = new ProductsEntity();
        if (productDto.getId() != null) {
            mesage = "cập nhập ";
            productsEntity = getById(productDto.getId());
            BeanUtils.copyProperties(productDto, productsEntity);
            productsEntity.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        } else {
            mesage = "Tạo mới ";
            productsEntity.setAlias(productDto.getAlias());
            productsEntity.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            productsEntity.setDiscountPercent(productDto.getDiscountPercent());
            productsEntity.setEnabled(productDto.isEnabled());
            productsEntity.setFullDescription(productDto.getFullDescription());
            productsEntity.setHeight(productDto.getHeight());
            productsEntity.setInStock(productDto.getInStock());
            productsEntity.setLength(productDto.getLength());
            productsEntity.setMainImage(productDto.getMainImage());
            productsEntity.setName(productDto.getName());
            productsEntity.setPrice(productDto.getPrice());
            productsEntity.setShortDescription(productDto.getShortDescription());
            productsEntity.setWeight(productDto.getWeight());
            productsEntity.setWidth(productDto.getWidth());
            productsEntity.setBrandId(productDto.getBrandId());
            productsEntity.setCategoryId(productDto.getCategoryId());
        }
        if (productDto.getFileImage() != null) {
            productsEntity.setMainImage(fileUtils.savefile(productDto.getFileImage()));
        }
        productRepository.save(productsEntity);

        ResponseDto responseDto = new ResponseDto(mesage + "thành công");
        return responseDto;
    }

    public void list(ResponseTableDto tableDto) {
        tableDto.list(productRepository);
    }

    public String delete(Long id) {
        ProductsEntity productsEntity = productRepository.findById(id).get();
        if (productsEntity == null)
            return "Sản phẩm không tồn tại";

        productRepository.delete(productsEntity);
        return "Xóa sản phẩm thành công";
    }

    public ProductsEntity getById(long id) {
        ProductsEntity productsEntity = productRepository.findById(id).get();
        return productsEntity;
    }

    static int DELETED = -1;
    static int ADD_TO_CART = 0;
    static int CREATED_ORDER = 1;
    static int PAYED = 2;
    @Autowired
    ProductOrderRepository productOrderRepository;
    public String createOrder(long id, String size, String color, int number) {
        ProductOrderEntity productsEntity = new ProductOrderEntity();
        productsEntity.setProductId(id);
        productsEntity.setNumber(number);
        productsEntity.setColor(color);
        productsEntity.setSize(size);
        productsEntity.setStatus(ADD_TO_CART);
        productsEntity.setUserId(((UserEntity)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getId());
        productOrderRepository.save(productsEntity);
        return "Tạo mới giỏ hàng thành công";
    }
    public int getOrderAddToCart(){
        if (SecurityContextHolder.getContext().getAuthentication() == null) return 0;
        Long userId = ((UserEntity)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getId();
        return productOrderRepository.countDistinctByUserIdAndStatus(userId, ADD_TO_CART);
    }

//
    public List<ProductOrderEntity> findAllByUserIdAndStatus(){
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) return new ArrayList<>();
        Long userId = ((UserEntity)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getId();
        return productOrderRepository.findAllByUserIdAndStatus(userId, ADD_TO_CART);
    }

    public String deleteCart(Long id) {
//        ProductOrderEntity productOrderEntity = productOrderRepository.findById(id).get();
//        productOrderEntity.setStatus(DELETED);
        productOrderRepository.deleteById(id);
        return "Xóa giỏ hàng thành công";
    }
    @Autowired
    OrderRepository orderRepository;
    public String checkout(List<ProductOrderEntity> orderEntities) {
        long total = 0;
        OrderEntity o = new OrderEntity();
        o.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        o.setUserId(((UserEntity)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getId());
        orderRepository.save(o);
        for (ProductOrderEntity e: orderEntities
        ) {
            ProductOrderEntity entity = productOrderRepository.findById(e.getId()).get();
            entity.setStatus(CREATED_ORDER);
            entity.setNumber(e.getNumber());
            entity.setOrderId(o.getId());
            total += entity.getNumber() * entity.getProductsEntity().getPrice();
            productOrderRepository.save(entity);
        }
        o.setTotal(total);
        orderRepository.save(o);
        return "Xóa giỏ hàng thành công";
    }

}
