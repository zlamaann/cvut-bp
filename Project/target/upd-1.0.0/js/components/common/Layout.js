import React from 'react';
import Main from "./Main";
import Header from "./Header";

export default class Layout extends React.Component {

    render() {
        return (
            <div>
                <Header/>
                <Main/>
            </div>
        );
    }
}