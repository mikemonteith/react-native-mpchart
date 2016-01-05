'use strict';

var React = require('react-native');
var {
  StyleSheet,
  Text,
  View,
  ScrollView,
} = React;

var Charts = require('react-native-mpchart');

var ReactMPChartExample = React.createClass({
  getInitialState: function(){
    return {
      text: "",
    };
  },

  onSegmentSelect: function(e){
    this.setState({
      text: "selected: " + Math.round(e.nativeEvent.value * 100) / 100,
    });
  },

  onSegmentDeselect: function(e){
    this.setState({
      text: "",
    });
  },

  render: function(){
    var data = [1, 1.3, 3.5];
    var colors = ['#FF6666', '#66FF66', '#6666FF'];

    return (
      <ScrollView style={{flex: 1}}>
        <Text style={styles.title}>Example app</Text>
        <View style={styles.chart}>
          <Text>Pie Chart {this.state.text}</Text>
          <Charts.PieChart
            style={{flex: 1}}
            values={data}
            colors={colors}
            onValueSelect={this.onSegmentSelect}
            onClearSelection={this.onSegmentDeselect}
            drawValues={false}
          />
        </View>
        <View style={styles.chart}>
          <Text>Bar Chart</Text>
          <Charts.BarChart
            style={{flex: 1}}
            values={[1,2,3,2,3,1,10,3,6,8,5,1,0.2,3,-0.5]}
            colors={colors}
            onValueSelect={function(e){ console.log("bar select", e.nativeEvent);}}
            onClearSelection={function(e){ console.log("bar deselect", e.nativeEvent);}}
          />
        </View>
        <View style={styles.chart}>
          <Text>Line Chart</Text>
          <Charts.LineChart
            style={{flex: 1}}
            values={[1,1.5,8,7,6,5,6,6.5,6,8,5,1,0.2,3,0.5]}
            colors={colors}
            onValueSelect={function(e){ console.log("point select", e.nativeEvent);}}
            onClearSelection={function(e){ console.log("point deselect", e.nativeEvent);}}
          />
        </View>
      </ScrollView>
    );
  },
});

var styles = StyleSheet.create({
  title: {
    textAlign: 'center',
    fontSize: 20,
    margin: 10,
  },
  chart: {
    height: 400,
  },
});

module.exports = ReactMPChartExample;
