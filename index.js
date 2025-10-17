const express = require('express');
const cors = require('cors');
const app = express();
const port = process.env.PORT || 8080;

app.use(cors());
app.use(express.json());

// Endpoint principal
app.get('/', (req, res) => {
  res.json({
    message: '🌱 EcoVive Perú Backend funcionando!',
    status: 'OK',
    version: '1.0.0'
  });
});

// Health check
app.get('/health', (req, res) => {
  res.json({
    status: 'UP',
    service: 'EcoVive Backend'
  });
});

// API endpoint
app.get('/api', (req, res) => {
  res.json({
    message: 'EcoVive API funcionando',
    endpoints: ['/health', '/api/reports', '/api/users']
  });
});

app.listen(port, () => {
  console.log(`🌱 EcoVive Backend funcionando en puerto ${port}`);
});
