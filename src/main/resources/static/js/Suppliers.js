document.addEventListener("DOMContentLoaded", () => {
    const supplierTableBody = document.querySelector("#supplierTable tbody");
    const addSupplierForm = document.querySelector('#addSupplierForm');
    const deleteSupplierForm = document.querySelector('#deleteSupplierForm');
    const updateSupplierForm = document.querySelector('#updateSupplierForm');
    const fetchSupplierForm = document.getElementById('fetchSupplierForm');
    const fetchSupplierId = document.getElementById('fetchSupplierId');
    const searchSupplierButton = document.getElementById('searchSupplierButton');
    const searchSupplierInput = document.getElementById('searchSupplierInput');

    loadSuppliers();

    // Загрузка данных
    function loadSuppliers() {
        fetch("/api/suppliers")
            .then(response => response.json())
            .then(suppliers => {
                supplierTableBody.innerHTML = "";
                suppliers.forEach(supplier => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${supplier.id}</td>
                        <td>${supplier.fullName}</td>
                        <td>${supplier.documentInn}</td>
                        <td>${supplier.numberOfHouse}</td>
                        <td>${supplier.bankAccount}</td>
                        <td>${supplier.bankTittle}</td>
                        <td>${supplier.streetName}</td>
                        <td>${supplier.cityName}</td>                        
                    `;
                    supplierTableBody.appendChild(row);
                });
            });
    }

    // Добавление нового поставщика
    addSupplierForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const newSupplier = {
            firstname: document.getElementById('addFirstName').value,
            lastname: document.getElementById('addLastName').value,
            surname: document.getElementById('addSurname').value,
            documentInn: document.getElementById('addDocumentInn').value,
            numberOfHouse: document.getElementById('addNumberOfHouse').value,
            bankAccount: document.getElementById('addBankAccount').value,
            bankId: document.getElementById('addBankId').value,
            streetId: document.getElementById('addStreetId').value,
            cityId: document.getElementById('addCityId').value,
        };

        fetch("/api/suppliers/addSupplier", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newSupplier)
        })
            .then(response => {
                if (response.ok) {
                    alert("Поставщик добавлен");
                    loadSuppliers();
                    closeModal('addSupplierModal');
                } else {
                    alert("Ошибка при добавлении");
                }
            });
    });

    // Загрузка данных для обновления
    fetchSupplierForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const supplierId = fetchSupplierId.value;

        fetch(`/api/suppliers/${supplierId}`)
            .then(response => response.json())
            .then(supplier => {
                if (supplier) {
                    document.getElementById('step1_supplier').style.display = 'none';
                    document.getElementById('step2_supplier').style.display = 'block';

                    document.getElementById('updateFirstName').value = supplier.firstname;
                    document.getElementById('updateLastName').value = supplier.lastname;
                    document.getElementById('updateSurname').value = supplier.surname;
                    document.getElementById('updateDocumentInn').value = supplier.documentInn;
                    document.getElementById('updateNumberOfHouse').value = supplier.numberOfHouse;
                    document.getElementById('updateBankAccount').value = supplier.bankAccount;
                    document.getElementById('updateBankId').value = supplier.bankId;
                    document.getElementById('updateStreetId').value = supplier.streetId;
                    document.getElementById('updateCityId').value = supplier.cityId;
                } else {
                    alert("Поставщик не найден");
                }
            });
    });

    // Обновление данных поставщика
    updateSupplierForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const supplierId = fetchSupplierId.value;

        const updatedSupplier = {
            firstname: document.getElementById('updateFirstName').value,
            lastname: document.getElementById('updateLastName').value,
            surname: document.getElementById('updateSurname').value,
            documentInn: document.getElementById('updateDocumentInn').value,
            numberOfHouse: document.getElementById('updateNumberOfHouse').value,
            bankAccount: document.getElementById('updateBankAccount').value,
            bankId: document.getElementById('updateBankId').value,
            streetId: document.getElementById('updateStreetId').value,
            cityId: document.getElementById('updateCityId').value,
        };

        fetch(`/api/suppliers/${supplierId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedSupplier)
        })
            .then(response => {
                if (response.ok) {
                    alert("Поставщик обновлен");
                    loadSuppliers();
                    closeModal('updateSupplierModal');
                } else {
                    alert("Ошибка при обновлении");
                }
            });
    });

    // Удаление поставщика
    deleteSupplierForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const supplierId = document.getElementById('deleteSupplierId').value;

        fetch(`/api/suppliers/${supplierId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert("Поставщик удален");
                    loadSuppliers();
                    closeModal('deleteSupplierModal');
                } else {
                    alert("Ошибка при удалении");
                }
            });
    });

    searchSupplierButton.addEventListener("click", function () {
        searchSupplierInput.style.display = searchSupplierInput.style.display === "none" ? "block" : "none";
    });

    searchSupplierInput.addEventListener("input", function () {
        const searchTerm = searchSupplierInput.value.toLowerCase();
        const rows = supplierTableBody.querySelectorAll("tr");

        rows.forEach(row => {
            const cells = row.querySelectorAll("td");
            const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
            row.style.display = isVisible ? "" : "none";
        });
    });
});
