# CS2820 Spring 2024 WARP Project Code **updated** 
This code base will be used for the University of Iowa CS 2820 Introduction to Software
Development course. The code was developed by Steve Goddard for the WARP sensor network 
research project. It was first written in Swift and rewritten in Java. It was then 
rewritten again in an object-oriented programming style. It was a quick
hack, and it needs a lot of cleanup and refactoring. A perfect code base to teach
the value of software development fundamentals!
~ software development is cool ~ 


<b> Sprint 2 distribution: </b>

- Weipeng: Step 5
- Matt: Step 3
- Dell: Step 2&3
- Esteban: Step 4
- All: Step 1&6

Diagrams and design documents for Sprint 2: 



<br>
For Sprint 2, Matt and Dell designed the algorithm for the implementation of the ChannelVisualization class by overriding the following methods in visualizationObject abstract class:


- createHeader(), which now creates a description object that contains the title and correct formatting.

- createFooter(), which also creates a description object that contains a message indicating whether the deadlines were met.
- createColumnHeader(), which returns a string array consisting of the time slots

- createVisualizationData(), which creates a 2 dimensional array containing the data from the program and schedule objects.
- displayVisualization: The methods createcolumnHeader(), and createVisualizationData() are designed to fit the parameters of the GuiVisualization constructor, and this is then used to override displayVisualization(). 
<br>
A method for generating the title in the specified format (createTitle()) is also implemented.
<br>
Weipeng developed the jUnit tests for these methods, ensuring their design and implementation, while Esteban updated the uml diagrams for the workLoad and Channel classes, as well as developed javadoc comments for the methods listed above.
