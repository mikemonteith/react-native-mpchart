'use strict';

var React = require('react-native');
var {
  Text,
  View,
} = React;

var Chart = require('react-native-mpchart');

var ReactMPChartExample = React.createClass({
  render: function(){
    var data = [1, 1.3, 3.5];
    var colors = ['#FF6666', '#66FF66', '#6666FF'];

    return (
      <View style={{flex: 1}}>
        <Text>Example app</Text>
        <Chart.PieChart values={data} colors={colors}/>
      </View>
    );
  },
});

module.exports = ReactMPChartExample;
