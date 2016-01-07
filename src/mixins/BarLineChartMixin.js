'use strict';

var React = require('react-native');
var BaseChartMixin = require('./BaseChartMixin.js');

/*
 * Props that apply to all types of axis
 */
var AxisBasePropMap = {
  enabled: React.PropTypes.bool,
  drawAxisLine: React.PropTypes.bool,
  drawGridLines: React.PropTypes.bool,
};

/*
 * Props that apply to both y axis (Left and Right)
 */
var YAxisPropMap = React.PropTypes.shape({
  ...AxisBasePropMap,

  minValue: React.PropTypes.number,
  maxValue: React.PropTypes.number,
  inverted: React.PropTypes.bool,
});

/*
 * Props that apply to the X axis only
 */
var XAxisPropMap = React.PropTypes.shape({
  ...AxisBasePropMap,
});

var BarLineChartMixin = {
  mixins: [BaseChartMixin],
  propTypes: {
    leftAxis: YAxisPropMap,
    rightAxis: YAxisPropMap,
    xAxis: XAxisPropMap,
  },
};

module.exports = BarLineChartMixin;
