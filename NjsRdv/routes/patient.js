import express from 'express';
import connection from '../services/db.js';

const router = express.Router();

router.get('/', function (req, res, next) {
    connection.query("SELECT * FROM user where role = 'patient'", function (err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

export default router;