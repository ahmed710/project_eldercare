import express from 'express';
import connection from '../services/db.js';

const router = express.Router();

router.get('/', function(req, res, next) {
    connection.query('SELECT * FROM symptome', function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.get('/:id', function(req, res, next) {
    connection.query('SELECT * FROM symptome WHERE ID_Symptome = ?', [req.params.id], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.post('/', function(req, res, next) {
    console.log(req.body);
    connection.query('INSERT INTO symptome SET ?', req.body, function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(201).json(result);
    })
});

router.put('/:id', function(req, res, next) {
    connection.query('UPDATE symptome SET ? WHERE ID_Symptome = ?', [req.body, req.params.id], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.delete('/:id', function(req, res, next) {
    connection.query('DELETE FROM symptome WHERE ID_Symptome = ?', [req.params.id], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

export default router;