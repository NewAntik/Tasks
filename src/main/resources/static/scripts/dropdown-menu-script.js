document.addEventListener("DOMContentLoaded", function () {
    // Get all dropdown toggle buttons
    var dropdownToggle = document.querySelectorAll(".dropdown-toggle");

    // Iterate over each toggle button
    dropdownToggle.forEach(function (toggle) {
      toggle.addEventListener("click", function () {
        // Find the associated dropdown menu
        var dropdownMenu = toggle.nextElementSibling;

        // Toggle the "show" class on the dropdown menu
        dropdownMenu.classList.toggle("show");
      });
    });
  });