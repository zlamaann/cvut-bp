import React from 'react';
import {Route, Switch} from "react-router-dom";
import Calendar from "./calendar/Calendar";
import Shifts from "./shifts/Shifts";
import Messages from "./messages/Messages";
import Performances from "./performances/Performances";
import Rehearsals from "./rehearsals/Rehearsals";
import Contacts from "./Contacts";
import Employees from "./employees/Employees";
import Profile from "./Profile";
import Logout from "./Logout";

export default class Main extends React.Component {

    render() {
        return (
                <Switch>
                    <Route exact path="/" component={Calendar}/>
                    <Route path="/shifts" component={Shifts}/>
                    <Route path="/messages" component={Messages}/>
                    <Route path="/performance" component={Performances}/>
                    <Route path="/employees" component={Employees}/>
                    <Route path="/rehearsals" component={Rehearsals}/>
                    <Route path="/contacts" component={Contacts}/>
                    <Route path="/profile" component={Profile}/>
                    <Route path="/logout" component={Logout}/>
                </Switch>
        );
    }
}