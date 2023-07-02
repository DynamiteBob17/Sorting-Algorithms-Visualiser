# About
- desktop sorting algorithms visualiser with audio feedback like those seen all over YouTube
- has plenty of all kinds of sorts like: selection, insertion, cocktail sort, gravity, quicksort, mergesort, heapsort, radix sorts, some hybrid sorts and so on...
- has 5 different visualisations: the classic bar graph, rgb hoops/circles and disparity hoops meaning they are displaced based on how correctly they are positioned, also circular disparity points as well as those disparity points but connected
- you can control array sizes, midi sounds that are played, volume, sorting speed, shuffling, sorting, watching, etc.
- [live demo](https://drive.google.com/file/d/1K9SKdQmahDoY22hS8GdVRgblg3y5frfv/view)

# How to use
If you have [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) and up installed you can:
- download the .jar executable from the release  
**OR**
- compile and run the repository as a Maven project with your method of choice.  
  
`mvn clean compile exec:java` to run from a command.  
  
> :warning: **If you run the program with no sound devices existing or connected to your system (if your sound is just muted this doesn't apply)**:  
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The MIDI sounds will not be loaded, so if you connect any sound device  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;while in the program, it won't be able to play any sounds. Therefore,  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;you need to restart the program, so it can load the sounds it needs.
