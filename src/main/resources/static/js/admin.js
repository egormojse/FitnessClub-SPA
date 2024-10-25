document.addEventListener('DOMContentLoaded', () => {
    const sidebar = document.querySelector('.sidebar');
    const toggleButton = document.querySelector('.toggle-sidebar');

    toggleButton.addEventListener('click', () => {
        sidebar.classList.toggle('hidden');
    });
});

function toggleActions(element) {
    const actions = element.nextElementSibling;
    if (actions.style.display === "none" || actions.style.display === "") {
        actions.style.display = "block";
    } else {
        actions.style.display = "none";
    }
}

