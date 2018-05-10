import React from 'react';
import { HashRouter} from 'react-router-dom';
import configureStore from './store/configureStore';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import Layout from "./components/common/Layout";
import {loadDailyCalendar} from "./actions/calendarActions";
//np import "../node_modules/toastr/build/toastr.min.css";

const store = configureStore();
store.dispatch(loadDailyCalendar());

render(
    <Provider store={store}>
        <HashRouter>
            <Layout/>
        </HashRouter>
    </Provider>,
    document.getElementById('root')
);

