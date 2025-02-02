# CS2820 Spring 2024 WARP Project Code **updated** 
This code base will be used for the University of Iowa CS 2820 Introduction to Software
Development course. The code was developed by Steve Goddard for the WARP sensor network 
research project. It was first written in Swift and rewritten in Java. It was then 
rewritten again in an object-oriented programming style. It was a quick
hack, and it needs a lot of cleanup and refactoring. A perfect code base to teach
the value of software development fundamentals!



<br>

-For Sprint 3. Esteban and Matt worked on the code for Channel Analysis and Channel Visualization. We were able to create a dummy table
 
-Dell and Weipeng worked on the tests for Channel Analysis and Visualization, located in the "test" folder

-Esteban and Matt updated javadoc comments while Esteban generated them, located in the "doc" folder

-Weipeng made the sequence diagrams and Esteban edited them, located in the "architecture"

-we removed createFooter() in Channel Visualization since we were not using it

-in Channel Analysis, we created buildtable(), which will use parsed information from WarpDSL to create a new table based on their flow, source, sink

-in Channel Analysis, we added isSameCoordinator() to see if there is a channel conflict, if so will change flag to true

-in Channel Analysis, we added initializaProgram() to start the warpdsl and get variables
 
-in Channel Visualization, a couple methods were modified to hopefully correct errors in sprint2. These methods were createColumnHeader(), createHeader(), and createVisualizationData()

-in the end we were not able to fully implement the graph transformation as desired in the assignment, we believe we got close

-the only high level plans are right here in this README
