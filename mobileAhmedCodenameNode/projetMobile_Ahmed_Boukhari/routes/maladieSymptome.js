import express from 'express';
import connection from '../services/db.js';

const router = express.Router();

router.get('/', function(req, res, next) {
    connection.query('SELECT * FROM maladie_symptome', function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.get('/:id1/:id2', function(req, res, next) {
    connection.query('SELECT * FROM maladie_symptome WHERE ID_Maladie = ? AND ID_Symptome = ?', [req.params.id1, req.params.id2], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.post('/', function(req, res, next) {
    console.log(req.body);
    connection.query('INSERT INTO maladie_symptome SET ?', req.body, function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(201).json(result);
    })
});

router.put('/:id1/:id2', function(req, res, next) {
    connection.query('UPDATE maladie_symptome SET ? WHERE ID_Maladie = ? AND ID_Symptome = ?', [req.body, req.params.id1, req.params.id2], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});


router.delete('/:id1/:id2', function(req, res, next) {
    connection.query('DELETE FROM maladie_symptome WHERE ID_Maladie = ? AND ID_Symptome = ?', [req.params.id1, req.params.id2], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

export default router;