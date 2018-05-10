import React from 'react';
import PropTypes from "prop-types";
import toastr from "toastr";
import { bindActionCreators } from "redux";
import * as performancesActions from "../../actions/performancesActions";
import { connect } from "react-redux";
import PerformancesList from "./PerformancesList";

class PerformancesPage extends React.Component {

    constructor(props, context) {
        super(props, context);

        this.redirectToAddPerformance = this.redirectToAddPerformance.bind(this);
        this.deletePerformance = this.deletePerformance.bind(this);
    }

    componentWillMount() {
        this.props.actions.loadPerformances();
    }

    redirectToAddPerformance() {
        this.context.router.history.push('/performance');
    }

    deletePerformance(performance) {
        this.props.actions.deletePerformance(performance)
            .then(() => {
                toastr.success('Představení smazáno.');
            });

    }

    render() {
        const {performances} = this.props;

        return (
            <div>
                <input type="submit"
                       value="Přidat představení"
                       className=""
                       onClick={this.redirectToAddPerformance}
                />
                <PerformancesList performances={performances} onClick={this.deletePerformance}/>
            </div>
        );
    }

}

PerformancesPage.propTypes = {
    performances: PropTypes.array.isRequired,
    actions: PropTypes.object.isRequired
};

PerformancesPage.contextTypes = {
    router: PropTypes.object
};

function mapStateToProps(state, ownProps) {
    return {
        performances: state.performances
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(performancesActions, dispatch)
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(PerformancesPage);