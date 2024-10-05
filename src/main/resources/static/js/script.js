document.addEventListener("DOMContentLoaded", function () {
    // Управление вкладками
    const tabButtons = document.querySelectorAll(".tab-button");
    const tabContents = document.querySelectorAll(".tab-content");
    const indicators = document.querySelectorAll(".indicator");

    tabButtons.forEach((button) => {
        button.addEventListener("click", function () {
            // Удаляем класс 'active' у всех кнопок и контента
            tabButtons.forEach((btn) => btn.classList.remove("active"));
            tabContents.forEach((content) => content.classList.remove("active"));

            // Добавляем класс 'active' к текущей кнопке и соответствующему контенту
            const target = this.getAttribute("data-target");
            this.classList.add("active");
            document.getElementById(target).classList.add("active");
        });
    });

    document.querySelectorAll('.indicator').forEach(indicator => {
        indicator.addEventListener('click', function() {
            const contentId = this.getAttribute('data-toggle');
            const details = document.querySelector(`#${contentId} .details`);

            // Тoggle visibility of details
            if (details.classList.contains('hidden')) {
                details.classList.remove('hidden');
                details.classList.add('visible');
                this.classList.add('active'); // Поворот стрелки
            } else {
                details.classList.remove('visible');
                details.classList.add('hidden');
                this.classList.remove('active'); // Возврат стрелки в исходное положение
            }
        });
    });


    // Валидация форм (универсальная для всех форм)
    const forms = document.querySelectorAll('.form');
    forms.forEach(form => {
        form.addEventListener('submit', function(event) {
            const inputs = form.querySelectorAll('input[type="text"], input[type="email"], input[type="password"]');
            let valid = true;

            inputs.forEach(input => {
                if (input.value.trim() === '') {
                    input.classList.add('error');
                    valid = false;
                } else {
                    input.classList.remove('error');
                }
            });

            if (!valid) {
                event.preventDefault();
                alert('Please fill in all required fields.');
            }
        });
    });
});
