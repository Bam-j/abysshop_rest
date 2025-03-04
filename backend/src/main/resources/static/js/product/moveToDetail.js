const items = document.getElementsByClassName('item');

Array.from(items).forEach(item => {
  item.addEventListener('click', () => {
    const productId =  parseInt(item.dataset.productId);
    window.location.href = `/product/detail/` + productId;
  });
});