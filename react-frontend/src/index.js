import './index.css';
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import { AuthenticationProvider } from './context/AuthenticationContext';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <AuthenticationProvider>
        <BrowserRouter>
            <App />
        </BrowserRouter>
    </AuthenticationProvider>
);