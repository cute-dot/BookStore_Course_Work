function showSection(sectionId) {
    // Скрыть все секции
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.style.display = 'none';
    });

    // Показать выбранную секцию
    const selectedSection = document.getElementById(sectionId);
    if (selectedSection) {
        selectedSection.style.display = 'block';
    }
}


function openModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.add("active"); // Добавляем класс
    }
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.remove("active");
    }
}
// Пример инициализации (если нужно)
document.addEventListener('DOMContentLoaded', function() {
    showSection()
});


document.addEventListener("DOMContentLoaded", function () {
    // Выбираем все таблицы с классом 'table-bd'
    const tables = document.querySelectorAll(".table-bd");

    // Для каждой таблицы
    tables.forEach((table) => {
        // Находим все сортируемые заголовки этой таблицы
        table.querySelectorAll("th.sortable").forEach((header) => {
            // Добавляем обработчик клика на каждый заголовок
            header.addEventListener("click", function () {
                const columnIndex = parseInt(header.dataset.column); // Номер столбца
                const order = header.dataset.order === "asc" ? "desc" : "asc"; // Определяем порядок сортировки
                header.dataset.order = order; // Сохраняем текущий порядок

                // Получаем строки текущей таблицы
                const rows = Array.from(table.querySelector("tbody").rows);

                // Сортируем строки
                rows.sort((rowA, rowB) => {
                    const cellA = rowA.cells[columnIndex].textContent.trim();
                    const cellB = rowB.cells[columnIndex].textContent.trim();

                    // Проверяем, числовые данные или текстовые
                    const isNumeric = !isNaN(cellA) && !isNaN(cellB);

                    if (isNumeric) {
                        return order === "asc"
                            ? parseFloat(cellA) - parseFloat(cellB)
                            : parseFloat(cellB) - parseFloat(cellA);
                    } else {
                        return order === "asc"
                            ? cellA.localeCompare(cellB, "ru")
                            : cellB.localeCompare(cellA, "ru");
                    }
                });

                // Перемещаем отсортированные строки обратно в таблицу
                const tbody = table.querySelector("tbody");
                tbody.innerHTML = "";
                rows.forEach((row) => tbody.appendChild(row));
            });
        });
    });
});
