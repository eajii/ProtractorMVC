This version of the protractor tool uses the main window instead of creating a seperate JFrame window.  The secondary window is unnecessary.  Notice in this version the paintComponent method was migrated to use the awt draw method of processing.  Still the features of the swing glasspane were applied to the processing applet.  This is yet another solution to using features of both swing and awt out-of-the box.  There is an additional methodology to consider:  What if we have begun with a Swing application and now want to integrate the processing awt window?  So the processing window as a secondary window to an already existing Swing application as a primary.  This is an interesting question, but as we found with the first version a bit out of scope for this tool.
