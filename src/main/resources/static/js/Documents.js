function setExampleQuery(query) {
    document.getElementById('sqlQueryInput').value = query;
}

async function downloadDocument() {
    const sql = document.getElementById('sqlQueryInput').value;
    if (!sql.trim()) {
        alert('Введите SQL запрос!');
        return;
    }
    try {
        const response = await fetch('/api/documentSQL/download', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ sql })
        });

        if (!response.ok) throw new Error('Ошибка при выполнении запроса');

        // Получаем JSON из ответа
        const json = await response.json();

        // Извлекаем имя таблицы из ответа
        const tableName = json.tableName || 'default'; // Если имя таблицы отсутствует, используем 'default'

        // Преобразуем данные в строку
        const jsonString = JSON.stringify(json, null, 2);

        // Создаем Blob с текстом JSON
        const blob = new Blob([jsonString], { type: 'text/plain' });

        // Создаем ссылку для скачивания
        const link = document.createElement('a');
        link.href = URL.createObjectURL(blob);
        link.download = `${tableName}.txt`; // Имя файла зависит от названия таблицы

        // Инициируем скачивание
        link.click();

    } catch (error) {
        console.error('Ошибка:', error);
        alert('Произошла ошибка при выполнении запроса.');
    }
}

// async function downloadDocument(){
//     const sql = document.getElementById('sqlQueryInput').value;
//     if (!sql.trim()) {
//         alert('Введите SQL запрос!');
//         return;
//     }
//     try {
//         const response = await fetch('/api/documentSQL/download', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify({ sql })
//         });
//
//         if (!response.ok) throw new Error('Ошибка при выполнении запроса');
//
//         // Получаем JSON из ответа
//         const json = await response.json();
//
//         // Преобразуем JSON в строку
//         const jsonString = JSON.stringify(json, null, 2); // форматируем с отступами для удобства чтения
//         const tableName = json.tableName || 'default';
//         // Создаем Blob с текстом JSON
//         const blob = new Blob([jsonString], { type: 'text/plain' });
//         // Создаем ссылку для скачивания
//         const link = document.createElement('a');
//         link.href = URL.createObjectURL(blob);
//         link.download = 'document.txt'; // Имя файла для скачивания
//
//         // Инициируем скачивание
//         link.click();
//
//     } catch (error) {
//         console.error('Ошибка:', error);
//         alert('Произошла ошибка при выполнении запроса.');
//     }
//
// }

async function executeQuery() {
    const query = document.getElementById('sqlQueryInput').value;

    if (!query.trim()) {
        alert('Введите SQL запрос!');
        return;
    }

    try {
        const response = await fetch('/api/documentSQL/execute', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ query })
        });

        if (!response.ok) throw new Error('Ошибка при выполнении запроса');

        const data = await response.json();

        updateTable(data.columns, data.rows);
    } catch (error) {
        console.error('Ошибка:', error);
        alert('Произошла ошибка при выполнении запроса.');
    }
}

function updateTable(columns, rows) {
    const tableHead = document.getElementById('sqlResultTableHead');
    const tableBody = document.getElementById('sqlResultTableBody');

    tableHead.innerHTML = '';
    tableBody.innerHTML = '';

    const headerRow = document.createElement('tr');

    columns.forEach((column, index) =>  {
        const th = document.createElement('th');
        th.textContent = column;
        th.className = "sortable";
        th.setAttribute('data-order', 'asc');
        th.setAttribute('data-column', index);
        headerRow.appendChild(th);
    });
    tableHead.appendChild(headerRow);

    rows.forEach(row => {
        const tr = document.createElement('tr');
        row.forEach(cell => {
            const td = document.createElement('td');
            td.textContent = cell;
            tr.appendChild(td);
        });
        tableBody.appendChild(tr);
    });
}
