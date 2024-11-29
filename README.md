# test-inforce
INFORCE Trainee Java Developer task

markdown
# Інструкція для запуску проекту

## Запуск проекту

1. **Відкриваємо консоль та переходимо у директорію проекту:**
   ```bash
   cd C:\*your path*\test-inforce
   ```

2. **Збираємо проект за допомогою Maven:**

   ```bash
   mvnw clean install -DskipTests
   ```

   або (для Linux/MacOS):

   ```bash
   ./mvnw clean install -DskipTests
   ```

3. **Після успішної збірки виконуємо команду для запуску Docker-контейнерів:**

   ```bash
   docker-compose up -d
   ```

4. **Перевіряємо працездатність додатку.**

---

## Робота з базою даних

1. **Входимо в контейнер `mysql-book-db`:**

   ```bash
   docker exec -it mysql-book-db bash
   ```

2. **Підключаємось до MySQL:**

   ```bash
   mysql -u root -p
   ```

   Вводимо пароль: `admin`

3. **Обираємо базу даних:**

   ```sql
   use books_db;
   ```

4. **Перевіряємо таблицю книг:**

   ```sql
   select * from t_books;
   ```

   На початку таблиця має бути порожня.

---

## Тестування ендпоїнтів через Postman

Використовуємо [Postman](https://web.postman.co) для перевірки роботи API.

### Доступні ендпоїнти:

1. **GET `/scrape`**

   - Зберігає дані тільки з першої сторінки.
   - URL: `http://localhost:8080/scrape`

2. **GET `/scrape/{page}`**

   - Зберігає дані зі сторінки, номер якої вказано замість `{page}`.
   - URL: `http://localhost:8080/scrape/{page}`
   - `{page}` — ціле число.

### Після виконання запитів:

Повертаємось у консоль та повторно виконуємо команду:

```sql
select * from t_books;
```

В таблиці `t_books` мають з’явитися нові дані.

---

## Завершення роботи

1. Виходимо з MySQL:

   ```bash
   exit
   ```

2. Виходимо з контейнера:

   ```bash
   exit
   ```
