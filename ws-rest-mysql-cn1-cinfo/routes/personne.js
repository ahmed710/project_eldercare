import express from 'express';
import connection from '../services/db.js';

const router = express.Router();

router.get('/', function(req, res, next) {
    connection.query('SELECT * FROM personne', function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.get('/:id', function(req, res, next) {
    connection.query('SELECT * FROM personne WHERE id = ?', [req.params.id], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.post('/', function(req, res, next) {
    console.log(req.body);
    connection.query('INSERT INTO personne SET ?', req.body, function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(201).json(result);
    })
});

router.put('/:id', function(req, res, next) {
    connection.query('UPDATE personne SET ? WHERE id = ?', [req.body, req.params.id], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.delete('/:id', function(req, res, next) {
    connection.query('DELETE FROM personne WHERE id = ?', [req.params.id], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

export default router;