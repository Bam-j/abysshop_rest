package com.joo.abysshop.controller;

import com.joo.abysshop.annotation.AdminOnly;
import com.joo.abysshop.util.constants.ModelAttributeNames;
import com.joo.abysshop.util.constants.ViewNames;
import com.joo.abysshop.dto.admin.AddProductRequest;
import com.joo.abysshop.dto.admin.ChangeOrderStateRequest;
import com.joo.abysshop.dto.admin.ChangePointRechargeStateRequest;
import com.joo.abysshop.dto.admin.ProvidePointRequest;
import com.joo.abysshop.dto.order.OrderListResponse;
import com.joo.abysshop.dto.point.PointRechargeDetailListResponse;
import com.joo.abysshop.dto.point.PointRechargeListResponse;
import com.joo.abysshop.dto.product.ProductListResponse;
import com.joo.abysshop.service.admin.AdminMyPageService;
import com.joo.abysshop.service.admin.AdminOrderManagementService;
import com.joo.abysshop.service.admin.AdminPointManagementService;
import com.joo.abysshop.service.order.OrderService;
import com.joo.abysshop.service.point.PointRechargeManagementService;
import com.joo.abysshop.service.point.PointRechargeService;
import com.joo.abysshop.service.product.ProductService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminMyPageService adminMyPageService;
    private final AdminOrderManagementService adminOrderManagementService;
    private final AdminPointManagementService adminPointManagementService;
    private final PointRechargeManagementService pointRechargeManagementService;
    private final OrderService orderService;
    private final PointRechargeService pointRechargeService;
    private final ProductService productService;

    @AdminOnly
    @GetMapping("/admin/my-page")
    public String getAdminMyPage(
        @RequestParam(value = "menu", defaultValue = "order-management") String menu,
        @RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10;

        if ("order-management".equals(menu)) {
            int totalOrders = orderService.getTotalOrderCount();
            int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

            List<OrderListResponse> orderList = adminOrderManagementService.findPagedOrders(page,
                pageSize);
            model.addAttribute(ModelAttributeNames.ORDER_LIST, orderList);
            model.addAttribute(ModelAttributeNames.CURRENT_PAGE, page);
            model.addAttribute(ModelAttributeNames.TOTAL_PAGES, totalPages);
        } else if ("point-recharge-management".equals(menu)) {
            int totalPointRecharges = pointRechargeService.getTotalPointRechargeCount();
            int totalPages = (int) Math.ceil((double) totalPointRecharges / pageSize);
            List<PointRechargeListResponse> pointRechargeList =
                adminPointManagementService.getPagedPointRecharge(page, pageSize);
            model.addAttribute(ModelAttributeNames.POINT_RECHARGE_LIST, pointRechargeList);
            model.addAttribute(ModelAttributeNames.CURRENT_PAGE, page);
            model.addAttribute(ModelAttributeNames.TOTAL_PAGES, totalPages);
        } else if ("point-recharge-detail".equals(menu)) {
            int totalPointRechargeDetails = pointRechargeManagementService.getTotalPointRechargeDetailCount();
            int totalPages = (int) Math.ceil((double) totalPointRechargeDetails / pageSize);
            List<PointRechargeDetailListResponse> pointRechargeDetailList =
                pointRechargeManagementService.getPagedPointRechargeDetail(page, pageSize);
            model.addAttribute(ModelAttributeNames.POINT_RECHARGE_DETAIL_LIST,
                pointRechargeDetailList);
            model.addAttribute(ModelAttributeNames.CURRENT_PAGE, page);
            model.addAttribute(ModelAttributeNames.TOTAL_PAGES, totalPages);
        } else if ("add-product".equals(menu)) {
        } else if ("remove-product".equals(menu)) {
            int totalProducts = productService.getTotalProductCount();
            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
            List<ProductListResponse> productList =
                adminMyPageService.getPagedProducts(page, pageSize);
            model.addAttribute(ModelAttributeNames.PRODUCT_LIST, productList);
            model.addAttribute(ModelAttributeNames.CURRENT_PAGE, page);
            model.addAttribute(ModelAttributeNames.TOTAL_PAGES, totalPages);
        }

        return ViewNames.ADMIN_MY_PAGE;
    }

    @AdminOnly
    @PostMapping("/admin/order/change-state")
    public RedirectView changeOrderState(
        @ModelAttribute ChangeOrderStateRequest changeOrderStateRequest, Model model) {
        adminOrderManagementService.changeOrderState(changeOrderStateRequest);
        return new RedirectView("/admin/my-page");
    }

    @AdminOnly
    @PostMapping("/admin/point/provide")
    public RedirectView providePoint(@ModelAttribute ProvidePointRequest providePointRequest) {
        adminPointManagementService.providePoint(providePointRequest);
        return new RedirectView("/admin/my-page?menu=point-recharge-management");
    }

    @AdminOnly
    @PostMapping("/admin/product/add")
    public RedirectView addProduct(@ModelAttribute AddProductRequest addProductRequest)
        throws IOException {
        adminMyPageService.addProduct(addProductRequest);
        return new RedirectView("/admin/my-page?menu=add-product");
    }

    @AdminOnly
    @PostMapping("/admin/product/remove")
    public RedirectView removeProduct(@RequestParam("productId") Long productId) {
        adminMyPageService.removeProduct(productId);
        return new RedirectView("/admin/my-page?menu=remove-product");
    }

    @AdminOnly
    @PostMapping("/admin/recharge/change-state")
    public RedirectView changePointRechargeState(
        @ModelAttribute ChangePointRechargeStateRequest changePointRechargeStateRequest) {
        adminPointManagementService.changePointRechargeState(changePointRechargeStateRequest);
        return new RedirectView("/admin/my-page?menu=point-recharge-management");
    }
}