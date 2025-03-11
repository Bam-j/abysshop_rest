package com.joo.abysshop.controller.cart;

import com.joo.abysshop.annotation.CurrentUserOnly;
import com.joo.abysshop.util.constants.Messages;
import com.joo.abysshop.util.constants.ModelAttributeNames;
import com.joo.abysshop.util.constants.RedirectMappings;
import com.joo.abysshop.util.constants.ViewNames;
import com.joo.abysshop.dto.cart.AddItemRequest;
import com.joo.abysshop.dto.cart.CartItemResponse;
import com.joo.abysshop.dto.cart.CartResponse;
import com.joo.abysshop.dto.cart.RemoveItemRequest;
import com.joo.abysshop.dto.cart.UpdateQuantityRequest;
import com.joo.abysshop.dto.user.UserInfoResponse;
import com.joo.abysshop.mapper.dto.ToCartDTOMapper;
import com.joo.abysshop.service.cart.CartService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ToCartDTOMapper toCartDTOMapper;

    @CurrentUserOnly
    @GetMapping("/user/cart/{userId}")
    public String getUserCartPage(@PathVariable("userId") Long userId, Model model) {
        CartResponse cart = cartService.getCart(userId);
        model.addAttribute(ModelAttributeNames.CART, cart);

        Long cartId = cart.getCartId();
        List<CartItemResponse> cartItemList = cartService.getUserCartItems(cartId);
        model.addAttribute(ModelAttributeNames.CART_ITEM_LIST, cartItemList);
        return ViewNames.SHOPPING_CART_PAGE;
    }

    //detail 페이지의 장바구니 추가 버튼에 대한 quantity 증가 or 상품 추가 컨트롤러
    @PostMapping("/cart/item/add")
    public String addItemToCart(@ModelAttribute AddItemRequest addItemRequest, HttpSession session,
        RedirectAttributes redirectAttributes) {
        UserInfoResponse user = (UserInfoResponse) session.getAttribute("user");

        if (user == null) {
            //로그인을 하지 않은 경우 장바구에 담기 요청을 수행하지 못하도록
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "로그인을 해주세요.");
            return RedirectMappings.REDIRECT_INDEX;
        } else if (user.getUserType().equals("admin")) {
            //관리자의 경우 장바구니 담기 요청을 수행하지 못하도록
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "불가능한 요청입니다. (관리자 계정)");
            return RedirectMappings.REDIRECT_INDEX;
        }

        if (cartService.isCartItemExists(addItemRequest)) {
            //카트에 상품이 존재하면 레코드를 새로 추가하지 않고, 존재하는 레코드의 quantity를 1 증가. price 증가
            cartService.increaseQuantity(addItemRequest);
            cartService.increaseTotalPrice(addItemRequest);
        } else {
            //카트에 상품이 존재하지 않으면 새로 레코드 추가
            cartService.addItem(addItemRequest);
        }

        Long cartId = addItemRequest.getCartId();
        cartService.updateCart(cartId);
        return RedirectMappings.REDIRECT_INDEX;
    }

    //cart에서 삭제 버튼을 클릭했을 때 상품 레코드를 제거하는 컨트롤러
    @PostMapping("/cart/item/remove")
    public RedirectView removeItemFromCart(@ModelAttribute RemoveItemRequest removeItemRequest,
        Model model) {
        cartService.removeItem(removeItemRequest);

        Long userId = removeItemRequest.getUserId();
        CartResponse cart = cartService.getCart(userId);
        model.addAttribute(ModelAttributeNames.CART, cart);

        Long cartId = cart.getCartId();
        cartService.updateCart(cartId);
        return new RedirectView("/user/cart/" + userId);
    }

    /*
    *   TODO: 수량증가 방식 변경!
    *       기존에 클릭마다 요청을 보냈던 비효율 방식에서
    *       프론트에서 수량 조절이 감지되면 적당한 시간(1~5초) 후에 데이터를 종합해서
    *       서버로 요청을 보내는 방식으로 변경해서 구현
     */
    //cart에서 증감 버튼 클릭 시 quantity를 조정하는 메소드
    @PostMapping("/cart/item/update/quantity")
    public RedirectView updateQuantity(@ModelAttribute UpdateQuantityRequest updateQuantityRequest) {
        String operator = updateQuantityRequest.getOperator();

        if (operator.equals("increase")) {
            AddItemRequest addItemRequest = toCartDTOMapper.toAddItemRequest(updateQuantityRequest);
            cartService.increaseQuantity(addItemRequest);
            cartService.increaseTotalPrice(addItemRequest);
        } else {
            RemoveItemRequest removeItemRequest = toCartDTOMapper.toRemoveItemRequest(updateQuantityRequest);
            cartService.decreaseQuantity(removeItemRequest);
            cartService.decreaseTotalPrice(removeItemRequest);
        }

        Long cartId = updateQuantityRequest.getCartId();
        cartService.updateCart(cartId);

        Long userId = updateQuantityRequest.getUserId();
        return new RedirectView("/user/cart/" + userId);
    }
}
