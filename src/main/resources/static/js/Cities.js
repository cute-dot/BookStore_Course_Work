    document.addEventListener("DOMContentLoaded", () => {
        const cityTableBody = document.querySelector("#cityTable tbody");
        const addCityForm = document.querySelector('#addCityForm');
        const deleteCityForm = document.querySelector('#deleteCityForm');
        const updateCityForm = document.querySelector('#updateCityForm');
        const fetchCityForm = document.getElementById('fetchCityForm');
        const fetchCityId = document.getElementById('fetchCityId');
        const searchCityButton = document.getElementById('searchCityButton');
        const searchCityInput = document.getElementById('searchCityInput');

        loadCities();

        // Загрузка данных
        function loadCities() {
            fetch("/api/cities")
                .then(response => response.json())
                .then(cities => {
                    cityTableBody.innerHTML = "";
                    cities.forEach(city => {
                        const row = document.createElement("tr");
                        row.innerHTML = `
                            <td>${city.id}</td>
                            <td>${city.cityName}</td>
                        `;
                        cityTableBody.appendChild(row);
                    });
                });
        }

        // Добавление нового города
        addCityForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const newCity = {
                cityName: document.getElementById('addCityName').value
            };

            fetch("/api/cities/addCity", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newCity)
            })
                .then(response => {
                    if (response.ok) {
                        alert("Город добавлен");
                        loadCities();
                        closeModal('addCityModal');
                    } else {
                        alert("Ошибка при добавлении");
                    }
                });
        });

        // Загрузка данных для обновления
        fetchCityForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const cityId = fetchCityId.value;

            fetch(`/api/cities/${cityId}`)
                .then(response => response.json())
                .then(city => {
                    if (city) {
                        document.getElementById('step1_city').style.display = 'none';
                        document.getElementById('step2_city').style.display = 'block';

                        document.getElementById('updateCityName').value = city.cityName;
                    } else {
                        alert("Город не найден");
                    }
                });
        });

        // Обновление данных города
        updateCityForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const cityId = fetchCityId.value;

            const updatedCity = {
                cityName: document.getElementById('updateCityName').value
            };

            fetch(`/api/cities/${cityId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedCity)
            })
                .then(response => {
                    if (response.ok) {
                        alert("Город обновлен");
                        loadCities();
                        closeModal('updateCityModal');
                    } else {
                        alert("Ошибка при обновлении");
                    }
                });
        });

        // Удаление города
        deleteCityForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const cityId = document.getElementById('deleteCityId').value;

            fetch(`/api/cities/${cityId}`, {
                method: 'DELETE',
            })
                .then(response => {
                    if (response.ok) {
                        alert("Город удален");
                        loadCities();
                        closeModal('deleteCityModal');
                    } else {
                        alert("Ошибка при удалении");
                    }
                });
        });

        searchCityButton.addEventListener("click", function () {
            searchCityInput.style.display = searchCityInput.style.display === "none" ? "block" : "none";
        });

        searchCityInput.addEventListener("input", function () {
            const searchTerm = searchCityInput.value.toLowerCase();
            const rows = cityTableBody.querySelectorAll("tr");

            rows.forEach(row => {
                const cells = row.querySelectorAll("td");
                const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
                row.style.display = isVisible ? "" : "none";
            });
        });
    });
