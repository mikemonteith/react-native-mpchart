'use strict';

var React = require('react-native');
var BaseChartMixin = require('./mixins/BaseChartMixin.js');

var BarChart = React.createClass({
  name: 'BarChart',
  mixins: [BaseChartMixin],
  propTypes: {
  },

  render: function(){
    return (
      <BarChartComponent
        {...this.props}
        onSelect={this._onSelect}
      />
    );
  },

});

var BarChartComponent = React.requireNativeComponent('MPChartBar', BarChart, {
  nativeOnly: {onSelect: true},
});

module.exports = BarChart;
