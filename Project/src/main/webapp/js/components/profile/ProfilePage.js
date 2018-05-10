import React from 'react';
import PropTypes from "prop-types";
import toastr from "toastr";
import { bindActionCreators } from "redux";
import * as profileActions from "../../actions/profileActions";
import { connect } from "react-redux";
import ProfileForm from "./ProfileForm";

class ProfilePage extends React.Component {

    constructor(props, context) {
        super(props, context);



        this.state = {
            current: Object.assign({}, props.current),
            errors: {},
            saving: false,
            isEditing: false
        };

        this.updateCurrentState = this.updateCurrentState.bind(this);
        this.findInArray = this.findInArray.bind(this);
        this.saveProfile = this.saveProfile.bind(this);
        this.redirect = this.redirect.bind(this);
        this.toggleEdit = this.toggleEdit.bind(this);
    }

    componentWillMount() {
        this.props.actions.loadProfile();
    }

    componentDidUpdate() {
        this.props.actions.loadProfile();
    }


    componentWillReceiveProps(nextProps) {
        if (this.props.current.id !== nextProps.current.id) {
            this.setState({current: Object.assign({}, nextProps.current)});
        }
    }

    updateCurrentState(event) {
        const field = event.target.name;
        let current = Object.assign({}, this.state.current);
        current[field] = event.target.value;
        return this.setState({current:current});
    }

    findInArray(array, id) {
        return array.find( item => item.id === Number(id));
    }

    saveProfile(event) {
        event.preventDefault();
        this.setState({saving: true});
        let current = {
            id: this.state.current.id,
            name: this.state.current.name,
            surname: this.state.current.surname,
            email: this.state.current.email,
            phoneNumber: this.state.current.phoneNumber,
            address: {
                id: this.state.current.address.id,
                streetName: this.state.current.address.streetName,
                streetNumber: this.state.current.address.streetNumber,
                city: this.state.current.address.city,
                postalCode: this.state.current.address.postalCode
            },
            roles: this.state.current.roles,
            performances: []
        };

        this.props.actions.saveProfile(current)
            .then(() => this.redirect())
            .catch(error => {
                toastr.error(error);
                this.setState({saving: false});
            });
    }

    toggleEdit() {
        this.setState({isEditing: !this.state.isEditing});
    }

    redirect() {
        this.setState({
            saving: false,
            isEditing: false
        });
        toastr.success('Profil uložen.');
    }

    render() {
        if (this.state.isEditing) {
            return (
                <ProfileForm
                    performances={[]}
                    roles={[]}
                    current={this.state.current}
                    onSave={this.saveProfile}
                    onChange={this.updateCurrentState}
                    errors={this.state.errors}
                    saving={this.state.saving}/>
            );
        }

        return (
            <div>
                <h3>{this.state.current.name + " " + this.state.current.surname}</h3>
                <p>E-mail: {this.state.current.email}</p>
                <p>Telefonní číslo: {this.state.current.phoneNumber}</p>
                <h4>Adresa</h4>
                <p>Ulice a 4.p.: {this.state.current.address.streetName + " " + this.state.current.address.streetNumber}</p>
                <p>Město: {this.state.current.address.city}</p>
                <p>PSČ: {this.state.current.address.postalCode}</p>
                <input type="submit" value="Upravit" onClick={this.toggleEdit}/>
            </div>
        )

    }
}

ProfilePage.propTypes = {
    current: PropTypes.object.isRequired,
    actions: PropTypes.object.isRequired,
};

ProfilePage.contextTypes = {
    router: PropTypes.object
};

function mapStateToProps(state, ownProps) {

    let current = {
        id: '', name: '', surname: '', email: '', phoneNumber: '', address: { id: '', streetName: '', streetNumber: '',  city: '', postalCode: ''}, roles: [], performances: []
    };

    if (state.current.id) {
        let result = state.current;
        current = {
            id: result.id.toString(),
            name: result.name,
            surname: result.surname,
            email: result.email,
            phoneNumber: result.phoneNumber.toString(),
            address: {
                id: result.address.id.toString(),
                streetName: result.address.streetName,
                streetNumber: result.address.streetNumber.toString(),
                city: result.address.city,
                postalCode: result.address.postalCode.toString()
            },
            roles:result.roles,
            performances: []
        };

    }

    return {
        current: current
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(profileActions, dispatch)
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(ProfilePage);
