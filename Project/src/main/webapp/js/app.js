import React from 'react';
import { BrowserRouter} from 'react-router-dom';
import Layout from "./components/Layout";
import ReactDOM from 'react-dom';

ReactDOM.render(
    <BrowserRouter>
        <Layout/>
    </BrowserRouter>,
document.getElementById('root')
);

