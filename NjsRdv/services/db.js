import mysql from 'mysql2';

const connection = mysql.createConnection({
    host: 'localhost',
    port: '3306',
    user: 'root',
    password: '',
    database: 'eldercarebase'
});

connection.connect();

export default connection;