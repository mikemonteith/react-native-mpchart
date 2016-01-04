var React = require('react-native');

var PieChartComponent = React.requireNativeComponent('MPChartPie');

module.exports = React.createClass({
  render: function(){
    return (
      <PieChartComponent values={this.props.values} style={{flex: 1}}/>
    );
  },
});
