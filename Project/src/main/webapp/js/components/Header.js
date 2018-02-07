
import React from 'react';
import {Link} from 'react-router-dom';

export default class Header extends React.Component {

    render () {
        return (
            <header className="flex nav-down">
                <img src="../../resources/images/logo.gif" alt=""/>
                    <section className="flex">
                        <nav className="flex" >
                            <ul id="nav-top">
                                <li>
                                    <Link to="/profile">PROFIL</Link></li>
                                <li>
                                    <Link to="/logout">ODHLÁSIT SE</Link></li>
                            </ul>

                        </nav>
                        <nav className="flex">
                            <ul id="main-nav">
                                <li className="active">
                                    <Link to="/">KALENDÁŘ</Link>
                                </li>
                                <li>
                                    <Link to="/shifts">SMĚNY</Link>
                                </li>
                                <li>
                                    <Link to="/messages">NÁSTĚNKA</Link>
                                </li>
                                <li>
                                    <Link to="/performance">PŘEDSTAVENÍ</Link>
                                </li>
                                <li>
                                    <Link to="/employees">ZAMĚSTNANCI</Link>
                                </li>
                                <li>
                                    <Link to="/rehearsals">ZKOUŠKY</Link>
                                </li>
                                <li>
                                    <Link to="/contacts">KONTAKTY</Link>
                                </li>
                            </ul>
                        </nav>
                    </section>

            </header>
        );
    }
}