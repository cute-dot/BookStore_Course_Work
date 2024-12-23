

document.addEventListener("DOMContentLoaded", () => {
    const authorTableBody = document.querySelector("#authorTable tbody");
    const addAuthorForm = document.querySelector('#addAuthorForm');
    const deleteAuthorForm = document.querySelector('#deleteAuthorForm');
    const updateAuthorForm = document.querySelector('#updateAuthorForm');
    const fetchAuthorForm = document.getElementById('fetchAuthorForm');
    const fetchAuthorId = document.getElementById('fetchAuthorId');
    const searchAuthorButton = document.getElementById('searchAuthorButton');
    const searchAuthorInput = document.getElementById('searchAuthorInput');

    loadAuthors();

    function loadAuthors() {
        fetch("/api/authors")
            .then(response => response.json())
            .then(authors => {
                authorTableBody.innerHTML = "";
                authors.forEach(author => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${author.id}</td>                  
                        <td>${author.fullName}</td>
                    `;
                    authorTableBody.appendChild(row);
                });
            });
    }

    addAuthorForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const newAuthor = {
            firstname: document.getElementById('addAuthorFirstname').value,
            lastname: document.getElementById('addAuthorLastname').value,
            surname: document.getElementById('addAuthorSurname').value,
        };

        fetch("/api/authors/addAuthor", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newAuthor)
        })
            .then(response => {
                if (response.ok) {
                    alert("Автор добавлен");
                    loadAuthors();
                    closeModal('addAuthorModal');
                } else {
                    alert("Ошибка при добавлении");
                }
            });
    });

    fetchAuthorForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const authorId = fetchAuthorId.value;

        fetch(`/api/authors/${authorId}`)
            .then(response => response.json())
            .then(author => {
                if (author) {
                    document.getElementById('step1_author').style.display = 'none';
                    document.getElementById('step2_author').style.display = 'block';

                    document.getElementById('updateAuthorFirstname').value = author.firstname;
                    document.getElementById('updateAuthorLastname').value = author.lastname;
                    document.getElementById('updateAuthorSurname').value = author.surname;
                } else {
                    alert("Автор не найден");
                }
            });
    });

    updateAuthorForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const authorId = fetchAuthorId.value;

        const updatedAuthor = {
            firstname: document.getElementById('updateAuthorFirstname').value,
            lastname: document.getElementById('updateAuthorLastname').value,
            surname: document.getElementById('updateAuthorSurname').value,
        };

        fetch(`/api/authors/${authorId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedAuthor)
        })
            .then(response => {
                if (response.ok) {
                    alert("Автор обновлен");
                    loadAuthors();
                    closeModal('updateAuthorModal');
                } else {
                    alert("Ошибка при обновлении");
                }
            });
    });

    deleteAuthorForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const authorId = document.getElementById('deleteAuthorId').value;

        fetch(`/api/authors/${authorId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert("Автор удален");
                    loadAuthors();
                    closeModal('deleteAuthorModal');
                } else {
                    alert("Ошибка при удалении");
                }
            });
    });

    searchAuthorButton.addEventListener("click", function () {
        searchAuthorInput.style.display = searchAuthorInput.style.display === "none" ? "block" : "none";
    });

    searchAuthorInput.addEventListener("input", function () {
        const searchTerm = searchAuthorInput.value.toLowerCase();
        const rows = authorTableBody.querySelectorAll("tr");

        rows.forEach(row => {
            const cells = row.querySelectorAll("td");
            const isVisible = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm));
            row.style.display = isVisible ? "" : "none";
        });
    });
});
