db.createUser({
    user:"root",
    pwd:"rootMM",
    roles: [
        {role: "readWrite", db:"doodle_poll_db"}
    ]
});