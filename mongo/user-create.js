let db = connect("mongodb://localhost/admin");
db.createUser(
    {
        user: "admin",
        pwd: "password",
        //https://docs.mongodb.com/manual/reference/built-in-roles/#built-in-roles
        roles: [
            { role: "readWrite", db: "admin" },
            { role: "readWrite", db: "productDb" }
            ]
    }
)
