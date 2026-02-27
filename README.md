# Stage 3 (Lab 7)

Modify the program from **Lab Work No. 6** as follows:

- Store the collection in a relational DBMS **(PostgreSQL)**. Remove file-based collection storage.
- Use database facilities *(a sequence)* to generate the `id` field.
- Update the in-memory collection state only after an object has been successfully added to the database.
- All data-retrieval commands must work with the in-memory collection, not with the database.
- Implement user registration and authentication. A user must be able to set a password.
- When storing passwords, hash them using the **SHA-1** algorithm.
- Forbid execution of commands by unauthorized users.
- When storing objects, save information about the user who created the object.
- Users must be able to view all objects in the collection, but may modify only the objects that belong to them.
- To identify the user, send the login and password with each request.
- Implement multi-threaded request processing.

---

## Multithreading requirements

- Use a **Fixed thread pool** for multi-threaded request reading.
- Use a **Cached thread pool** for multi-threaded processing of received requests.
- Use a **Cached thread pool** for multi-threaded response sending.
- Use **`synchronized`** read/write synchronization to control access to the collection.

---

## Work order / environment

- Use **PostgreSQL** as the database.
- To connect to the database on the department server, use host **`pg`**, database name **`studs`**, and username/password the same as those used for connecting to the server.
