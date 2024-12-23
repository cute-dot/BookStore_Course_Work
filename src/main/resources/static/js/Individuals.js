document.addEventListener("DOMContentLoaded", () => {
    const individualTableBody = document.querySelector("#individualTable tbody");
    const addIndividualForm = document.querySelector('#addIndividualForm');
    const deleteIndividualForm = document.querySelector('#deleteIndividualForm');
    const updateIndividualForm = document.querySelector('#updateIndividualForm');
    const fetchIndividualForm = document.getElementById('fetchIndividualForm');
    const fetchIndividualId = document.getElementById('fetchIndividualId');
    const searchIndividualButton = document.getElementById('searchIndividualButton');
    const searchIndividualInput = document.getElementById('searchIndividualInput');

    const updateFirstname = document.getElementById('updateFirstname');
    const updateLastname = document.getElementById('updateLastname');
    const updateSurname = document.getElementById('updateSurname');
    const updatePhoneNumber = document.getElementById('updatePhoneNumber');
    const updatePassportSeries = document.getElementById('updatePassportSeries');
    const updatePassportNumber = document.getElementById('updatePassportNumber');
    const updatePassportIssueDate = document.getElementById('updatePassportIssueDate');
    const updateWhoIssuedThePassport = document.getElementById('updateWhoIssuedThePassport');
    const updateCityId = document.getElementById('updateCityId');
    const updateStreetId = document.getElementById('updateStreetId');
    const updateBankAccount = document.getElementById('updateBankAccount');
    const updateNumberOfHouse = document.getElementById('updateNumberOfHouse');

    loadIndividuals();

    // Загрузка данных
    function loadIndividuals() {
        fetch("/api/individuals")
            .then(response => response.json())
            .then(individuals => {
                individualTableBody.innerHTML = "";
                individuals.forEach(individual => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${individual.id}</td>
                        <td>${individual.firstname}</td>
                        <td>${individual.lastname}</td>
                        <td>${individual.surname}</td>
                        <td>${individual.phoneNumber}</td>
                        <td>${individual.passportSeries}</td>
                        <td>${individual.passportNumber}</td>
                        <td>${individual.passportIssueDate}</td>
                        <td>${individual.whoIssuedThePassport}</td>
                        <td>${individual.cityName}</td>
                        <td>${individual.streetName}</td>
                        <td>${individual.bankAccount}</td>
                        <td>${individual.numberOfHouse}</td>
                    `;
                    individualTableBody.appendChild(row);
                });
            })
            .catch(error => console.error("Ошибка:", error));
    }

    // Добавление
    addIndividualForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const newIndividual = {
            firstname: document.getElementById("addFirstname").value,
            lastname: document.getElementById("addLastname").value,
            surname: document.getElementById("addSurname").value,
            phoneNumber: document.getElementById("addPhoneNumber").value,
            passportSeries: document.getElementById("addPassportSeries").value,
            passportNumber: document.getElementById("addPassportNumber").value,
            passportIssueDate: document.getElementById("addPassportIssueDate").value,
            whoIssuedThePassport: document.getElementById("addWhoIssuedThePassport").value,
            cityId: document.getElementById("addCityId").value,
            streetId: document.getElementById("addStreetId").value,
            bankAccount: document.getElementById("addBankAccount").value,
            numberOfHouse: document.getElementById("addNumberOfHouse").value
        };

        fetch("/api/individuals/addIndividual", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newIndividual),
        })
            .then(response => {
                if (response.ok) {
                    loadIndividuals();
                    closeModal('addIndividualModal');
                } else {
                    alert("Ошибка при добавлении Физ. Лица");
                }
            })
            .catch(error => console.error("Ошибка:", error));
    });

    // Обновление
    async function updateIndividual(individual, id) {
        try {
            const response = await fetch(`http://localhost:8080/api/individuals/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(individual),
            });
            if (!response.ok) {
                throw new Error('Ошибка при обновлении Физ. Лица');
            }
            const updatedData = await response.json();
            alert('Физ. Лицо успешно изменено!');
        } catch (error) {
            alert(error.message);
        }
    }

    updateIndividualForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const individualID = fetchIndividualId.value;
        const updatedIndividual = {
            firstname: updateFirstname.value,
            lastname: updateLastname.value,
            surname: updateSurname.value,
            phoneNumber: updatePhoneNumber.value,
            passportSeries: updatePassportSeries.value,
            passportNumber: updatePassportNumber.value,
            passportIssueDate: updatePassportIssueDate.value,
            whoIssuedThePassport: updateWhoIssuedThePassport.value,
            cityId: updateCityId.value,
            streetId: updateStreetId.value,
            bankAccount: updateBankAccount.value,
            numberOfHouse: updateNumberOfHouse.value,
        };
        await updateIndividual(updatedIndividual, individualID);
        loadIndividuals();
    });

    // Обработчик для загрузки данных Физ. Лица
    fetchIndividualForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const individualId = fetchIndividualId.value;
        try {
            const response = await fetch(`http://localhost:8080/api/individuals/${individualId}`);
            if (!response.ok) {
                throw new Error('Физ. Лицо не найдено');
            }
            const individualData = await response.json();
            // Заполнение полей формы данными Физ. Лица
            updateFirstname.value = individualData.firstname || '';
            updateLastname.value = individualData.lastname || '';
            updateSurname.value = individualData.surname || '';
            updatePhoneNumber.value = individualData.phoneNumber || '';
            updatePassportSeries.value = individualData.passportSeries || '';
            updatePassportNumber.value = individualData.passportNumber || '';
            updatePassportIssueDate.value = individualData.passportIssueDate || '';
            updateWhoIssuedThePassport.value = individualData.whoIssuedThePassport || '';
            updateCityId.value = individualData.cityId || '';
            updateStreetId.value = individualData.streetId || '';
            updateBankAccount.value = individualData.bankAccount || '';
            updateNumberOfHouse.value = individualData.numberOfHouse || '';
            showStep2();
        } catch (error) {
            alert(error.message);
        }
    });

    // Функция для переключения шагов формы
    function showStep2() {
        const step1 = document.getElementById('step1_individuals');
        if (step1) {
            step1.style.display = 'none';
        }

        const step2 = document.getElementById('step2_individuals');
        if (step2) {
            step2.style.display = 'block';
        }
    }

    // Удаление
    async function deleteIndividual(id) {
        try {
            await axios.delete(`http://localhost:8080/api/individuals/${id}`);
        } catch (error) {
            console.error('Error deleting individual:', error);
        }
    }

    deleteIndividualForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const id = document.querySelector('#deleteIndividualId').value;
        await deleteIndividual(id);
        loadIndividuals();
        closeModal('deleteIndividualModal');
    });

    // Поиск
    searchIndividualButton.addEventListener("click", function () {
        searchIndividualInput.style.display = searchIndividualInput.style.display === "none" ? "block" : "none";
    });

    searchIndividualInput.addEventListener("input", function () {
        const searchTerm = searchIndividualInput.value.toLowerCase();
        const rows = individualTableBody.querySelectorAll("tr");

        rows.forEach(row => {
            const cells = row.querySelectorAll("td");
            const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
            row.style.display = isVisible ? "" : "none";
        });
    });
});
