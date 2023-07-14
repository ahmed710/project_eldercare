import express from 'express';
import connection from '../services/db.js';

const router = express.Router();

router.get('/', function(req, res, next) {
    connection.query('SELECT * FROM pharmacie', function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.get('/:id', function(req, res, next) {
    connection.query('SELECT * FROM pharmacie WHERE ID_pharmacie = ?', [req.params.id], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});
router.get('/:nom', function(req, res, next) {
    connection.query('SELECT ID_pharmacie FROM pharmacie WHERE nom_pharmacie = ?', [req.params.nom], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});
router.get('/:id', function(req, res, next) {
    connection.query('SELECT * FROM pharmacie WHERE ID_pharmacie = ?', [req.params.id], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.post('/', function(req, res, next) {
    console.log(req.body);
    connection.query('INSERT INTO pharmacie SET ?', req.body, function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(201).json(result);
    })
});

router.put('/:id', function(req, res, next) {
    connection.query('UPDATE pharmacie SET ? WHERE ID_pharmacie = ?', [req.body, req.params.id], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
});

router.delete('/:id', function(req, res, next) {
    connection.query('DELETE FROM pharmacie WHERE ID_pharmacie = ?', [req.params.id], function(err, result) {
        if (err) {
            return next(err);
        }
        res.status(200).json(result);
    })
}); 

export default router;