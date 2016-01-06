'use strict';

var React = require('react-native');
var BaseChartMixin = require('./mixins/BaseChartMixin.js');

var LineChart = React.createClass({
  name: 'LineChart',
  mixins: [BaseChartMixin],
  propTypes: {
  },

  render: function(){
    return (
      <LineChartComponent
        {...this.props}
        onSelect={this._onSelect}
      />
    );
  },

});

var LineChartComponent = React.requireNativeComponent('MPChartLine', LineChart, {
  nativeOnly: {onSelect: true},
});

module.exports = LineChart;
