document.addEventListener("click", event => {
    const selectedItem = event.target.closest(".dropdown-item");
    if (!selectedItem) {
      return;
    }

    event.preventDefault();

    const selectedText = selectedItem.textContent;
    const selectedValue = selectedItem.getAttribute("data-value");

    const btnGroup = selectedItem.closest(".btn-group");
    if (!btnGroup) {
      return;
    }

    const dropdownButton = btnGroup.querySelector(".dropdown-toggle");
    const newStateInput = btnGroup.querySelector(".newState");
    if (!dropdownButton || !newStateInput) {
      return;
    }

    dropdownButton.textContent = selectedText;
    newStateInput.value = selectedValue;
});