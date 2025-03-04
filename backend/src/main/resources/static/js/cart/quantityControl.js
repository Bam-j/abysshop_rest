document.addEventListener("DOMContentLoaded", () => {
  const plusButtons = document.querySelectorAll(
      ".btn-primary[id='plus-button']");
  const minusButtons = document.querySelectorAll(
      ".btn-primary[id='minus-button']");

  plusButtons.forEach(button => {
    button.addEventListener("click", () => updateQuantity(button, "increase"));
  });

  minusButtons.forEach(button => {
    button.addEventListener("click", () => {
      const quantityElement = button.closest(
          ".quantity-controller").querySelector("strong");
      let quantity = parseInt(quantityElement.textContent, 10);

      if (quantity > 1) {
        updateQuantity(button, "decrease");
      }
    });
  });

  const updateQuantity = (button, operator) => {
    //증감 버튼에 hidden input을 달자니 jsp 구조가 너무 복잡해서져서 스크립트로 삽입
    const quantityElement = button.closest(
        ".quantity-controller").querySelector("strong");

    const cartId = button.dataset.cartId;
    const userId = button.dataset.userId;
    const productId = button.dataset.productId;

    const form = document.createElement("form");
    form.method = "POST";
    form.action = "/cart/item/update/quantity";

    const cartIdInput = document.createElement("input");
    cartIdInput.type = "hidden";
    cartIdInput.name = "cartId";
    cartIdInput.value = cartId;
    form.appendChild(cartIdInput);

    const userIdInput = document.createElement("input");
    userIdInput.type = "hidden";
    userIdInput.name = "userId";
    userIdInput.value = userId;
    form.appendChild(userIdInput);

    const productIdInput = document.createElement("input");
    productIdInput.type = "hidden";
    productIdInput.name = "productId";
    productIdInput.value = productId;
    form.appendChild(productIdInput);

    const operatorInput = document.createElement("input");
    operatorInput.type = "hidden";
    operatorInput.name = "operator";
    operatorInput.value = operator;
    form.appendChild(operatorInput);

    document.body.appendChild(form);
    form.submit();
  };
});