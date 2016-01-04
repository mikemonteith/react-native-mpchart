var React = require('react-native');

var PieChartComponent = React.requireNativeComponent('MPChartPie');

module.exports = React.createClass({
  propTypes: {
    colors: React.PropTypes.array,
    values: React.PropTypes.array,
  },

  render: function(){
    return (
      <PieChartComponent
        style={[{flex: 1}, this.props.style]}
        colors={this.props.colors}
        values={this.props.values}
      />
    );
  },
});
