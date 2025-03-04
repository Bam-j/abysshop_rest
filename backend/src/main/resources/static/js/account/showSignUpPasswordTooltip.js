document.addEventListener("DOMContentLoaded", function () {
  let tooltipTriggerList = [].slice.call(
      document.querySelectorAll('[data-bs-toggle="tooltip"]'));
  let tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl);
  });


  document.querySelectorAll("input").forEach(input => {
    input.addEventListener("focus", function () {
      let tooltip = bootstrap.Tooltip.getInstance(this);
      tooltip.show();

      //setTimeout(() => tooltip.hide(), 3000);
    });
  });
});