# date-time-fields Add-on for Vaadin 7

date-time-fields is a UI component add-on for Vaadin 7. At the moment it adds Field-Components for DateTime, LocalTime, Interval and DateTimeAndLocalTime.

## Online demo

Not available

## Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to http://vaadin.com/addon/date-time-fields

## Building and running demo

```bash
git clone git@github.com:andreashuthe/date-time-fields.git
mvn clean install
cd demo
mvn jetty:run
```

To see the demo, navigate to <http://localhost:8080/>

### Date-Time-Fields Shortcuts

By adding the shortcuts your customer will be able to enter dates in a very fast Way.

Locale **EN** / **DEFAULT**

Press _n_ -> **todays date**

Press _t_ -> **tomorrows date**

Press _y_ -> **yesterdays date**

Press _+_ -> **add one day to date**

Press _-_ -> **minus one day to date**
 
Locale **DE** 

Press _h_ -> **todays date**

Press _m_ -> **tomorrows date**

Press _g_ -> **yesterdays date**

Press _+_ -> **add one day to date**

Press _-_ -> **minus one day to date**

This functionality is very useful for adding appointments, invoices and many other usecases.

### Debugging server-side

If you have not already compiled the widgetset, do it now by running vaadin:install Maven target for date-time-fields-root project.

If you have a JRebel license, it makes on the fly code changes faster. Just add JRebel nature to your date-time-fields-demo project by clicking project with right mouse button and choosing JRebel > Add JRebel Nature

To debug project and make code modifications on the fly in the server-side, right-click the date-time-fields-demo project and choose Debug As > Debug on Server. Navigate to http://localhost:8080/joda-time-fields-demo/ to see the application.

### Debugging client-side

The most common way of debugging and making changes to the client-side code is dev-mode. To create debug configuration for it, open date-time-fields-demo project properties and click "Create Development Mode Launch" button on the Vaadin tab. Right-click newly added "GWT development mode for date-time-fields-demo.launch" and choose Debug As > Debug Configurations... Open up Classpath tab for the development mode configuration and choose User Entries. Click Advanced... and select Add Folders. Choose Java and Resources under joda-time-fields/src/main and click ok. Now you are ready to start debugging the client-side code by clicking debug. Click Launch Default Browser button in the GWT Development Mode in the launched application. Now you can modify and breakpoints to client-side classes and see changes by reloading the web page. 

Another way of debugging client-side is superdev mode. To enable it, uncomment devModeRedirectEnabled line from the end of DemoWidgetSet.gwt.xml located under joda-time-fields-demo resources folder and compile the widgetset once by running vaadin:compile Maven target for date-time-fields-demo. Refresh date-time-fields-demo project resources by right clicking the project and choosing Refresh. Click "Create SuperDevMode Launch" button on the Vaadin tab of the date-time-fields-demo project properties panel to create superder mode code server launch configuration and modify the class path as instructed above. After starting the code server by running SuperDevMode launch as Java application, you can navigate to http://localhost:8080/date-time-fields-demo/?superdevmode. Now all code changes you do to your client side will get compiled as soon as you reload the web page. You can also access Java-sources and set breakpoints inside Chrome if you enable source maps from inspector settings. 

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

DateTimeField is written by [Andreas Huth](https://github.com/andreashuthe)

# Developer Guide

## Getting started

Here is a simple example on how to try out the add-on component:

```java
final DateTimeDemoBean demoBean = new DateTimeDemoBean();
BeanItem<DateTimeDemoBean> demoBeanItem = new BeanItem<DateTimeDemoBean>(demoBean);
final FieldGroup fieldGroup = new FieldGroup(demoBeanItem);
fieldGroup.setFieldFactory(new JodaTimeFieldGroupFieldFactory());
addComponent(fieldGroup.buildAndBind("dateTime"));
```

For a more comprehensive example, see [date-time-fields-demo/src/main/java/org/vaadin/demo/DemoUI.java](../../blob/master/date-time-fields-demo/src/main/java/org/vaadin/demo/DemoUI.java)
