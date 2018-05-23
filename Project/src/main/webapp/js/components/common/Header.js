
import React from 'react';
import {Link, NavLink} from 'react-router-dom';
import {logout} from "../../actions/profileActions";
import {connect} from "react-redux";

class Header extends React.Component {

    constructor(props, context) {
        super(props, context);

        this.onLogout = this.onLogout.bind(this);
    }

    onLogout = () => {
        this.props.logout()
    };

    render () {
        return (
            <header className="flex nav-down">
                <img src="../../../resources/images/logo.gif" alt=""/>
                    <section className="flex">
                        <nav className="flex" >
                            <ul id="nav-top">
                                <li>
                                    <NavLink to="/profile">PROFIL</NavLink></li>
                                <li>
                                    <a href onClick={this.onLogout}>ODHLÁSIT SE</a></li>
                            </ul>

                        </nav>
                        <nav className="flex">
                            <ul id="main-nav">
                                <li>
                                    <NavLink to="/" exact activeClassName="active">KALENDÁŘ</NavLink>
                                </li>
                                <li>
                                    <NavLink to="/shifts" activeClassName="active">SMĚNY</NavLink>
                                </li>
                                <li>
                                    <NavLink to="/messages" activeClassName="active">NÁSTĚNKA</NavLink>
                                </li>
                                <li>
                                    <NavLink to="/performances" activeClassName="active">PŘEDSTAVENÍ</NavLink>
                                </li>
                                <li>
                                    <NavLink to="/employees" activeClassName="active">ZAMĚSTNANCI</NavLink>
                                </li>
                            </ul>
                        </nav>
                    </section>

            </header>
        );
    }
}

export default connect(null, { logout })(Header);