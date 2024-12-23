document.addEventListener("DOMContentLoaded", () => {
    const bookTableBody = document.querySelector("#orderTable tbody");
    const addForm = document.querySelector('#addorderForm');
    const deleteForm = document.querySelector('#deleteOrderForm');
    const updateForm = document.querySelector('#updateOrderForm');
    const fetchBookForm = document.getElementById('fetchOrderForm');
    const fetchBookId = document.getElementById('fetchOrderId');
    const searchBookButton = document.getElementById('searchOrderButton');
    const searchBookInput = document.getElementById('searchOrderInput');

    const updateNumberOfOrder = document.getElementById('updateNumberOfOrder');
    const updateDateOfOrder = document.getElementById('updateDateOfOrder');
    const updateSumToPay = document.getElementById('updateSumToPay');
    const updateIndividualId = document.getElementById('updateIndividualId');
    const updateLegalCustomerId = document.getElementById('updateLegalCustomerId');
    loadOrders();


    //Загрузка
    function loadOrders() {
        fetch("/api/orders")
            .then(response => response.json())
            .then(orders => {
                bookTableBody.innerHTML = "";
                orders.forEach(order => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${order.id}</td>
                        <td>${order.numberOfOrder}</td>
                        <td>${order.dateOfOrder}</td>
                        <td>${order.sumToPay}</td>
                        <td>${order.individualId}</td>
                        <td>${order.legalCustomerId}</td>
                    `;
                    bookTableBody.appendChild(row);
                });
            })
            .catch(error => console.error("Ошибка:", error));
    }


    // Добавление
    document.getElementById("addOrderForm").addEventListener("submit", function (event) {
        event.preventDefault();

        const individualId = document.getElementById("addIndividualId").value || null;
        const legalCustomerId = document.getElementById("addLegalCustomerId").value || null;

        const sumToPayInput = document.getElementById("addSumToPay").value;
        const sumToPay = sumToPayInput ? parseFloat(sumToPayInput) : null;

        if (isNaN(sumToPay) || sumToPay <= 0) {
            alert("Введите корректную сумму для оплаты.");
            return;
        }
        const newOrder = {
            numberOfOrder: document.getElementById("addNumberOfOrder").value,
            dateOfOrder: document.getElementById("addDateOfOrder").value,
            sumToPay: sumToPay,
            individualId: individualId ? parseInt(individualId) : null, // Передаём null, если пусто
            legalCustomerId: legalCustomerId ? parseInt(legalCustomerId) : null, // Передаём null, если пусто
        };
        console.log("Созданный объект заказа:", newOrder.individualId);
        console.log("Созданный объект заказа:", newOrder.legalCustomerId);
        console.log("Созданный объект заказа:", newOrder.sumToPay);
        console.log("Созданный объект заказа:", newOrder.dateOfOrder);



        fetch("/api/orders/addOrder", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newOrder),
        })
            .then(response => {
                if (response.ok) {
                    loadOrders();
                    closeModal('addOrderModal');
                } else {
                    alert("Ошибка при добавлении Заказа");
                }
            })
            .catch(error => console.error("Ошибка:", error));
    });
    // document.addEventListener("DOMContentLoaded", function () {
    //     const orderInput = document.getElementById("addNumberOfOrder");
    //
    //     // Устанавливаем значение по умолчанию
    //     const defaultPrefix = "ORD-";
    //     const defaultNumber = "0000";
    //     orderInput.value = `${defaultPrefix}${defaultNumber}`;
    //
    //     // Ограничиваем ввод: пользователь может изменить только числовую часть
    //     orderInput.addEventListener("input", function () {
    //         const currentValue = orderInput.value;
    //
    //         // Разделяем префикс и числовую часть
    //         if (!currentValue.startsWith(defaultPrefix)) {
    //             orderInput.value = `${defaultPrefix}${defaultNumber}`;
    //         } else {
    //             const numericPart = currentValue.slice(defaultPrefix.length).replace(/\D/g, "");
    //             orderInput.value = `${defaultPrefix}${numericPart}`;
    //         }
    //     });
    // });


    //Обновление
    async function updateOrder(order, id) {
        try {
            const response = await fetch(`http://localhost:8080/api/orders/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(order),
            });
            if (!response.ok) {
                throw new Error('Ошибка при обновлении заказа');
            }
            const updatedData = await response.json();
            alert('Заказ успешно изменена!');
        } catch (error) {
            alert(error.message);
        }
    }

    updateForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const orderID = fetchBookId.value;
        const updatedOrder = {
            numberOfOrder: updateNumberOfOrder.value,
            dateOfOrder: updateDateOfOrder.value,
            sumToPay: updateSumToPay.value,
            individualId: updateIndividualId.value,
            legalCustomerId: updateLegalCustomerId.value,
        };
        console.log("Updated Book:", updatedBook);
        await updateOrder(updatedOrder, orderID);
        loadOrders();
    });

// Обработчик для загрузки данных клиента
    fetchBookForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const orderId = fetchBookId.value;
        try {
            const response = await fetch(`http://localhost:8080/api/orders/${orderId}`);
            if (!response.ok) {
                throw new Error('Заказ не найдена');
            }
            const orderData = await response.json();
            // Заполнение полей формы данными клиента
            updateNumberOfOrder.value = orderData.numberOfOrder || '';
            updateDateOfOrder.value = orderData.dateOfOrder || '';
            updateSumToPay.value = orderData.sumToPay || '';
            updateIndividualId.value = orderData.individualId || '';
            updateLegalCustomerId.value = orderData.legalCustomerId || '';
            console.log("Updated Book:", orderData);
            // Показать второй шаг формы
            showStep2();

        } catch (error) {
            alert(error.message);
        }
    });


// Функция для переключения шагов формы
    function showStep2() {
        // Скрыть первую часть формы
        const step1 = document.getElementById('step1_orders');
        if (step1) {
            step1.style.display = 'none';
        }

        // Показать вторую часть формы
        const step2 = document.getElementById('step2_orders');
        if (step2) {
            step2.style.display = 'block';
        }
    }


    // удаление
    async function deleteOrder(id) {
        try {
            await axios.delete(`http://localhost:8080/api/orders/${id}`);
        } catch (error) {
            console.error('Error deleting order:', error);
        }
    }
    deleteForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const id = document.querySelector('#deleteOrderId').value;
        await deleteOrder(id);
        loadOrders();
        closeModal('deleteOrderModal');
    });

    // Поиск



    searchBookButton.addEventListener("click", function () {
        searchBookInput.style.display = searchBookInput.style.display === "none" ? "block" : "none";
    });

    searchBookInput.addEventListener("input", function () {
        const searchTerm = searchBookInput.value.toLowerCase();
        const rows = bookTableBody.querySelectorAll("tr");

        rows.forEach(row => {
            const cells = row.querySelectorAll("td");
            const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
            row.style.display = isVisible ? "" : "none";
        });

    });
});

