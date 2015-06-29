Sitevision Portlet for an Inline Brightcove Video
=================================================

A portlet application built on the Spring Portlet MVC framework. Displays markup for a video on City of Malmo's external and internal web site. The video is retrieved from Brightcove Videocloud by using their JavaScript API. 

## Dependencies
* Sitevision Server 3
* Maven 3

## Build & Deployment
* In the project's folder, run the command `mvn  clean install`. 
* Deploy manually to the server by copying the resulting file `/target/webbvideo-inline.war` to `[Path to Sitevision server]/tomcat/webapps`. 
 
## Development
The project can be imported into Eclipse or Netbeans as a Maven project. 

## Licence
Released under AGPL version 3.
