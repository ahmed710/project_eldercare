import express from 'express';
import cors from 'cors';
import morgan from 'morgan';

import { notFoundError, errorHandler } from './services/error-handler.js';

// ************************ Import routes *************************
import maladieRoutes from './routes/maladie.js';
import symptomeRoutes from './routes/symptome.js';
import maladieSymptomeRoutes from './routes/maladieSymptome.js';
import personneRoutes from './routes/personne.js';


// ****************************************************************

const app = express();
const port = process.env.PORT || 9090;

app.use(cors());
app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// **************************** Routes ****************************
app.use('/personne', personneRoutes);
app.use('/maladie', maladieRoutes);
app.use('/symptome', symptomeRoutes);
app.use('/maladieSymptome', maladieSymptomeRoutes);
// ****************************************************************

app.use(notFoundError);
app.use(errorHandler);

app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}/`);
});