### THIS LIBRARY IS NO LONGER MAINTAINED.
See https://github.com/hongyin163/react-native-chart-android and https://github.com/Jpadilla1/react-native-ios-charts

# react-native-mpchart


A React Native chart library

This module is a bridge between [React Native](https://facebook.github.io/react-native/) and [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart). Eventually, iOS will be supported via the iOS port of MPAndroidChart, [ios-charts](https://github.com/danielgindi/ios-charts).

All charts and properties come from MPAndroidChart.

## Installation

### Android

#### Install the node package

`npm install --save https://github.com/mikemonteith/react-native-mpchart`

#### Import the java library from `node_modules/react-native-mpchart/android`

*android/settings.gradle*
```
include ':react-native-mpchart', ':app'

project(':react-native-mpchart').projectDir = new File(settingsDir, '../node_modules/react-native-mpchart/android')
```

Add the project to your gradle dependencies

*android/app/build.gradle*
```
apply plugin: 'com.android.application'

android {
  ...
}

dependencies {
  compile fileTree(dir: "libs", include: ["*.jar"])
  compile "com.android.support:appcompat-v7:23.0.0"
  compile "com.facebook.react:react-native:0.19.+"
  compile project(":react-native-mpchart")
}

```

Add the package to your ReactInstanceManager

*MainActivity.java*
```java

//import chart package (usually, your IDE will do this for you)
import com.mikemonteith.reactnativempchart.MPChartPackage;

// ...

/**
* A list of packages used by the app. If the app uses additional views
* or modules besides the default ones, add more packages here.
*/
@Override
protected List<ReactPackage> getPackages() {
	return Arrays.<ReactPackage>asList(
		new MainReactPackage(),
		new MPChartPackage() // <-- add this line
	);
}

// ...
```

## Basic Usage

```js
var React = require('react-native');
var Charts = require('react-native-mpchart');

var ExampleComponent = React.createClass({
  render: function(){
    return (
      <Charts.BarChart
        style={{flex: 1}}
        data={{
          dataSets: [{
            values: [1, 2, 3, 4, 5],
          }],
          xValues: ["one", "two", "three", "four", "five"],
        }}
      />
    );
  },
});
```

## Charts

* PieChart
* BarChart
* LineChart

## Props
(inherits from [React.View](https://facebook.github.io/react-native/docs/view.html#props))

| Prop | Description | Type | Charts |
|------|-------------|------|--------|
| data | see [data properties](#data-properties) | object | All
| touchEnabled | handle touch gestures on the chart | boolean | All
| xAxis | see [xaxis properties](#x-axis-properties) | object | Bar, Line
| leftAxis | see [yaxis properties](#y-axis-properties) | object | Bar, Line
| rightAxis | see [yaxis properties](#y-axis-properties) | object | Bar, Line
| gridBackgroundColor | color of the grid background | string | Bar, Line
| holeRadius | percentage radius of the pie hole | number | Pie
| transparentCircleRadius | percentage radius of the transparent overlay | number | Pie
| transparentCircleColor | color of the transparent overlay | string | Pie
| transparentCircleAlpha | alpha of the transparent overlay between 0 and 1 | number | Pie
| rotationAngle | clockwise angle in degrees where the first segment starts (0 = 3 o'clock) | number | Pie


#### data properties

| Property | Description | Type | Charts |
|----------|-------------|------|--------|
| dataSets | see [dataSet properties](#dataSet-properties) | array of DataSet objects | All |
| xValues | strings specifying x axis labels | array of strings | All |

#### dataSet properties

| Property | Description | Type | Charts |
|----------|-------------|------|--------|
| values | values to plot on the graph | array of numbers | All
| colors | colors to draw the chart data | array of strings | All
| drawValues | draw value text | boolean | All
| drawCircles | draw circles on data points | boolean | Line
| drawCubic | draw a smooth line-of-best-fit | boolean | Line
| lineWidth | width of the line-of-best-fit | number | Line
| drawFill | draw a colored area underneath the line | boolean | Line
| fillColor | color of fill underneath the line | string | Line
| fillAlpha | alpha of the fill underneath the line between 0 and 1 | number | Line

#### axis properties

| Property | Description | Type | Charts |
|----------|-------------|------|--------|
| enabled | draw the axis | boolean | Bar, Line |
| drawAxisLine | draw the main axis line | boolean | Bar, Line
| drawGridLines | draw grid lines that are parallel to this axis | boolean | Bar, Line

#### x axis properties
(inherits from [axis properties](#axis-properties))

| Property | Description | Type | Charts |
|----------|-------------|------|--------|
| position | where to draw the axis | string `'bottom'`, `'top'` or `'bothSided'` | Bar, Line

#### y axis properties
(inherits from [axis properties](#axis-properties))

| Property | Description | Type | Charts |
|----------|-------------|------|--------|
| minValue | minimum value | number | Bar, Line
| maxValue | maximum value | number | Bar, Line
| inverted | if true, draw the data upside-down | boolean | Bar, Line


## TODO

* Support all MPAndroidChart charts
* Support all MPAndroidChart properties
* Support iOS
* Release as npm package
