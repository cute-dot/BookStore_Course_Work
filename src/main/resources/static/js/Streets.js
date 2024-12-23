document.addEventListener("DOMContentLoaded", () => {
    const streetTableBody = document.querySelector("#streetTable tbody");
    const addStreetForm = document.querySelector('#addStreetForm');
    const deleteStreetForm = document.querySelector('#deleteStreetForm');
    const updateStreetForm = document.querySelector('#updateStreetForm');
    const fetchStreetForm = document.getElementById('fetchStreetForm');
    const fetchStreetId = document.getElementById('fetchStreetId');
    const searchStreetButton = document.getElementById('searchStreetButton');
    const searchStreetInput = document.getElementById('searchStreetInput');

    loadStreets();

    // Загрузка данных
    function loadStreets() {
        fetch("/api/streets")
            .then(response => response.json())
            .then(streets => {
                streetTableBody.innerHTML = "";
                streets.forEach(street => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${street.id}</td>
                        <td>${street.streetName}</td>
                    `;
                    streetTableBody.appendChild(row);
                });
            });
    }

    // Добавление новой улицы
    addStreetForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const newStreet = {
            streetName: document.getElementById('addStreetName').value
        };

        fetch("/api/streets/addStreet", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newStreet)
        })
            .then(response => {
                if (response.ok) {
                    alert("Улица добавлена");
                    loadStreets();
                    closeModal('addStreetModal');
                } else {
                    alert("Ошибка при добавлении");
                }
            });
    });

    // Загрузка данных для обновления
    fetchStreetForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const streetId = fetchStreetId.value;

        fetch(`/api/streets/${streetId}`)
            .then(response => response.json())
            .then(street => {
                if (street) {
                    document.getElementById('step1_street').style.display = 'none';
                    document.getElementById('step2_street').style.display = 'block';

                    document.getElementById('updateStreetName').value = street.streetName;
                } else {
                    alert("Улица не найдена");
                }
            });
    });

    // Обновление данных улицы
    updateStreetForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const streetId = fetchStreetId.value;

        const updatedStreet = {
            streetName: document.getElementById('updateStreetName').value
        };

        fetch(`/api/streets/${streetId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedStreet)
        })
            .then(response => {
                if (response.ok) {
                    alert("Улица обновлена");
                    loadStreets();
                    closeModal('updateStreetModal');
                } else {
                    alert("Ошибка при обновлении");
                }
            });
    });

    // Удаление улицы
    deleteStreetForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const streetId = document.getElementById('deleteStreetId').value;

        fetch(`/api/streets/${streetId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert("Улица удалена");
                    loadStreets();
                    closeModal('deleteStreetModal');
                } else {
                    alert("Ошибка при удалении");
                }
            });
    });

    searchStreetButton.addEventListener("click", function () {
        searchStreetInput.style.display = searchStreetInput.style.display === "none" ? "block" : "none";
    });

    searchStreetInput.addEventListener("input", function () {
        const searchTerm = searchStreetInput.value.toLowerCase();
        const rows = streetTableBody.querySelectorAll("tr");

        rows.forEach(row => {
            const cells = row.querySelectorAll("td");
            const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
            row.style.display = isVisible ? "" : "none";
        });
    });
});
