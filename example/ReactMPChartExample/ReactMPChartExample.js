'use strict';

var React = require('react-native');
var {
  StyleSheet,
  Text,
  View,
} = React;

var Chart = require('react-native-mpchart');

var data = [1, 2, 3, 4];

var ReactMPChartExample = React.createClass({
  render: function(){
    return (
      <View style={{flex: 1}}>
        <Text>Example app</Text>
        <Chart.PieChart values={data} style={{flex: 1}}/>
      </View>
    );
  },
});

module.exports = ReactMPChartExample;
