document.addEventListener("DOMContentLoaded", () => {
    const legalTableBody = document.querySelector("#legalTable tbody");
    const addLegalForm = document.querySelector('#addLegalForm');
    const deleteLegalForm = document.querySelector('#deleteLegalForm');
    const updateLegalForm = document.querySelector('#updateLegalForm');
    const fetchLegalForm = document.getElementById('fetchLegalForm');
    const fetchLegalId = document.getElementById('fetchLegalId');
    const searchLegalButton = document.getElementById('searchLegalButton');
    const searchLegalInput = document.getElementById('searchLegalInput');

    const updateCompanyName = document.getElementById('updateCompanyName');
    const updateFirstname = document.getElementById('updateFirstname');
    const updateLastname = document.getElementById('updateLastname');
    const updateSurname = document.getElementById('updateSurname');
    const updateNumberOfHouse = document.getElementById('updateNumberOfHouse');
    const updateBankAccount = document.getElementById('updateBankAccount');
    const updateDocumentInn = document.getElementById('updateDocumentInn');
    const updateBankId = document.getElementById('updateBankId');
    const updateBankName = document.getElementById('updateBankName');
    const updateStreetId = document.getElementById('updateStreetId');
    const updateCityId = document.getElementById('updateCityId');

    loadLegals();

    // Загрузка данных
    function loadLegals() {
        fetch("/api/legalCustomers")
            .then(response => response.json())
            .then(legals => {
                legalTableBody.innerHTML = "";
                legals.forEach(legal => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${legal.id}</td>
                        <td>${legal.companyName}</td>
                        <td>${legal.firstname}</td>
                        <td>${legal.lastname}</td>
                        <td>${legal.surname}</td>
                        <td>${legal.numberOfHouse}</td>
                        <td>${legal.bankAccount}</td>
                        <td>${legal.documentInn}</td>
                        <td>${legal.bankName}</td>
                        <td>${legal.streetName}</td>
                        <td>${legal.cityName}</td>                        
                    `;
                    legalTableBody.appendChild(row);
                });
            });
    }

    // // Открытие модального окна
    // function openModal(modalId) {
    //     document.getElementById(modalId).style.display = "block";
    //     document.getElementById("overlay").style.display = "block";
    // }

    // // Закрытие модального окна
    // function closeModal(modalId) {
    //     document.getElementById(modalId).style.display = "none";
    //     document.getElementById("overlay").style.display = "none";
    // }

    // Добавление нового юридического лица
    addLegalForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const newLegal = {
            companyName: document.getElementById('addCompanyName').value,
            firstname: document.getElementById('addFirstname').value,
            lastname: document.getElementById('addLastname').value,
            surname: document.getElementById('addSurname').value,
            numberOfHouse: document.getElementById('addNumberOfHouse').value,
            bankAccount: document.getElementById('addBankAccount').value,
            documentInn: document.getElementById('addDocumentInn').value,
            bankId: document.getElementById('addBankId').value,
            streetId: document.getElementById('addStreetId').value,
            cityId: document.getElementById('addCityId').value
        };

        fetch("/api/legalCustomers/addLegal", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newLegal)
        })
            .then(response => {
                if (response.ok) {
                    alert("Юридическое лицо добавлено");
                    loadLegals();
                    closeModal('addLegalModal');
                } else {
                    alert("Ошибка при добавлении");
                }
            });
    });

    // Обновление юридического лица
    fetchLegalForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const legalId = fetchLegalId.value;

        fetch(`/api/legalCustomers/${legalId}`)
            .then(response => response.json())
            .then(legal => {
                if (legal) {
                    document.getElementById('step1_legal').style.display = 'none';
                    document.getElementById('step2_legal').style.display = 'block';

                    updateCompanyName.value = legal.companyName;
                    updateFirstname.value = legal.firstname;
                    updateLastname.value = legal.lastname;
                    updateSurname.value = legal.surname;
                    updateNumberOfHouse.value = legal.numberOfHouse;
                    updateBankAccount.value = legal.bankAccount;
                    updateDocumentInn.value = legal.documentInn;
                    updateBankId.value = legal.bankId;
                    updateStreetId.value = legal.streetId;
                    updateCityId.value = legal.cityId;
                } else {
                    alert("Юридическое лицо не найдено");
                }
            });
    });

    // Обновление данных юридического лица
    updateLegalForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const legalId = fetchLegalId.value;

        const updatedLegal = {
            companyName: updateCompanyName.value,
            firstname: updateFirstname.value,
            lastname: updateLastname.value,
            surname: updateSurname.value,
            numberOfHouse: updateNumberOfHouse.value,
            bankAccount: updateBankAccount.value,
            documentInn: updateDocumentInn.value,
            bankId: updateBankId.value,
            streetId: updateStreetId.value,
            cityId: updateCityId.value
        };

        fetch(`/api/legalCustomers/${legalId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedLegal)
        })
            .then(response => {
                if (response.ok) {
                    alert("Данные обновлены");
                    loadLegals();
                    closeModal('updateLegalModal');
                } else {
                    alert("Ошибка при обновлении");
                }
            });
    });

    // Удаление юридического лица
    deleteLegalForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const legalId = document.getElementById('deleteLegalId').value;

        fetch(`/api/legalCustomers/${legalId}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert("Юридическое лицо удалено");
                    loadLegals();
                    closeModal('deleteLegalModal');
                } else {
                    alert("Ошибка при удалении");
                }
            });
    });

    searchLegalButton.addEventListener("click", function () {
        searchLegalInput.style.display = searchLegalInput.style.display === "none" ? "block" : "none";
    });

    searchLegalInput.addEventListener("input", function () {
        const searchTerm = searchLegalInput.value.toLowerCase();
        const rows = legalTableBody.querySelectorAll("tr");

        rows.forEach(row => {
            const cells = row.querySelectorAll("td");
            const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
            row.style.display = isVisible ? "" : "none";
        });
    });
});
