import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import * as performancesActions from '../../actions/performancesActions';
import { bindActionCreators } from 'redux';
import toastr from 'toastr';
import PerformanceForm from "./PerformanceForm";

class ManagePerformancesPage extends React.Component {
    constructor(props, context) {
        super(props, context);

        this.state = {
            performance: Object.assign({}, props.performance),
            errors: {},
            saving: false,
            isEditing: false
        };

        this.updatePerformanceState = this.updatePerformanceState.bind(this);
        this.findInArray = this.findInArray.bind(this);
        this.savePerformance = this.savePerformance.bind(this);
        this.redirect = this.redirect.bind(this);
        this.toggleEdit = this.toggleEdit.bind(this);
    }

    componentWillMount() {
        this.props.actions.loadPerformances();

    }

    componentWillReceiveProps(nextProps) {
        if (this.props.performance.id !== nextProps.performance.id) {
            this.setState({performance: Object.assign({}, nextProps.performance)});
        }
    }

    updatePerformanceState(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const field = target.name;
        let performance = Object.assign({}, this.state.performance);
        performance[field] = value;
        return this.setState({performance:performance});
    }

    findInArray(array, id) {
        return array.find( item => item.id === Number(id));
    }

    savePerformance(event) {
        event.preventDefault();
        this.setState({saving: true});
        let performance = {
            id: this.state.performance.id,
            name: this.state.performance.name,
            length: this.state.performance.length,
            isRegular: this.state.performance.isRegular,
            description: this.state.performance.description
        };

        this.props.actions.savePerformance(performance)
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
        toastr.success('Představení uloženo.');
    }

    render() {
        let paramId;
        if (this.props.match.params)  paramId = this.props.match.params.id;

        if (this.state.isEditing || !paramId) {
            return (
                <PerformanceForm
                    performance={this.state.performance}
                    onSave={this.savePerformance}
                    onChange={this.updatePerformanceState}
                    errors={this.state.errors}
                    saving={this.state.saving}/>
            );
        }

        return (
            <div>
                <h3>{this.state.performance.name}</h3>
                <p>Délka: {this.state.performance.length}</p>
                <p>Běžné představení: {this.state.performance.isRegular ? "Ano" : "Ne"}</p>
                <p>Popis: {this.state.performance.description}</p>
                <input type="submit" value="Upravit" onClick={this.toggleEdit}/>
            </div>
        )

    }
}

ManagePerformancesPage.propTypes = {
    performance: PropTypes.object.isRequired,
    actions: PropTypes.object.isRequired,
};

ManagePerformancesPage.contextTypes = {
    router: PropTypes.object
};

function getPerformanceById(performances, id) {
    const result = performances.filter(performance => performance.id === Number(id));
    if (result.length) return result[0];
    return null;
}

function mapStateToProps(state, ownProps) {
    const performanceId = ownProps.match.params.id; // path '/performance/:id'
    let performance = {
        id: '', name: '', length: '', isRegular: true, description: ''
    };

    if (performanceId && state.performances.length > 0) {
        let result = getPerformanceById(state.performances, performanceId);
        performance = {
            id: result.id.toString(),
            name: result.name,
            length: result.length.toString(),
            isRegular: result.isRegular,
            description: result.description
        };

    }

    return {
        performance: performance
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(performancesActions, dispatch)
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(ManagePerformancesPage);