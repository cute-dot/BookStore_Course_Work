document.addEventListener("DOMContentLoaded", () => {
    const bookTableBody = document.querySelector("#bookTable tbody");
    const addForm = document.querySelector('#addBookForm');
    const deleteForm = document.querySelector('#deleteBookForm');
    const updateForm = document.querySelector('#updateBookForm');
    const fetchBookForm = document.getElementById('fetchBookForm');
    const fetchBookId = document.getElementById('fetchBookId');
    const searchBookButton = document.getElementById('searchBookButton');
    const searchBookInput = document.getElementById('searchBookInput');

    const updateBookTittle = document.getElementById('updateBookTittle');
    const updateYearOfPublication = document.getElementById('updateYearOfPublication');
    const updateNumberOfPages = document.getElementById('updateNumberOfPages');
    const updatePublishingHouseID = document.getElementById('updatePublishingHouseID');
    const updateSupplyID = document.getElementById('updateSupplyID');
    const updatePrice = document.getElementById('updatePrice');
    loadBooks();


    //Загрузка
    function loadBooks() {
        fetch("/api/books")
            .then(response => response.json())
            .then(books => {
                bookTableBody.innerHTML = "";
                books.forEach(book => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${book.id}</td>
                        <td>${book.bookTitle}</td>
                        <td>${book.yearOfPublication}</td>
                        <td>${book.numberOfPages}</td>
                        <td>${book.publishingHouse}</td>
                        <td>${book.supplyId}</td>
                        <td>${book.price}</td>
                    `;
                    bookTableBody.appendChild(row);
                });
            })
            .catch(error => console.error("Ошибка:", error));
    }


    // Добавление
    document.getElementById("addBookForm").addEventListener("submit", function (event) {
        event.preventDefault();
        const newBook = {
            bookTitle: document.getElementById("addBookTittle").value,
            yearOfPublication: document.getElementById("addYearOfPublication").value,
            numberOfPages: document.getElementById("addNumberOfPages").value,
            publishingHouseId: document.getElementById("addPublishingHouseID").value,
            supplyId: document.getElementById("addSupplyID").value,
            price: document.getElementById("addPrice").value,
        };
        console.log(newBook.bookTitle, newBook.numberOfPages, newBook.price);
        fetch("/api/books/addBook", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newBook),
        })
            .then(response => {
                if (response.ok) {
                    loadBooks();
                    closeModal('addBookModal');
                } else {
                    alert("Ошибка при добавлении книги");
                }
            })
            .catch(error => console.error("Ошибка:", error));
    });

    //Обновление
    async function updateBook(book, id) {
        try {
            const response = await fetch(`http://localhost:8080/api/books/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(book),
            });
            if (!response.ok) {
                throw new Error('Ошибка при обновлении книги');
            }
            const updatedData = await response.json();
            alert('Книга успешно изменена!');
        } catch (error) {
            alert(error.message);
        }
    }

    updateForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const bookID = fetchBookId.value;
        const updatedBook = {
            bookTitle: updateBookTittle.value,
            yearOfPublication: updateYearOfPublication.value,
            numberOfPages: updateNumberOfPages.value,
            publishingHouseId: updatePublishingHouseID.value,
            supplyId: updateSupplyID.value,
            price: updatePrice.value,
        };
        console.log("Updated Book:", updatedBook);
        await updateBook(updatedBook, bookID);
        loadBooks();
    });

// Обработчик для загрузки данных клиента
    fetchBookForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const bookId = fetchBookId.value;
        try {
            const response = await fetch(`http://localhost:8080/api/books/${bookId}`);
            if (!response.ok) {
                throw new Error('Книга не найдена');
            }
            const bookData = await response.json();
            // Заполнение полей формы данными клиента
            updateBookTittle.value = bookData.bookTitle || '';
            updateYearOfPublication.value = bookData.yearOfPublication || '';
            updateNumberOfPages.value = bookData.numberOfPages || '';
            updatePublishingHouseID.value = bookData.publishingHouseId || '';
            updateSupplyID.value = bookData.supplyId || '';
            updatePrice.value = bookData.price || '';
            console.log("Updated Book:", bookData);
            // Показать второй шаг формы
            showStep2();

        } catch (error) {
            alert(error.message);
        }
    });


// Функция для переключения шагов формы
    function showStep2() {
        // Скрыть первую часть формы
        const step1 = document.getElementById('step1_books');
        if (step1) {
            step1.style.display = 'none';
        }

        // Показать вторую часть формы
        const step2 = document.getElementById('step2_books');
        if (step2) {
            step2.style.display = 'block';
        }
    }


    // удаление
    async function deleteBook(id) {
        try {
            await axios.delete(`http://localhost:8080/api/books/${id}`);
        } catch (error) {
            console.error('Error deleting book:', error);
        }
    }
    deleteForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const id = document.querySelector('#deleteBookId').value;
        await deleteBook(id);
        loadBooks();
        closeModal('deleteModal');
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

