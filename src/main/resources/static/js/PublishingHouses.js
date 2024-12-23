document.addEventListener("DOMContentLoaded", () => {
    const publishingHouseTableBody = document.querySelector("#publishingHouseTable tbody");
    const addPublishingHouseForm = document.querySelector('#addPublishingHouseForm');
    const deletePublishingHouseForm = document.querySelector('#deletePublishingHouseForm');
    const updatePublishingHouseForm = document.querySelector('#updatePublishingHouseForm');
    const fetchPublishingHouseForm = document.getElementById('fetchPublishingHouseForm');
    const fetchPublishingHouseId = document.getElementById('fetchPublishingHouseId');
    const searchPublishingHouseButton = document.getElementById('searchPublishingHouseButton');
    const searchPublishingHouseInput = document.getElementById('searchPublishingHouseInput');

    loadPublishingHouses();

    function loadPublishingHouses() {
        fetch("/api/publishingHouses")
            .then(response => response.json())
            .then(publishingHouses => {
                publishingHouseTableBody.innerHTML = "";
                publishingHouses.forEach(house => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${house.id}</td>
                        <td>${house.nameOfPublishingHouse}</td>
                    `;
                    publishingHouseTableBody.appendChild(row);
                });
            });
    }

    addPublishingHouseForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const newPublishingHouse = {
            nameOfPublishingHouse: document.getElementById('addPublishingHouseName').value,
        };

        fetch("api/publishingHouses/addPublishingHouse", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newPublishingHouse)
        })
            .then(response => {
                if (response.ok) {
                    alert("Издательство добавлено");
                    loadPublishingHouses();
                    closeModal('addPublishingHouseModal');
                } else {
                    alert("Ошибка при добавлении");
                }
            });
    });

    fetchPublishingHouseForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const publishingHouseId = fetchPublishingHouseId.value;

        fetch(`/api/publishingHouses/${publishingHouseId}`)
            .then(response => response.json())
            .then(house => {
                if (house) {
                    document.getElementById('step1_publishingHouse').style.display = 'none';
                    document.getElementById('step2_publishingHouse').style.display = 'block';

                    document.getElementById('updatePublishingHouseName').value = house.nameOfPublishingHouse;
                } else {
                    alert("Издательство не найдено");
                }
            });
    });

    updatePublishingHouseForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const publishingHouseId = fetchPublishingHouseId.value;

        const updatedPublishingHouse = {
            nameOfPublishingHouse: document.getElementById('updatePublishingHouseName').value,
        };

        fetch(`/api/publishingHouses/${publishingHouseId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedPublishingHouse)
        })
            .then(response => {
                if (response.ok) {
                    alert("Издательство обновлено");
                    loadPublishingHouses();
                    closeModal('updatePublishingHouseModal');
                } else {
                    alert("Ошибка при обновлении");
                }
            });
    });

    deletePublishingHouseForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const publishingHouseId = document.getElementById('deletePublishingHouseId').value;

        fetch(`/api/publishingHouses/${publishingHouseId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert("Издательство удалено");
                    loadPublishingHouses();
                    closeModal('deletePublishingHouseModal');
                } else {
                    alert("Ошибка при удалении");
                }
            });
    });

    searchPublishingHouseButton.addEventListener("click", function () {
        searchPublishingHouseInput.style.display = searchPublishingHouseInput.style.display === "none" ? "block" : "none";
    });

    searchPublishingHouseInput.addEventListener("input", function () {
        const searchTerm = searchPublishingHouseInput.value.toLowerCase();
        const rows = publishingHouseTableBody.querySelectorAll("tr");

        rows.forEach(row => {
            const cells = row.querySelectorAll("td");
            const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
            row.style.display = isVisible ? "" : "none";
        });
    });
});
