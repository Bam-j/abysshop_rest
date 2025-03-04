package com.joo.abysshop.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedirectMappings {
    public static final String REDIRECT_INDEX =  "redirect:/";

    public static final String REDIRECT_SIGN_IN = "redirect:/account/sign-in";
    public static final String REDIRECT_SIGN_UP = "redirect:/account/sign-up";

    public static final String REDIRECT_ORDER_COMPLETE = "redirect:/order/complete";
}
