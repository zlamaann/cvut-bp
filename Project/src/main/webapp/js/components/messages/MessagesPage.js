import React from 'react';
import Layout from "../common/Layout";
import toastr from "toastr";
import * as messagesActions from "../../actions/messagesActions";
import {bindActionCreators} from "redux";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import MessagesGrid from "./MessagesGrid";

class MessagesPage extends React.Component {

    constructor(props, context) {
        super(props, context);

        this.state = {
            message: Object.assign({}, props.message),
            errors: {},
            saving: false,
            isEditing: false,
            isCreating: false
        };

        this.toggleCreate = this.toggleCreate.bind(this);
        this.toggleEdit = this.toggleEdit.bind(this);
        this.deleteMessage = this.deleteMessage.bind(this);
        this.updateMessageState = this.updateMessageState.bind(this);
        this.findInArray = this.findInArray.bind(this);
        this.saveMessage = this.saveMessage.bind(this);
        this.redirect = this.redirect.bind(this);
    }

    componentWillMount() {
        //this.props.actions.loadMessages();
    }


    toggleCreate() {
        //let message = {id: '', subject: '', description: '', date: ''};
        //this.props.messages.push(message);
        this.setState({isCreating: !this.state.isCreating});
    }

    toggleEdit() {
        this.setState({isEditing: !this.state.isEditing});
    }

    deleteMessage(message) {
        this.props.actions.deleteMessage(message)
            .then(() => {
                toastr.success('Vzkaz smazán.');
            });

    }

    updateMessageState(event) {
        const field = event.target.name;
        let message = Object.assign({}, this.state.message);
        message[field] = event.target.value;
        return this.setState({message:message});
    }

    findInArray(array, id) {
        return array.find( item => item.id === Number(id));
    }

    saveMessage(event) {
        event.preventDefault();
        this.setState({saving: true});
        let message = {
            id: this.state.message.id,
            subject: this.state.message.subject,
            description: this.state.message.description,
            date: new Date(),
            author: this.state.message.author
        };
        this.props.actions.saveMessage(message)
            .then(() => this.redirect())
            .catch(error => {
                toastr.error(error);
                this.setState({saving: false});
            });
    }

    redirect() {
        this.setState({
            saving: false,
            isCreating: false,
            isEditing: false
        });
        toastr.success('Vzkaz uložen.');
    }

    render() {
        const {messages} = this.props;

        return (
            <div>
                <MessagesGrid messages={messages}
                              message={this.state.message}
                              onDelete={this.deleteMessage}
                              onAdd={this.toggleCreate}
                              onSave={this.saveMessage}
                              onChange={this.updateMessageState}
                              onClose={this.toggleCreate}
                              isCreating={this.state.isCreating}
                              errors={this.state.errors}/>
            </div>
        );
    }

}

MessagesPage.propTypes = {
    messages: PropTypes.array.isRequired,
    message: PropTypes.object.isRequired,
    actions: PropTypes.object.isRequired
};

MessagesPage.contextTypes = {
    router: PropTypes.object
};

function getMessageById(messages, id) {
    const result = messages.filter(message => message.id === Number(id));
    if (result.length) return result[0];
    return null;
}

function mapStateToProps(state, ownProps) {
    const messageId = ownProps.match.params.id; // path '/message/:id'

    let message = {id: '', subject: '', description: '', date: '', author: state.current};

    if (messageId && state.messages.length > 0) {
        let result = getMessageById(state.messages, messageId);
        message = {
            id: result.id.toString(),
            subject: result.subject,
            description: result.description,
            date: result.date.toString(),
            author: result.author
        };
    }

    return {
        messages: state.messages,
        message: message
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(messagesActions, dispatch)
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(MessagesPage);