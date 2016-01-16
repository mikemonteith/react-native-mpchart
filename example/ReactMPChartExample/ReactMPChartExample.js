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
    var colors = ['#FF6666', '#66FF66', '#6666FF'];

    return (
      <ScrollView style={{flex: 1}}>
        <Text style={styles.title}>Example app</Text>
        <View style={styles.chart}>
          <Text>Pie Chart {this.state.text}</Text>
          <Charts.PieChart
            style={{flex: 1}}
            data={{
              dataSets: [{
                values: [0.1, 1.3, 3.5],
                colors: colors,
              }],
            }}
            onValueSelect={this.onSegmentSelect}
            onClearSelection={this.onSegmentDeselect}
            drawValues={false}
            rotationAngle={45}
            holeRadius={70}
          />
        </View>
        <View style={styles.chart}>
          <Text>Bar Chart</Text>
          <Charts.BarChart
            style={{flex: 1}}
            data={{
              dataSets:[{
                values: [1,2,3,4,5,6,7],
                colors: ['#990000'],
                drawValues: false,
              },{
                values: [4,3,4,2,1,3,1],
                colors: ['#009900'],
                drawValues: false,
              }],
              xValues: ["A","B","C","D","E","F","E"],
              highlightEnabled: false,
            }}
            gridBackgroundColor="#33990000" //transparent red
            touchEnabled={false}
            leftAxis={{
              //minValue: -12,
              //maxValue: 12,
              drawGridLines: false,
              //inverted: true,
            }}
            rightAxis={{
              enabled: false,
            }}
            xAxis={{
              enabled: false,
            }}
          />
        </View>
        <View style={styles.chart}>
          <Text>Line Chart</Text>
          <Charts.LineChart
            style={{flex: 1}}
            data={{
              dataSets: [{
                values: [1,1.5,8,7,6,5,6,6.5,6,8,5,1,0.2,3,0.5],
                colors: ['#990000'],
                drawCircles: false,
                drawValues: false,
                drawCubic: true,
                lineWidth: 3,
              }],
              xValues: new Array(15).fill("x"),
            }}
            xAxis={{
              position: 'bottom',
            }}
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
