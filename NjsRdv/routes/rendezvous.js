import express from 'express';
import connection from '../services/db.js';

const router = express.Router();

router.get('/', function (req, res, next) {
    connection.query('SELECT date,id_medecin,etat,id_patient FROM rendezvous', function (err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.get('/:id', function (req, res, next) {
    connection.query('SELECT date,id_medecin,etat FROM rendezvous WHERE idR = ?', [req.params.id], function (err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.post('/', function (req, res, next) {
    console.log(req.body);
    connection.query('INSERT INTO rendezvous SET ?', req.body, function (err, result) {
        if (err) {
            return next(err);
        }
        res.status(201).json(result);
    })
});

router.put('/:idR', function (req, res, next) {
    connection.query('UPDATE rendezvous SET ? WHERE idR = ?', [req.body, req.params.id], function (err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});



export default router;