import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import * as shiftActions from '../../actions/shiftActions';
import { loadPerformances } from '../../actions/performancesActions';
import { loadShiftTypes } from '../../actions/shiftTypesActions';
import { loadLocations } from '../../actions/locationActions';
import { bindActionCreators } from 'redux';
import ShiftForm from './ShiftForm';
import toastr from 'toastr';

class ManageShiftPage extends React.Component {
    constructor(props, context) {
        super(props, context);

        this.state = {
            shift: Object.assign({}, props.shift),
            errors: {},
            saving: false
        };

        this.updateShiftState = this.updateShiftState.bind(this);
        this.findInArray = this.findInArray.bind(this);
        this.saveShift = this.saveShift.bind(this)
        this.redirect = this.redirect.bind(this);
    }

    componentWillMount() {
        this.props.actions.loadShifts();
        this.props.loadLocations();
        this.props.loadShiftTypes();
        this.props.loadPerformances();

    }

    componentWillReceiveProps(nextProps) {
        if (this.props.shift.id !== nextProps.shift.id) {
            this.setState({shift: Object.assign({}, nextProps.shift)});
        }
    }

    updateShiftState(event) {
        const field = event.target.name;
        let shift = Object.assign({}, this.state.shift);
        shift[field] = event.target.value;
        return this.setState({shift:shift});
    }

    findInArray(array, id) {
        return array.find( item => item.id === Number(id));
    }

    saveShift(event) {
        event.preventDefault();
        this.setState({saving: true});
        let shift = {
            id: this.state.shift.id,
            timeFrom: this.state.shift.timeFrom,
            timeTo: this.state.shift.timeTo,
            performance: this.findInArray(this.props.performances, this.state.shift.performance),
            type: this.findInArray(this.props.shiftTypes, this.state.shift.type),
            location: this.findInArray(this.props.locations, this.state.shift.location),
            notes: this.state.shift.notes
        };
        this.props.actions.saveShift(shift)
            .then(() => this.redirect())
            .catch(error => {
                toastr.error(error);
                this.setState({saving: false});
            });
    }

    redirect() {
        this.setState({saving: false});
        toastr.success('Událost uložena.');
        this.context.router.history.push('/');
    }

    render() {
        return (
            <ShiftForm
                performances={this.props.performancesFormatted}
                types={this.props.shiftTypesFormatted}
                locations={this.props.locationsFormatted}
                shift={this.state.shift}
                onSave={this.saveShift}
                onChange={this.updateShiftState}
                errors={this.state.errors}
                saving={this.state.saving}/>
        );
    }
}

ManageShiftPage.propTypes = {
    shift: PropTypes.object.isRequired,
    performancesFormatted: PropTypes.array.isRequired,
    shiftTypesFormatted: PropTypes.array.isRequired,
    locationsFormatted: PropTypes.array.isRequired,
    performances: PropTypes.array.isRequired,
    shiftTypes: PropTypes.array.isRequired,
    locations: PropTypes.array.isRequired,
    actions: PropTypes.object.isRequired,
    loadPerformances: PropTypes.func.isRequired,
    loadShiftTypes: PropTypes.func.isRequired,
    loadLocations: PropTypes.func.isRequired,
};

ManageShiftPage.contextTypes = {
    router: PropTypes.object
};

function getShiftById(shifts, id) {
    const result = shifts.filter(shift => shift.id === Number(id));
    if (result.length) return result[0];
    return null;
}

function mapStateToProps(state, ownProps) {
    const shiftId = ownProps.match.params.id; // path '/shift/:id'

    let shift = {id: '', timeFrom: '', timeTo: '', performance: '', type: '', location: '', notes: ''};

    if (shiftId && state.shifts.length > 0) {
        let result = getShiftById(state.shifts, shiftId);
        shift = {
            id: result.id.toString(),
            timeFrom: result.timeFrom.toString(),
            timeTo: result.timeTo.toString(),
            performance: result.performance.id.toString(),
            type: result.type.id.toString(),
            location: result.location.id.toString(),
            notes: result.notes
        };
    }

    const performancesFormattedForDropdown = state.performances.map(performance => {
       return {
           value: performance.id,
           text: performance.name
       }
    });

    const shiftTypesFormattedForDropdown = state.shiftTypes.map(type => {
        return {
            value: type.id,
            text: type.value
        }
    });

    const locationsFormattedForDropdown = state.locations.map(location => {
        return {
            value: location.id,
            text: location.name
        }
    });

    return {
        shift: shift,
        performancesFormatted: performancesFormattedForDropdown,
        shiftTypesFormatted: shiftTypesFormattedForDropdown,
        locationsFormatted: locationsFormattedForDropdown,
        performances: state.performances,
        shiftTypes: state.shiftTypes,
        locations: state.locations
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(shiftActions, dispatch),
        loadPerformances: bindActionCreators(loadPerformances, dispatch),
        loadShiftTypes: bindActionCreators(loadShiftTypes, dispatch),
        loadLocations: bindActionCreators(loadLocations, dispatch)
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(ManageShiftPage);