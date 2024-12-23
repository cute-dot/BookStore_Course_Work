document.addEventListener("DOMContentLoaded", () => {
    const supplyTableBody = document.querySelector("#supplyTable tbody");
    const addSupplyForm = document.querySelector('#addSupplyForm');
    const deleteSupplyForm = document.querySelector('#deleteSupplyForm');
    const updateSupplyForm = document.querySelector('#updateSupplyForm');
    const fetchSupplyForm = document.getElementById('fetchSupplyForm');
    const fetchSupplyId = document.getElementById('fetchSupplyId');
    const searchSupplyButton = document.getElementById('searchSupplyButton');
    const searchSupplyInput = document.getElementById('searchSupplyInput');

    const updateDateOfSupply = document.getElementById('updateDateOfSupply');
    const updatePurchasePrice = document.getElementById('updatePurchasePrice');
    const updateSupplierId = document.getElementById('updateSupplierId');
    const updateFullSupplierName = document.getElementById('updateFullSupplierName');

    loadSupplies();

    // Загрузка данных
    function loadSupplies() {
        fetch("/api/supplies")
            .then(response => response.json())
            .then(supplies => {
                supplyTableBody.innerHTML = "";
                supplies.forEach(supply => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${supply.id}</td>
                        <td>${supply.dateOfSupply}</td>
                        <td>${supply.purchasePrice}</td>
                        <td>${supply.fullSupplierName}</td>
                        <td>${supply.supplierId}</td>                        
                    `;
                    supplyTableBody.appendChild(row);
                });
            });
    }


    // Добавление новой поставки
    addSupplyForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const newSupply = {
            dateOfSupply: document.getElementById('addDateOfSupply').value,
            purchasePrice: document.getElementById('addPurchasePrice').value,
            supplierId: document.getElementById('addSupplierId').value,
        };

        fetch("/api/supplies/addSupply", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newSupply)
        })
            .then(response => {
                if (response.ok) {
                    alert("Поставка добавлена");
                    loadSupplies();
                    closeModal('addSupplyModal');
                } else {
                    alert("Ошибка при добавлении");
                }
            });
    });

    // Загрузка данных для обновления
    fetchSupplyForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const supplyId = fetchSupplyId.value;

        fetch(`/api/supplies/${supplyId}`)
            .then(response => response.json())
            .then(supply => {
                if (supply) {
                    document.getElementById('step1_supply').style.display = 'none';
                    document.getElementById('step2_supply').style.display = 'block';

                    updateDateOfSupply.value = supply.dateOfSupply;
                    updatePurchasePrice.value = supply.purchasePrice;
                    updateSupplierId.value = supply.supplierId;
                } else {
                    alert("Поставка не найдена");
                }
            });
    });

    // Обновление данных поставки
    updateSupplyForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const supplyId = fetchSupplyId.value;

        const updatedSupply = {
            dateOfSupply: updateDateOfSupply.value,
            purchasePrice: updatePurchasePrice.value,
            supplierId: updateSupplierId.value,
        };

        fetch(`/api/supplies/${supplyId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedSupply)
        })
            .then(response => {
                if (response.ok) {
                    alert("Поставка обновлена");
                    loadSupplies();
                    closeModal('updateSupplyModal');
                } else {
                    alert("Ошибка при обновлении");
                }
            });
    });

    // Удаление поставки
    deleteSupplyForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const supplyId = document.getElementById('deleteSupplyId').value;

        fetch(`/api/supplies/${supplyId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert("Поставка удалена");
                    loadSupplies();
                    closeModal('deleteSupplyModal');
                } else {
                    alert("Ошибка при удалении");
                }
            });
    });

    searchSupplyButton.addEventListener("click", function () {
        searchSupplyInput.style.display = searchSupplyInput.style.display === "none" ? "block" : "none";
    });

    searchSupplyInput.addEventListener("input", function () {
        const searchTerm = searchSupplyInput.value.toLowerCase();
        const rows = supplyTableBody.querySelectorAll("tr");

        rows.forEach(row => {
            const cells = row.querySelectorAll("td");
            const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
            row.style.display = isVisible ? "" : "none";
        });
    });
});
